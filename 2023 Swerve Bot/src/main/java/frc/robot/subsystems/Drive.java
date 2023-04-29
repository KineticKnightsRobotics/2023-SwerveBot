// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.SPI;

import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ModuleConstants;

public class Drive extends SubsystemBase {

  //Create Modules

  private final SwerveModule frontLeft = new SwerveModule(
    DriveConstants.FL_dMotor_ID,
    DriveConstants.FL_tMotor_ID,
    DriveConstants.FL_dMotor_Reversed,
    DriveConstants.FL_tMotor_Reversed,
    DriveConstants.FL_absEncoder_ID,
    DriveConstants.FL_absEncoder_offset,
    DriveConstants.FL_absEncoder_Reversed
  );
  private final SwerveModule frontRight = new SwerveModule(
    DriveConstants.FR_dMotor_ID,
    DriveConstants.FR_tMotor_ID,
    DriveConstants.FR_dMotor_Reversed,
    DriveConstants.FR_tMotor_Reversed,
    DriveConstants.FR_absEncoder_ID,
    DriveConstants.FR_absEncoder_offset,
    DriveConstants.FR_absEncoder_Reversed
  );
  private final SwerveModule backLeft = new SwerveModule(
    DriveConstants.BL_dMotor_ID,
    DriveConstants.BL_tMotor_ID,
    DriveConstants.BL_dMotor_Reversed,
    DriveConstants.BL_tMotor_Reversed,
    DriveConstants.BL_absEncoder_ID,
    DriveConstants.BL_absEncoder_offset,
    DriveConstants.BL_absEncoder_Reversed
  );
  private final SwerveModule backRight = new SwerveModule(
    DriveConstants.BR_dMotor_ID,
    DriveConstants.BR_tMotor_ID,
    DriveConstants.BR_dMotor_Reversed,
    DriveConstants.BR_tMotor_Reversed,
    DriveConstants.BR_absEncoder_ID,
    DriveConstants.BR_absEncoder_offset,
    DriveConstants.BR_absEncoder_Reversed
  );

  private final AHRS gyro = new AHRS(SPI.Port.kMXP);

  public Drive() {
    //Note: Figure out a way to sleep for 1 second before reseting gyro
    zeroGyro();

  }

  public void zeroGyro() {
    gyro.reset();
  }

  public Rotation2d getRobotDirection() {
    return Rotation2d.fromDegrees(Math.IEEEremainder(gyro.getAngle(), 360));
  }

  public void setModules(SwerveModuleState[] states) {
    SwerveDriveKinematics.desaturateWheelSpeeds(states, DriveConstants.maxVelocity);
    frontLeft.setState(states[0]);
    frontRight.setState(states[1]);
    backLeft.setState(states[2]);
    backRight.setState(states[3]);
  }
  public void stopModules() {
    frontLeft.stop();
    frontRight.stop();
    backLeft.stop();
    backRight.stop();
  }

}
