package omnitech.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import omnitech.OurRobot;
import omnitech.Subsystem;

public class DuckCarousel implements Subsystem {

    public static final boolean active = true;

    public DcMotor duckSpinner;

    private static final double TURN_POWER = 0.3;
    // 1.0 power m a y be a little overkill for this

    public void initialize(LinearOpMode opMode, OurRobot robot) {
        duckSpinner = opMode.hardwareMap.get(DcMotor.class, "duckSpinner");
        duckSpinner.setDirection(DcMotorSimple.Direction.FORWARD);
        duckSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public boolean active() {
        return active;
    }

    public void turn(boolean turning) {
        if(turning) {
            duckSpinner.setPower(TURN_POWER);
        } else {
            duckSpinner.setPower(0.0);
        }
    }

    public void turn(int amount){
        // spin the motor a certain amount and then stop
    }

}
