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

    // prob need a better variable name for this
    public double TURNING_LIMIT = 0.75;
    public double DRIVE_LIMIT = 0.75;
    public double STRAFE_LIMIT = 0.7;
    // 0.7 strafe limit makes motions in all directions the same speed

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

    // turn parameter is only relevant for POV movement
    // turn is a double from -1 to 1
    // for POV x is turn and y is power
    // for STRAFE x and y are.... x and y
    public void power(Movement movementType, double x, double y) {
        switch(movementType) {
            case POV:
                y *= DRIVE_LIMIT;
                x *= TURNING_LIMIT;
                double rightPower = Range.clip(y + x, -1.0, 1.0);
                double leftPower = Range.clip(y - x, -1.0, 1.0);
                rightFront.setPower(rightPower);
                rightRear.setPower(rightPower);
                leftFront.setPower(leftPower);
                leftRear.setPower(leftPower);
                break;
            case STRAFE:
                x *= STRAFE_LIMIT;
                y *= STRAFE_LIMIT;
                
                double power1 = Range.clip(x-y, -1.0, 1.0);
                double power2 = Range.clip(-x-y, -1.0, 1.0);


                leftFront.setPower(power1);
                rightFront.setPower(power2));
                leftRear.setPower(power2);
                rightRear.setPower(power1);

                break
        }
    }



}
