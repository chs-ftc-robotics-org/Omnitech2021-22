package omnitech.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import omnitech.OurRobot;
import omnitech.Subsystem;

public class Intake implements Subsystem {

    public static final boolean active = false;

    // will make this more descriptive once we have an actual design
    public DcMotor intakeMotor;

    private double intakePower = 0.25;

    @Override
    public void initialize(LinearOpMode opMode, OurRobot robot) {
        intakeMotor = opMode.hardwareMap.get(DcMotor.class,"intake_motor");
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public boolean active() { return active; }

    public void setIntakePower(double power) { intakePower = power; }

    public void intakeMove(boolean moving) {
        if (moving) {
            intakeMotor.setPower(intakePower);
        }
        else {
            intakeMotor.setPower(0.0);

        }
    }
}
