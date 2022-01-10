package omnitech.subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import omnitech.Subsystem;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class IMU implements Subsystem {

    public static final boolean active = true;
    public boolean active() {
        return active;
    }

    public BNO055IMU imu;
    public OpMode currentOpMode;

    public void initialize(LinearOpMode opMode) {
        currentOpMode = opMode;
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

    public void logVals() {
        Orientation angles = imu.getAngularOrientation();
        currentOpMode.telemetry.addData("1st Angle", angles.firstAngle);
        currentOpMode.telemetry.addData("2nd Angle", angles.secondAngle);
        currentOpMode.telemetry.addData("3rd Angle", angles.thirdAngle);
    }


}
