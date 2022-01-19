package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import omnitech.OurRobot;

@Autonomous
public class EncodersTest extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot();
        robot.initialize(this);
        waitForStart();
        while(opModeIsActive()) {

            telemetry.addData("leftFrontPosition:", robot.drivetrain.leftFront.getCurrentPosition());
            telemetry.addData("rightFrontPosition:", robot.drivetrain.rightFront.getCurrentPosition());
            telemetry.addData("leftRearPosition:", robot.drivetrain.leftRear.getCurrentPosition());
            telemetry.addData("rightRearPosition:", robot.drivetrain.rightRear.getCurrentPosition());
            telemetry.update();
        }
    }
}
