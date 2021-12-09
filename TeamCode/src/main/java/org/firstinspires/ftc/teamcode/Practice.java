package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class Practice extends LinearOpMode {

    private DcMotor leftFrontMotor;
    private DcMotor rightFrontMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightBackMotor;
    private double driveLimit = 0.75;
    private double turnLimit = 0.75;
    private boolean isPressed = false;

    @Override
    public void runOpMode() {
        leftFrontMotor = hardwareMap.get(DcMotor.class, "left_front_motor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "right_front_motor");
        leftBackMotor = hardwareMap.get(DcMotor.class, "left_back_motor");
        rightBackMotor = hardwareMap.get(DcMotor.class, "right_back_motor");

        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);

        leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();


        while (opModeIsActive()) {

            double leftPower;
            double rightPower;

            if (gamepad1.a) {
                driveLimit += 0.05;
                isPressed = true;
            }
            else if (gamepad1.b) {
                driveLimit -= 0.05;
                isPressed = true;
            }
            else if (gamepad1.x) {
                turnLimit += 0.05;
                isPressed = true;
            }
            else if (gamepad1.y) {
                turnLimit -= 0.05;
                isPressed = true;
            }
            else {
                isPressed = false;
            }

            double drive = -driveLimit * gamepad1.left_stick_y;
            double turn = turnLimit * gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -1.0, 1.0);
            rightPower = Range.clip(drive - turn, -1.0, 1.0);

            leftFrontMotor.setPower(leftPower);
            rightFrontMotor.setPower(rightPower);
            leftBackMotor.setPower(leftPower);
            rightBackMotor.setPower(rightPower);

        }
    }
}