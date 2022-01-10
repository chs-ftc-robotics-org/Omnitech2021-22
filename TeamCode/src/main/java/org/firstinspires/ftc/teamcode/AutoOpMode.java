package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import omnitech.OurRobot;

@Autonomous
public class AutoOpMode extends LinearOpMode {

    private OurRobot robot;

    @Override
    public void runOpMode() {

        telemetry.addData("State", "Initializing...");
        telemetry.update();

        robot = new OurRobot();
        robot.initialize(this);

        telemetry.addData("State", "Ready to start...");
        telemetry.update();

        ElapsedTime timer = new ElapsedTime();

        waitForStart();

        timer.reset();

        double target = 90.0;
        double lastError = -1*(target - robot.imu.getHeading());
        double lastTime = timer.milliseconds();
        while(opModeIsActive()) {
            robot.imu.logVals();
            telemetry.update();
            double angle = robot.imu.getHeading();

            double timeNow = timer.milliseconds();

            double kp = 0.075; // 0.05 is kinda stable and 0.75 oscillates a decent amt
            double p = -1*kp*(target - angle);

            double kd = 0;
            double d = kd*(p-lastError)/(timeNow-lastTime);


            double turnAmt = Range.clip(p+d, -1.0, 1.0);

            robot.drivetrain.povDrive(0.0, turnAmt);

            lastError = p;
            lastTime = timeNow;

        }
    }
}
