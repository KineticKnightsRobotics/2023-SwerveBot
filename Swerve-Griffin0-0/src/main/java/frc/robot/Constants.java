package frc.robot;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

public class Constants {
    
    public static final class ModuleConstants {
        public static final double kWheelDiameterMeters = Units.inchesToMeters(4.0);
        public static final double kDriveMotorGearRatio = 8.14 / 1.0; // Drive ratio of 8.14 : 1
        public static final double kTurningMotorGearRatio = 1.0 / (150.0 / 7.0); // Turning ratio of (150 / 7) : 1
        public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters;
        public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
        public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60.0;
        public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60.0;
        public static final double kPTurning = 0.5; // For PID
    }

    public static final class DriveConstants {

        public static final double kTrackWidth = Units.inchesToMeters(22.5); // Distance between right and left wheels
        public static final double kWheelBase = Units.inchesToMeters(22.5); // Distance between front and back wheels

        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2));

        // DRIVE Motor Ports
        public static final int kFrontLeftDriveMotorPort = 4;
        public static final int kBackLeftDriveMotorPort = 2;
        public static final int kFrontRightDriveMotorPort = 6;
        public static final int kBackRightDriveMotorPort = 8;

        // TURNING Motor Ports
        public static final int kFrontLeftTurningMotorPort = 3;
        public static final int kBackLeftTurningMotorPort = 1;
        public static final int kFrontRightTurningMotorPort = 5;
        public static final int kBackRightTurningMotorPort = 7;

        // CANCoder Ids
        public static final int kFrontLeftCANCoderId = 6;
        public static final int kBackLeftCANCoderId = 8;
        public static final int kFrontRightCANCoderId = 5;
        public static final int kBackRightCANCoderId = 7;

        // Invert booleans | We use MK4i modules so the turning motors are inverted
        public static final boolean kModuleTurningEncoderReversed = true;
        public static final boolean kModuleDriveEncoderReversed = false;
        public static final boolean kModuleCANCoderReversed = false;
        public static final boolean kGyroReversed = true;

        // Turning encoder offsets
        public static final double kFrontLeftDriveAbsoluteEncoderOffsetRad = Math.toRadians(299.091);
        public static final double kBackLeftDriveAbsoluteEncoderOffsetRad = Math.toRadians(91.054);
        public static final double kFrontRightDriveAbsoluteEncoderOffsetRad = Math.toRadians(316.582);
        public static final double kBackRightDriveAbsoluteEncoderOffsetRad = Math.toRadians(192.7632);

        // Robot speeds
        public static final double kPhysicalMaxSpeedMetersPerSecond = 3.6; // PHYSICAL max speed of the modules (safety cap)
        public static final double kTeleDriveMaxSpeedMetersPerSecond = 1; // Max speed set for teleop

        // Robot turning speeds
        public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;
        public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = kPhysicalMaxAngularSpeedRadiansPerSecond / 4;

        // Robot acceleration
        public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
        public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;
    }

    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
        public static final double kDeadband = 0.05;

        // Joysticks
        public static final int kDriverYAxis = 1;
        public static final int kDriverXAxis = 0;
        public static final int kDriverRotAxis = 4;

        // Buttons
        public static final int kDriverFieldOrientedButtonId = 1;
        public static final int kDriverResetGyroButtonId = 2;
    }

}
