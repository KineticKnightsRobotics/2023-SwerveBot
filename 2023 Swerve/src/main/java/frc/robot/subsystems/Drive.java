package frc.robot.subsystems;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.SwerveConstants.*;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import com.kauailabs.navx.frc.AHRS;


//TODO: Documentation

public class Drive extends SubsystemBase {
    public static SwerveModule m_frontLeft = new SwerveModule(
        "Front Left",
        FrontLeftModule.D_MotorID,
        FrontLeftModule.R_MotorID,
        FrontLeftModule.CancoderID,
        FrontLeftModule.CancoderOffset,
        FrontLeftModule.DriveInverted,
        FrontLeftModule.TurnInverted,
        FrontLeftModule.EncoderInverted
    );
    public static SwerveModule m_frontRight = new SwerveModule(
        "Front Right",
        FrontRightModule.D_MotorID,
        FrontRightModule.R_MotorID,
        FrontRightModule.CancoderID,
        FrontRightModule.CancoderOffset,
        FrontRightModule.DriveInverted,
        FrontRightModule.TurnInverted,
        FrontRightModule.EncoderInverted
    );
    public static SwerveModule m_backLeft = new SwerveModule(
        "Back Left",
        BackLeftModule.D_MotorID,
        BackLeftModule.R_MotorID,
        BackLeftModule.CancoderID,
        BackLeftModule.CancoderOffset,
        BackLeftModule.DriveInverted,
        BackLeftModule.TurnInverted,
        BackLeftModule.EncoderInverted
    );
    public static SwerveModule m_backRight = new SwerveModule(
        "Back Right",
        BackRightModule.D_MotorID,
        BackRightModule.R_MotorID,
        BackRightModule.CancoderID,
        BackRightModule.CancoderOffset,
        BackRightModule.DriveInverted,
        BackRightModule.TurnInverted,
        BackRightModule.EncoderInverted
    );

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
        
    }
}