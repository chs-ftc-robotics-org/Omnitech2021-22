package omnitech.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import static java.lang.Math.*;

import omnitech.OurRobot;
import omnitech.Subsystem;

public class Outtake implements Subsystem {

    public static final boolean active = true;

    public DcMotor slide;
    public Servo elbow;
    public Servo box;

    public double slidePower = 0.25;
    public double boxPosition = 0.5;

    // just for reference
    private double slideRotations = 56.25;
    private double motorTicks;

    public boolean slideExtendedFully = false;
    public boolean slideRetractedFully = true;

    @Override
    public void initialize(LinearOpMode opMode, OurRobot robot) {
        slide = opMode.hardwareMap.get(DcMotor.class, "slide");
        slide.setDirection(DcMotor.Direction.FORWARD);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elbow = opMode.hardwareMap.get(Servo.class, "elbow");
        box = opMode.hardwareMap.get(Servo.class, "box");
        box.setDirection(Servo.Direction.FORWARD);
        elbow.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public boolean active() { return active; }

    //public void setSlidePower(double power) { slidePower = power; }

    public void setBoxPwr(double position) { box.setPosition(position);}

    public void slideMove(boolean moving) {
        if (moving) {
            slide.setPower(slidePower);
        }
        else {
            slide.setPower(0.0);

        }
    }

    public void slideHighestPosition() {

        /*slidePower = abs(slidePower);
        while (slide.getCurrentPosition() < 4.25 * motorTicks) {
            slideMove(true);
        }
        slideMove(false);
        slideExtendedFully = true;
        slideRetractedFully = false;*/    }

    public void slideLowestPosition() {
        slidePower = -abs(slidePower);
        while (slide.getCurrentPosition() > 0) {
            slideMove(true);
        }
        slideMove(false);
        slideExtendedFully = false;
        slideRetractedFully = true;
    }

    public void slideMiddlePosition() {
        if (slideExtendedFully) {
            slidePower = -abs(slidePower);
            while (slide.getCurrentPosition() > 2.125 * motorTicks) {
                slideMove(true);
            }
            slideMove(false);
        }
        else {
            slidePower = abs(slidePower);
            while (slide.getCurrentPosition() < 2.125 * motorTicks) {
                slideMove(true);
            }
            slideMove(false);
        }
        slideExtendedFully = false;
        slideRetractedFully = false;
    }
    public void setElbowPwr(double power){
        elbow.setPosition(power);
    }
    public void setSlidePower (double power1){
        slide.setPower(power1);
    }
}
