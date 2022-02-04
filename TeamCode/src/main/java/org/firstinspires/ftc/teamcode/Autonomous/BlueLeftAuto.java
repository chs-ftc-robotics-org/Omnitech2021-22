package org.firstinspires.ftc.teamcode.Autonomous;

//import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

import omnitech.OurRobot;

@Disabled
@Autonomous(name="Blue Left Auto", group = "auto")
public class BlueLeftAuto extends LinearOpMode {
    private OurRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("State", "Initializing...");
        telemetry.update();

        robot = new OurRobot();
        robot.initialize(this);

        telemetry.addData("State", "Ready to start...");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()) {
            robot.drivetrain.povDrive(0.3, 0);
            sleep(1000);
            robot.drivetrain.rotate(45);
            robot.outtake.slideHighestPosition();
        }

    }

}
