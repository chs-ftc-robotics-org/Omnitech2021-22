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
        boolean bWasPressed = false;
        int positiveNegativeMultiplier;

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

            if(aPressed) positiveNegativeMultiplier = -1;
            else positiveNegativeMultiplier = 1;
            
//            if(yPressed && !yWasPressed) robot.drivetrain.TURNING_LIMIT += 0.05*positiveNegativeMultiplier;
//            if(bPressed && !bWasPressed) robot.drivetrain.STRAFE_LIMIT += 0.05*positiveNegativeMultiplier;
//            if(xPressed && !xWasPressed) robot.drivetrain.DRIVE_LIMIT += 0.05*positiveNegativeMultiplier;

            xWasPressed = xPressed;
            yWasPressed = yPressed;
            bWasPressed = bPressed;

//            telemetry.addData("Drive Limit", robot.drivetrain.DRIVE_LIMIT);
//            telemetry.addData("Turning Limit", robot.drivetrain.TURNING_LIMIT);
//            telemetry.addData("Strafe Limit", robot.drivetrain.STRAFE_LIMIT);


//            robot.drivetrain.power(Drivetrain.Movement.POV, turnAmt, drivePower);
//            robot.drivetrain.power(Drivetrain.Movement.STRAFE, strafeX, strafeY);

            telemetry.update();
        }

    }


}
