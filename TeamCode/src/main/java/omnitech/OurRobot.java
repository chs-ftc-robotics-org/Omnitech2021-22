package omnitech;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import omnitech.subsystems.Drivetrain;
import omnitech.subsystems.DuckCarousel;
import omnitech.subsystems.IMU;
import omnitech.subsystems.Intake;
import omnitech.subsystems.Outtake;

public class OurRobot {

    // add stuff here this basically is a class that lets you access all functionality of bot

    public Drivetrain drivetrain = new Drivetrain();
    public DuckCarousel carousel = new DuckCarousel();
    public IMU imu = new IMU();
    public Intake intake = new Intake();
    public Outtake outtake = new Outtake();

    private final Subsystem[] subsystems = {
            drivetrain,
            carousel,
            imu,
            intake,
            outtake
    };

    public void initialize(LinearOpMode opMode) {
        for(Subsystem system : subsystems){
            if(system.active()) {
                system.initialize(opMode);
            }
        }
    }
}
