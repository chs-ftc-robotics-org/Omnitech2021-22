package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

import omnitech.OurRobot;

@TeleOp(name="Camera Test 2", group = "Test")
public class CameraTest2 extends LinearOpMode {
    private OurRobot robot;
    private TFObjectDetector tfod;


    @Override
    public void runOpMode() {

        telemetry.addData("State", "Initializing...");
        telemetry.update();

        robot = new OurRobot();
        robot.initialize(this);
        tfod = robot.camera.getTfod();


        telemetry.addData("State", "Ready...");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){
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
        }


    }

}
