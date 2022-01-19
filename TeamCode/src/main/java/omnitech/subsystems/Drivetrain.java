package omnitech.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import omnitech.OurRobot;
import omnitech.Subsystem;

public class Drivetrain implements Subsystem {

    public static final boolean active = true;

    public DcMotor rightFront;
    public DcMotor rightRear;
    public DcMotor leftFront;
    public DcMotor leftRear;

    public OurRobot robotInstance;
    public LinearOpMode opModeInstance;

    public enum Movement {
        POV,
        STRAFE
    }

    public void initialize(LinearOpMode opMode, OurRobot robot) {

        robotInstance = robot;
        opModeInstance = opMode;

        rightFront = opMode.hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = opMode.hardwareMap.get(DcMotor.class, "rightRear");
        leftFront = opMode.hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = opMode.hardwareMap.get(DcMotor.class, "leftRear");

        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public boolean active() {
        return active;
    }

    public void povDrive(double power, double turn) {
        double y = power;
        double x = turn;
        double rightPower = Range.clip(y + x, -1.0, 1.0);
        double leftPower = Range.clip(y - x, -1.0, 1.0);
        rightFront.setPower(rightPower);
        rightRear.setPower(rightPower);
        leftFront.setPower(leftPower);
        leftRear.setPower(leftPower);
    }

    public void strafe(double x, double y, double r){
        x *= 1.1;
        
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(r), 1);

        double power1 = x-y;
        double power2 = -x-y;

        
        leftFront.setPower((power1+r)/denominator);
        rightFront.setPower((power2-r)/denominator);
        leftRear.setPower((power2+r)/denominator);
        rightRear.setPower((power1-r)/denominator);
    }

    public void rotate(double target) {

        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        double lastError = -1*(target - robotInstance.imu.getHeading());
        double lastTime = timer.milliseconds();
        while(opModeInstance.opModeIsActive()) {
            robotInstance.imu.logVals();
            opModeInstance.telemetry.update();
            double angle = robotInstance.imu.getHeading();

            double timeNow = timer.milliseconds();

            double kp = 0.06; // 0.05 is kinda stable and 0.75 oscillates a decent amt2
            double p = -1*kp*(target - angle);

            double kd = 50; // between 75 and 100 kinda works
            double d = kd*(p-lastError)/(timeNow-lastTime);


            double turnAmt = Range.clip(p+d, -1.0, 1.0);

            robotInstance.drivetrain.povDrive(0.0, turnAmt);

            lastError = p;
            lastTime = timeNow;

        }
    }

    //TODO: Add PID to move method
    public void move(double inches, double power) {
        robotInstance.drivetrain.povDrive(power, 0);
    }

    public int inchesToEncoderCounts(double inches) {
        // (537.6 ticks/1 rev) * (1 rev/100pi mm) * (1 mm/0.0393701 inches)
        double countsPerInch = 43.5;
        return (int) (inches * countsPerInch);
    }

    // this is probably gonna be jerky if we actually use this
    public void moveByInches(double inches) {

        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int encoderCounts = inchesToEncoderCounts(inches);

        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightFront.setTargetPosition(encoderCounts);
        rightRear.setTargetPosition(encoderCounts);
        leftFront.setTargetPosition(encoderCounts);
        leftRear.setTargetPosition(encoderCounts);
    }
}
