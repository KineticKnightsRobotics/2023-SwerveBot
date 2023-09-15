package frc.robot.subsystems;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.DriveConstants.*;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

import com.kauailabs.navx.frc.AHRS;
import com.swervedrivespecialties.swervelib.Mk4ModuleConfiguration;
import com.swervedrivespecialties.swervelib.Mk4iSwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SwerveModule;


//TODO: Documentation

public class Drive extends SubsystemBase {
    public static SwerveModule m_frontLeft;
    public static SwerveModule m_frontRight;
    public static SwerveModule m_backLeft;
    public static SwerveModule m_backRight;

    ShuffleboardTab drive_Tab = Shuffleboard.getTab("DriveTrain");

    public static AHRS m_Gyro = new AHRS(SPI.Port.kMXP);

    public static SwerveDriveKinematics m_chassisKinematics = new SwerveDriveKinematics(
        // Front left
        new Translation2d(DriveConstants.TrackWidth / 2.0, DriveConstants.TrackLength / 2.0),
        // Front right
        new Translation2d(DriveConstants.TrackWidth / 2.0, -DriveConstants.TrackLength / 2.0),
        // Back left
        new Translation2d(-DriveConstants.TrackWidth / 2.0, DriveConstants.TrackLength / 2.0),
        // Back right
        new Translation2d(-DriveConstants.TrackWidth / 2.0, -DriveConstants.TrackLength / 2.0)
    );

    public static ChassisSpeeds m_ChassisSpeeds = new ChassisSpeeds(0.0,0.0,0.0);

    public Drive() {
        m_frontLeft = Mk4iSwerveModuleHelper.createNeo(
            drive_Tab.getLayout("Front Left Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(0, 0),
            Mk4iSwerveModuleHelper.GearRatio.L1, 
            FrontLeftModule.D_MotorID, 
            FrontLeftModule.R_MotorID, 
            FrontLeftModule.CancoderID, 
            FrontLeftModule.CancoderOffset
        );
        m_frontRight = Mk4iSwerveModuleHelper.createNeo(
            drive_Tab.getLayout("Front Right Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(2, 0),
            Mk4iSwerveModuleHelper.GearRatio.L1, 
            FrontRightModule.D_MotorID,
            FrontRightModule.R_MotorID,
            FrontRightModule.CancoderID,
            FrontRightModule.CancoderOffset
        );
        m_backLeft = Mk4iSwerveModuleHelper.createNeo(
            drive_Tab.getLayout("Back Left Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(4, 0),
            Mk4iSwerveModuleHelper.GearRatio.L1, 
            BackLeftModule.D_MotorID,
            BackLeftModule.R_MotorID,
            BackLeftModule.CancoderID,
            BackLeftModule.CancoderOffset
        );
        m_backRight = Mk4iSwerveModuleHelper.createNeo(
            drive_Tab.getLayout("Back Right Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(6, 0),
            Mk4iSwerveModuleHelper.GearRatio.L1,
            BackRightModule.D_MotorID,
            BackRightModule.R_MotorID,
            BackRightModule.CancoderID,
            BackRightModule.CancoderOffset
        );
    }
    public void resetGyro(){
        m_Gyro.zeroYaw();
    }
    public Rotation2d getGyroRotation(){
        return Rotation2d.fromDegrees(360 - m_Gyro.getYaw());
    }
    public void setChassisSpeed(ChassisSpeeds chassisSpeeds){
        m_ChassisSpeeds = chassisSpeeds;
    }
    @Override
    public void periodic(){
        SwerveModuleState[] states = m_chassisKinematics.toSwerveModuleStates(m_ChassisSpeeds);
        SwerveDriveKinematics.desaturateWheelSpeeds(states,DriveConstants.MaxChassisSpeed);


        m_frontLeft.set(states[0].speedMetersPerSecond / DriveConstants.MaxChassisSpeed * DriveConstants.MaxVoltage, states[0].angle.getRadians());
        m_frontRight.set(-states[1].speedMetersPerSecond / DriveConstants.MaxChassisSpeed * DriveConstants.MaxVoltage, states[1].angle.getRadians());
        m_backLeft.set(states[2].speedMetersPerSecond / DriveConstants.MaxChassisSpeed * DriveConstants.MaxVoltage, states[2].angle.getRadians());
        m_backRight.set(-states[3].speedMetersPerSecond / DriveConstants.MaxChassisSpeed * DriveConstants.MaxVoltage, states[3].angle.getRadians());
    }
}