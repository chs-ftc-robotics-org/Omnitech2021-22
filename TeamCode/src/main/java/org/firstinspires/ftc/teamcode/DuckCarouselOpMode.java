package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

import omnitech.OurRobot;

// run this opmode if robot is positioned closer to duck carousel
@Autonomous
public class DuckCarouselOpMode extends LinearOpMode {

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

        boolean duckBarcodeScanningDone = false;

        waitForStart();

        timer.reset();

        // do we need while loop?
        while(opModeIsActive()) {

            // detecting duck/team shipping element location on barcode
            if (!duckBarcodeScanningDone) {
                if(tfod==null) {
                    telemetry.addData("weird", "tfod is null");
                    telemetry.update();
                    continue;
                }

                List<Recognition> newThings = tfod.getUpdatedRecognitions();
                if(newThings==null)
                    continue;

                int[] sectors = {0, 0, 0};

                for(Recognition thing : newThings){
                    int sector = (int) (thing.getLeft()/(thing.getImageWidth()/3));
                    sectors[sector]++;
                }

                telemetry.addData("Sectors", sectors[0]+" "+sectors[1]+" "+sectors[2]);
                telemetry.update();

                // for now this just adjusts the slide position based on the barcode position
                // will add movement later
                if (sectors[0] > 0) {
                    ;
                }
                else if (sectors[1] > 0) {
                    robot.outtake.slideMiddlePosition();
                }
                else {
                    robot.outtake.slideHighestPosition();
                }
                duckBarcodeScanningDone = true;
            }

            // duck carousel
            double timeBeforeCarousel = timer.milliseconds();

            // gonna change how long this runs later
            // also gotta add code to move the robot to the carousel
            if (timer.milliseconds() - timeBeforeCarousel < 3000) {
                robot.carousel.turn(true);
            }
            else {
                robot.carousel.turn(false);
            }

            // parking
        }
    }
}
