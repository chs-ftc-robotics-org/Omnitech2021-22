package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class Practice extends LinearOpMode {

    // initialize motors
    private DcMotor leftFrontMotor;
    private DcMotor rightFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightBackMotor;
    // initialize limits
    private double driveLimit = 0.75;
    private double turnLimit = 0.75;
    // initialize limit system boolean
    private boolean isPressed = false;
    // initialize strafe mode boolean
    private boolean isStrafe = false;

    @Override
    public void runOpMode() {
        // initialize motor hardware map
        leftFrontMotor = hardwareMap.get(DcMotor.class, "left_front_motor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "right_front_motor");
        leftBackMotor = hardwareMap.get(DcMotor.class, "left_back_motor");
        rightBackMotor = hardwareMap.get(DcMotor.class, "right_back_motor");

        // initialize motor directions
        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);

        // initialize motor zero power behaviors
        leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();


        while (opModeIsActive()) {

            // initialize regular mode motor powers
            double leftPower;
            double rightPower;
            // initialize strafe mode power
            double strafePower;

            // adjusting limits
            if (gamepad1.a && !isPressed) {
                driveLimit += 0.05;
                isPressed = true;
            }
            else if (gamepad1.b && !isPressed) {
                driveLimit -= 0.05;
                isPressed = true;
            }
            else if (gamepad1.x && !isPressed) {
                turnLimit += 0.05;
                isPressed = true;
            }
            else if (gamepad1.y && !isPressed) {
                turnLimit -= 0.05;
                isPressed = true;
            }
            else {
                isPressed = false;
            }

            // switching to strafe mode
            if (gamepad1.left_bumper) {
                isStrafe = true;
            }
            // switching to regular mode
            if (gamepad1.right_bumper) {
                isStrafe = false;
            }
            // regular mode driving
            if (!isStrafe) {
                double drive = -driveLimit * gamepad1.left_stick_y;
                double turn = turnLimit * gamepad1.right_stick_x;

                leftPower = Range.clip(drive + turn, -1.0, 1.0);
                rightPower = Range.clip(drive - turn, -1.0, 1.0);

                leftFrontMotor.setPower(leftPower);
                rightFrontMotor.setPower(rightPower);
                leftBackMotor.setPower(leftPower);
                rightBackMotor.setPower(rightPower);
            }
            // strafe mode driving
            else {
                strafePower = driveLimit * gamepad1.left_stick_x;
                leftFrontMotor.setPower(strafePower);
                rightFrontMotor.setPower(-strafePower);
                leftBackMotor.setPower(-strafePower);
                rightBackMotor.setPower(strafePower);
            }
        }
    }
}