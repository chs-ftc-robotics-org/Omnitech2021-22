package omnitech.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import omnitech.Subsystem;

public class Outtake implements Subsystem {

    public static final boolean active = false;

    public DcMotor slide;
    public Servo box;

    private double slidePower = 0.25;
    private double boxPosition = 0.5;
    private double slideRotations = 4.25;

    @Override
    public void initialize(LinearOpMode opMode) {
        slide = opMode.hardwareMap.get(DcMotor.class, "slide");
        slide.setDirection(DcMotorSimple.Direction.FORWARD);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        box = opMode.hardwareMap.get(Servo.class, "box");
        box.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public boolean active() { return active; }

    public void setSlidePower(double power) { slidePower = power; }

    public void setRotations(double rotations) { slideRotations = rotations; }

    public void setBoxPosition(double position) { boxPosition = position; }

    public void slideMove(boolean moving) {
        if (moving) {
            slide.setPower(slidePower);
        }
        else {
            slide.setPower(0.0);
        }
    }
}
