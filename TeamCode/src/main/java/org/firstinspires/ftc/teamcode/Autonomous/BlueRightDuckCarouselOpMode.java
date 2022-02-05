package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.value;

import java.util.List;

import omnitech.OurRobot;

// run this opmode if robot is positioned closer to duck carousel
@Autonomous
public class BlueRightDuckCarouselOpMode extends LinearOpMode {

    private OurRobot robot;
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        telemetry.addData("State", "Initializing...");
        telemetry.update();

        robot = new OurRobot();
        robot.initialize(this);

        telemetry.addData("State", "Ready to start...");
        telemetry.update();

        ElapsedTime timer = new ElapsedTime();

        tfod = robot.camera.getTfod();

        boolean preLoadedBoxDelivery = false;
        boolean movementDone = false;
        boolean duckDeliveryDone = false;

        waitForStart();

        timer.reset();

        while(opModeIsActive()) {

            // delivering pre-loaded box (we're just gonna put the box on the highest level
            // unless we somehow get the camera to work
            if (!preLoadedBoxDelivery) {
                robot.drivetrain.povDrive(0.3, 0);
                sleep(1000);
                robot.drivetrain.povDrive(0.0, 0);
                robot.drivetrain.povDrive(0.3,45);
                robot.outtake.slide.setPower(0.3);
                sleep(1250);
                robot.outtake.setElbowPwr(value.elbowExtended);
                robot.outtake.setBoxPwr(value.boxDumped);
                sleep(500);
                robot.outtake.setBoxPwr(value.boxVertical);
                robot.outtake.setElbowPwr(value.elbowRetracted);
                robot.outtake.slideLowestPosition();
                preLoadedBoxDelivery = true;
            }

            // driving to carousel
            if (!movementDone) {
                robot.drivetrain.rotate(180);
                robot.drivetrain.povDrive(0.3, 0);
                sleep(2500);
                robot.drivetrain.povDrive(0, 0);
                movementDone = true;
            }

            // duck carousel
            if (!duckDeliveryDone) {
                robot.carousel.turn(true);
                sleep(6000);
                robot.carousel.stop();
                duckDeliveryDone = true;
            }

            // parking
            robot.drivetrain.rotate(-135);
            robot.drivetrain.povDrive(0.3, 0);
            sleep(500);
            robot.drivetrain.povDrive(0.0, 0);
            //sleep((long) (30000 - timer.milliseconds()));
        }
    }
}
