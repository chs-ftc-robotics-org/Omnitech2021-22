package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import omnitech.OurRobot;

public class MotorsTest extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot();
        robot.initialize(this);
        waitForStart();
        while(opModeIsActive()) {
            double power = 0.2;
            if(gamepad1.a)
                robot.drivetrain.leftFront.setPower(power);
            if(gamepad1.b)
                robot.drivetrain.leftFront.setPower(power);
            if(gamepad1.x)
                robot.drivetrain.leftFront.setPower(power);
            if(gamepad1.y)
                robot.drivetrain.leftFront.setPower(power);

            telemetry.addData("leftFrontPwr:", robot.drivetrain.leftFront.getPower());
            telemetry.addData("rightFrontPwr:", robot.drivetrain.rightFront.getPower());
            telemetry.addData("leftRearPwr:", robot.drivetrain.leftRear.getPower());
            telemetry.addData("rightRearPwr:", robot.drivetrain.rightRear.getPower());
            telemetry.update();
        }
    }
}
