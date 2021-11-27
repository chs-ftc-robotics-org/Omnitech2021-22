package omnitech;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import omnitech.subsystems.Drivetrain;
import omnitech.subsystems.DuckCarousel;

public class OurRobot {

    // add stuff here this basically is a class that lets you access all functionality of bot

    Drivetrain drivetrain = new Drivetrain();
    DuckCarousel carousel = new DuckCarousel();

    private final Subsystem[] subsystems = {
            drivetrain,
            carousel
    };

    public void initialize(LinearOpMode opMode) {
        for(Subsystem system : subsystems){
            system.initialize(opMode);
        }
    }
}
