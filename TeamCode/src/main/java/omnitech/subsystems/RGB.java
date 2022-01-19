package omnitech.subsystems;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import omnitech.OurRobot;
import omnitech.Subsystem;

public class RGB implements Subsystem {
    @Override
    public void initialize(LinearOpMode opMode, OurRobot robot) {
        RevBlinkinLedDriver blinkinLedDriver;
        RevBlinkinLedDriver.BlinkinPattern pattern;
        blinkinLedDriver = opMode.hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
        pattern = RevBlinkinLedDriver.BlinkinPattern.RED;   //Change this however you like
        blinkinLedDriver.setPattern(pattern);
    }

    @Override
    public boolean active() {
        return false;
    }
}
