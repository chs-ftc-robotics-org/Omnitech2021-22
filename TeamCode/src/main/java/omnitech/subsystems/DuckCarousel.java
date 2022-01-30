package omnitech.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import omnitech.OurRobot;
import omnitech.Subsystem;

public class DuckCarousel implements Subsystem {

    public static final boolean active = true;

    public DcMotor duckSpinner;

    public static double turnPower = 0.3;
    // 1.0 power m a y be a little overkill for this

    public void initialize(LinearOpMode opMode, OurRobot robot) {
        duckSpinner = opMode.hardwareMap.get(DcMotor.class, "duckSpinner");
        duckSpinner.setDirection(DcMotorSimple.Direction.FORWARD);
        duckSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public boolean active() {
        return active;
    }

    public void turn(boolean direction) {
        if (direction) {
            duckSpinner.setPower(turnPower);
        } else {
            duckSpinner.setPower(-turnPower);
        }
    }
    public void stop() {
        duckSpinner.setPower(0);
    }
    public static void setTurnPower(int amount){
        turnPower += amount;
    }

    public void turn(int amount1){
        // spin the motor a certain amount and then stop
    }

}
