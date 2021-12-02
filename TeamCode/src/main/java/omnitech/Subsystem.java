package omnitech;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public interface Subsystem {

    void initialize(LinearOpMode opMode);
    boolean active();

}
