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
            if(gamepad2.x){
                robot.carousel.turn(true);
            }
            if(gamepad2.y){
                robot.carousel.turn(false);
            }
            if(gamepad2.a){
                robot.outtake.setBoxPosition(0.0);
            }
            if(gamepad2.b){
                robot.outtake.setBoxPosition(0.7);
            }
            if(gamepad2.left_bumper){
                robot.outtake.setElbowPower(0.4);
            }
            if(gamepad2.right_bumper){
                robot.outtake.setElbowPower(-0.4);
            }
            if(gamepad2.right_trigger > 0.05)
                robot.outtake.setSlidePower(gamepad2.right_trigger - 0.1);
            else if(gamepad2.left_trigger > 0.05)
                robot.outtake.setSlidePower(gamepad2.left_trigger - 0.1);



            // intake
            //robot.intake.intakeMove(gamepad1.b);

            // outtake (probably use left and right bumper)

            robot.imu.logVals();
            telemetry.update();
        }
    }
}
