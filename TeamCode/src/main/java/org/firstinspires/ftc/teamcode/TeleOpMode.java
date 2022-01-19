package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import omnitech.OurRobot;
import omnitech.subsystems.Drivetrain;

@TeleOp
public class TeleOpMode extends LinearOpMode {

    private OurRobot robot;

    @Override
    public void runOpMode() {

        telemetry.addData("State", "Initializing...");
        telemetry.update();

        robot = new OurRobot();
        robot.initialize(this);

        telemetry.addData("State", "Ready to start...");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()) {

            // drivetrain
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
            if (gamepad1.a) {
                robot.carousel.turn(true);
            }
            else {
                robot.carousel.turn(false);
            }

            // intake
            robot.intake.intakeMove(gamepad1.b);

            // outtake (probably use left and right bumper)

            robot.imu.logVals();
            telemetry.update();
        }
    }
}
