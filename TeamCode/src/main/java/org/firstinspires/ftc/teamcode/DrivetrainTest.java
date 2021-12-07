package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import omnitech.OurRobot;
import omnitech.subsystems.Drivetrain;

@TeleOp(name="DrivetrainTest", group="Linear OpMode")
public class DrivetrainTest extends LinearOpMode{

    private OurRobot robot;
    private Drivetrain.Movement movementType;

    @Override
    public void runOpMode(){

        telemetry.addData("State", "Initializing...");
        telemetry.update();

        robot = new OurRobot();
        robot.initialize(this);

        movementType = Drivetrain.Movement.POV;
        telemetry.addData("Movement Mode", "POV");


        telemetry.addData("State", "Ready to start...");
        telemetry.update();
        waitForStart();

        boolean xWasPressed = false;
        boolean yWasPressed = false;
        boolean aWasPressed = false;
        boolean bWasPressed = false;

        while(opModeIsActive()) {
            double drivePower = gamepad1.right_trigger-gamepad1.left_trigger;
            double turnAmt = gamepad1.left_stick_x;
            boolean driveButton = gamepad1.dpad_left;
            boolean povButton = gamepad1.dpad_up;
            boolean rotateButton = gamepad1.dpad_right;
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

            if(driveButton) {
                movementType = Drivetrain.Movement.DRIVE;
                telemetry.addData("Movement Mode", "DRIVE");
            } else if(povButton) {
                movementType = Drivetrain.Movement.POV;
                telemetry.addData("Movement Mode", "POV");
            } else if(rotateButton) {
                movementType = Drivetrain.Movement.ROTATE;
                telemetry.addData("Movement Mode", "ROTATE");
            }


            

            robot.drivetrain.power(movementType, drivePower, turnAmt);
            telemetry.update();
        }

    }


}
