// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kLeftXAxisID =0;
    public static final int kLeftYAxisID =1;
    public static final int kRightXAxisID=4;
  }

  public static class DriveConstants {

    public static final double MaxVoltage = 12.0; //Max drive motor voltage.
    public static final double MaxChassisSpeed = 1.0; //Max Chassis speed in M/S

    public static final double TrackWidth = Units.metersToInches(0); //distance from left side to right side of the drive train //TODO: fish this measurements out of the old code
    public static final double TrackLength= Units.metersToInches(0); //distance from front to back of the drive train

    public static final int NavXID = 0;
  }
  public static class moduleConstants{
    public static final double kWheelDiameterMeters = Units.inchesToMeters(4.0);
    public static final double kDriveMotorGearRatio = 8.14 / 1.0; // Drive ratio of 8.14 : 1
    public static final double kTurningMotorGearRatio = 1.0 / (150.0 / 7.0); // Turning ratio of (150 / 7) : 1
    public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters;
    public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
    public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60.0;
    public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60.0;
    
    public static final double maxModuleSpeed = 4.2672;
  }
  public static class SwerveConstants{
    public static class FrontLeftModule {
      public static final int D_MotorID = 8;
      public static final int R_MotorID = 7;
      public static final int CancoderID= 4;
      public static final double CancoderOffset = -Math.toRadians(299.091); //TODO: Measure offsets.
      public static final boolean DriveInverted = false;
      public static final boolean TurnInverted = false;
      public static final boolean EncoderInverted = false;
    }
    public static class FrontRightModule {
      public static final int D_MotorID = 2;
      public static final int R_MotorID = 1;
      public static final int CancoderID= 1;
      public static final double CancoderOffset = -Math.toRadians(316.582);
      public static final boolean DriveInverted = false;
      public static final boolean TurnInverted = false;
      public static final boolean EncoderInverted = false;
    }
    public static class BackLeftModule {
      public static final int D_MotorID = 6;
      public static final int R_MotorID = 5;
      public static final int CancoderID= 2;
      public static final double CancoderOffset = -Math.toRadians(91.054);
      public static final boolean DriveInverted = false;
      public static final boolean TurnInverted = false;
      public static final boolean EncoderInverted = false;
    }
    public static class BackRightModule {
      public static final int D_MotorID = 4;
      public static final int R_MotorID = 3;
      public static final int CancoderID= 3;
      public static final double CancoderOffset = -Math.toRadians(71.542);
      public static final boolean DriveInverted = false;
      public static final boolean TurnInverted = false;
      public static final boolean EncoderInverted = false;
    }
  }
}




