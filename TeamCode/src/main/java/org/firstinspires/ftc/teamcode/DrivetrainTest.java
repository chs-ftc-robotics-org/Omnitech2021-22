package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import omnitech.OurRobot;
import omnitech.subsystems.Drivetrain;

@TeleOp(name="DrivetrainTest", group="Linear OpMode")
public class DrivetrainTest extends LinearOpMode{

    private OurRobot robot;

    @Override
    public void runOpMode(){

        telemetry.addData("State", "Initializing...");
        telemetry.update();

        robot = new OurRobot();
        robot.initialize(this);


        telemetry.addData("State", "Ready to start...");
        telemetry.update();

        boolean xWasPressed = false;
        boolean yWasPressed = false;
        boolean aWasPressed = false;
        boolean bWasPressed = false;

        waitForStart();

        while(opModeIsActive()) {
            double drivePower = gamepad1.right_trigger-gamepad1.left_trigger;
            double turnAmt = gamepad1.left_stick_x;
            double strafeX = gamepad1.right_stick_x;
            double strafeY = gamepad1.right_stick_y;
            boolean xPressed = gamepad1.x;
            boolean yPressed = gamepad1.y;
            boolean aPressed = gamepad1.a;
            boolean bPressed = gamepad1.b;

            if(yPressed) {
                if(!yWasPressed)
                    robot.drivetrain.DRIVE_LIMIT += 0.05;
            }
            if (aPressed) {
                if (!aWasPressed)
                    robot.drivetrain.DRIVE_LIMIT -= 0.05;
            }
            if(bPressed) {
                if(!bWasPressed)
                    robot.drivetrain.TURNING_LIMIT += 0.05;
            }
            if(xPressed) {
                if(!xWasPressed)
                    robot.drivetrain.TURNING_LIMIT -= 0.05;
            }
            xWasPressed = xPressed;
            aWasPressed = aPressed;
            yWasPressed = yPressed;
            bWasPressed = bPressed;
            telemetry.addData("Drive Limit", robot.drivetrain.DRIVE_LIMIT);
            telemetry.addData("Turning Limit", robot.drivetrain.TURNING_LIMIT);


            

            robot.drivetrain.power(movementType, drivePower, turnAmt);
            telemetry.update();
        }

    }


}
