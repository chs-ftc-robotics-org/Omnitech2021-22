package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.value;

import java.util.List;

import omnitech.OurRobot;

// run this opmode if robot is positioned closer to freight
@Autonomous
public class BlueLeftFreightOpMode extends LinearOpMode {

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
        int freightDelivery = 0;

        waitForStart();

        timer.reset();

        while (opModeIsActive()) {

            // delivering pre-loaded box (we're just gonna put the box on the highest level
            // unless we somehow get the camera to work
            if (!preLoadedBoxDelivery) {
                robot.drivetrain.povDrive(0.3, 0);
                sleep(1000);
                robot.drivetrain.povDrive(0.0, 0);
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
            /*if (!duckBarcodeScanningDone) {
                if (tfod == null) {
                    telemetry.addData("weird", "tfod is null");
                    telemetry.update();
                    continue;
                }

                List<Recognition> newThings = tfod.getUpdatedRecognitions();
                if (newThings == null)
                    continue;

                int[] sectors = {0, 0, 0};

                for (Recognition thing : newThings) {
                    int sector = (int) (thing.getLeft() / (thing.getImageWidth() / 3));
                    sectors[sector]++;
                }

                telemetry.addData("Sectors", sectors[0] + " " + sectors[1] + " " + sectors[2]);
                telemetry.update();

                // for now this just adjusts the slide position based on the barcode position
                // will add movement later
                if (sectors[0] > 0) {
                    ;
                } else if (sectors[1] > 0) {
                    robot.outtake.slideMiddlePosition();
                } else {
                    robot.outtake.slideHighestPosition();
                }
                duckBarcodeScanningDone = true;
            }*/
/*
            // freight delivery
            if (freightDelivery < 2) {
                // driving to freight
                robot.drivetrain.rotate(180);
                robot.drivetrain.povDrive(0.3, 0);
                sleep(1000);
                robot.drivetrain.povDrive(0.0, 0);
                robot.drivetrain.rotate(45);
                robot.drivetrain.povDrive(0.3, 0);
                sleep(1000);
                robot.drivetrain.povDrive(0.0, 0);

                // taking in freight through intake
                robot.intake.setIntakePower(0.5);
                sleep(2000);
                robot.intake.setIntakePower(0);

                // driving to shipping hub
                robot.drivetrain.rotate(180);
                robot.drivetrain.povDrive(0.3, 0);
                sleep(1000);
                robot.drivetrain.povDrive(0.0, 0);
                robot.drivetrain.rotate(-45);
                robot.drivetrain.povDrive(0.3, 0);
                sleep(1000);
                robot.drivetrain.povDrive(0.0, 0);

                // delivering freight onto shipping hub
                robot.outtake.slideHighestPosition();
                robot.outtake.setElbowPwr(0.2);
                robot.outtake.setBoxPwr(0.2);
                sleep(500);
                robot.outtake.setBoxPwr(-0.2);
                robot.outtake.setElbowPwr(-0.3);
                robot.outtake.slideLowestPosition();
                freightDelivery++;
            }
*/
            // parking
            robot.drivetrain.povDrive(0.3,180);
            robot.drivetrain.povDrive(0.3, 0);
            sleep(1000);
            robot.drivetrain.povDrive(0.0, 0);
            robot.drivetrain.povDrive(0.3,45);
            robot.drivetrain.povDrive(0.3, 0);
            sleep(1000);
            robot.drivetrain.povDrive(0.0, 0);
            //sleep((long) (30000 - timer.milliseconds()));

        }
    }
}
