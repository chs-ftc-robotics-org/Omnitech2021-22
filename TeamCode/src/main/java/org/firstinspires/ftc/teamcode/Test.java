package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

// just runs a motor "only_motor"
@TeleOp(name="Test", group="Linear Opmode")
public class TestOpMode extends LinearOpMode {

    private DcMotor motor = null;

    @Override
    public void runOpMode(){
        motor  = hardwareMap.get(DcMotor.class, "only_motor");
        motor.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        while (opModeIsActive()){
            motor.setPower(1);
        }
    }
}