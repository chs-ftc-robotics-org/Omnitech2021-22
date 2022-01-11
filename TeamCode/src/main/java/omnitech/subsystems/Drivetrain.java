package omnitech.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import omnitech.Subsystem;

public class Drivetrain implements Subsystem {

    public static final boolean active = true;

    public DcMotor rightFront;
    public DcMotor rightRear;
    public DcMotor leftFront;
    public DcMotor leftRear;

    public double drivetrainTicks = 537.6;


    public enum Movement {
        POV,
        STRAFE
    }

    public void initialize(LinearOpMode opMode) {

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

    public int inchesToEncoderCounts(double inches) {
        // (537.6 ticks/1 rev) * (1 rev/100pi mm) * (1 mm/0.0393701 inches)
        double countsPerInch = 43.5;
        return (int) (inches * countsPerInch);
    }

    public void moveByInches(double inches) {
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
