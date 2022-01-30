package omnitech;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import omnitech.subsystems.*;

public class OurRobot {

    // add stuff here this basically is a class that lets you access all functionality of bot

    public Drivetrain drivetrain = new Drivetrain();
    public DuckCarousel carousel = new DuckCarousel();
    public IMU imu = new IMU();
    public Intake intake = new Intake();
    public Outtake outtake = new Outtake();
    public Camera camera = new Camera();
    public Slides slides = new Slides();
    public RGB rgb = new RGB();

    private final Subsystem[] subsystems = {
            camera,
            drivetrain,
            carousel,
            imu,
            intake,
            outtake
            //slides
            //rgb
    };

    public void initialize(LinearOpMode opMode) {
        for(Subsystem system : subsystems){
            if(system.active()) {
                system.initialize(opMode, this);
            }
        }
    }
}
