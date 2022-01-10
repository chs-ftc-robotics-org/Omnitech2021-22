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

        double power1 = Range.clip(x-y, -1.0, 1.0);
        double power2 = Range.clip(-x-y, -1.0, 1.0);

        
        leftFront.setPower((power1+r)/denominator);
        rightFront.setPower((power2-r)/denominator);
        leftRear.setPower((power2+r)/denominator);
        rightRear.setPower((power1-r)/denominator);
    }

}
