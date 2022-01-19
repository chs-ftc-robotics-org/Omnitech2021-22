package omnitech.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import omnitech.OurRobot;
import omnitech.Subsystem;

public class Slides implements Subsystem {
    public DcMotorEx slideMotor;
    public Servo slideServo;
    //TODO: Calculate actual value
    public static final int TICKS_TO_INCHES = 2000;
    @Override
    public void initialize(LinearOpMode opMode, OurRobot robot) {
        slideMotor = opMode.hardwareMap.get(DcMotorEx.class, "duckSpinner");
        slideMotor.setDirection(DcMotorEx.Direction.FORWARD);
        slideMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        slideMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        slideServo = opMode.hardwareMap.get(Servo.class, "duckSpinner");
        slideServo.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public boolean active() {
        return false;
    }
}
