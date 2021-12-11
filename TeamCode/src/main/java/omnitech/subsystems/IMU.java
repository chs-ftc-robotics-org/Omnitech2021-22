package omnitech.subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import omnitech.Subsystem;

public class IMU implements Subsystem {

    public static final boolean active = false;
    public boolean active() {
        return active;
    }

    public BNO055IMU imu;

    public void initialize(LinearOpMode opMode) {
        imu = opMode.hardwareMap.get(BNO055IMU.class, "IMU");
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        params.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        params.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        params.calibrationDataFile = "AdafruitIMUCalibration.json";
        params.loggingEnabled = true;
        params.loggingTag = "IMU";
        params.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu.initialize(params);
        while(!opMode.isStopRequested() && !imu.isGyroCalibrated());
    }


}
