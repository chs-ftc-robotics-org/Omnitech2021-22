package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import omnitech.OurRobot;

@Autonomous
public class JustPark extends LinearOpMode {

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

        boolean parked = false;

        while (opModeIsActive()) {
            if (!parked) {
                robot.drivetrain.strafe(-1, 0, 0);
                sleep(10000);
                robot.drivetrain.povDrive(0.0, 0);
                parked = true;
            }
        }
    }
}
