package omnitech.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import omnitech.Subsystem;

public class Drivetrain implements Subsystem {

    public DcMotor rightFront;
    public DcMotor rightRear;
    public DcMotor leftFront;
    public DcMotor leftRear;

    public enum Movement {
        DRIVE,
        ROTATE,
        POV
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

    // turn parameter is only relevant for POV movement
    // turn is a double from -1 to 1
    public void power(Movement movementType, double power, double turn) {
        switch(movementType) {
            case DRIVE:
                rightFront.setPower(power);
                rightRear.setPower(power);
                leftFront.setPower(power);
                leftRear.setPower(power);
                break;

            case ROTATE:
                rightFront.setPower(-1*power);
                rightRear.setPower(-1*power);
                leftFront.setPower(power);
                leftRear.setPower(power);
                break;

            case POV:
                double rightPower = Range.clip(power - turn, -1.0, 1.0);
                double leftPower = Range.clip(power + turn, -1.0, 1.0);
                rightFront.setPower(rightPower);
                rightRear.setPower(rightPower);
                leftFront.setPower(leftPower);
                leftRear.setPower(leftPower);
                break;
        }
    }

}
