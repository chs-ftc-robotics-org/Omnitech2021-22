package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import omnitech.OurRobot;

//TODO: work in progress. this will only work on the full robot
@Disabled
@TeleOp(name="TwoDriverTeleOp", group="Linear OpMode")
public class TwoDriverTeleop extends LinearOpMode{

    private OurRobot robot;

    @Override
    public void runOpMode(){

        telemetry.addData("State", "Initializing...");
        telemetry.update();

        robot = new OurRobot();
        robot.initialize(this);


        telemetry.addData("State", "Ready to start...");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()) {
            // idea: driver 1 focuses on driving robot around while driver 2 focuses on other stuff
            double drivePower = gamepad1.right_trigger-gamepad1.left_trigger;
            double turnAmt = gamepad1.left_stick_x;
            double strafeX = gamepad1.right_stick_x;
            double strafeY = gamepad1.right_stick_y;

            robot.drivetrain.povDrive(drivePower, turnAmt);
            // should probably use a deadzone for this
            if(Math.abs(strafeX)>0.3 || Math.abs(strafeY)>0.3) {
                robot.drivetrain.strafe(strafeX, strafeY, turnAmt);
            }

            // carousel
            robot.carousel.turn(gamepad2.a);

            // intake
            robot.intake.intakeMove(gamepad2.b);

            // outtake slide
            if (gamepad2.right_bumper) {
                //robot.outtake.setSlidePower(abs(robot.outtake.slidePower));
                robot.outtake.slideMove(true);
            }
            else if (gamepad2.left_bumper) {
                //robot.outtake.setSlidePower(-abs(robot.outtake.slidePower));
                robot.outtake.slideMove(true);
            }
            else {robot.outtake.slideMove(false);}

            robot.imu.logVals();
            telemetry.update();
        }
    }
}


