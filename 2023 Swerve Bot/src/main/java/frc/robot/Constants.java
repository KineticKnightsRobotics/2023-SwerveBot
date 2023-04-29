// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
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
  public static class DriverConstants {
    public static final int DriverControllerPort = 0;
    public static final int DriverX = 0;
    public static final int DriverY = 0;
    public static final int DriverZ = 0;

    public static final int drA = 2;
    public static final int drB = 2;
    public static final int drY = 4;
    public static final int drX = 3;
    public static final int drLB = 5;
    public static final int drRB = 6;
  }

  public static class ModuleConstants {
    public final static double wheelDiameter = Units.inchesToMeters(0);
    public final static double driveGearRatio = 1 / 1;                                    // NEEDS TO BE CALCULATED!!!!
    public final static double turnGearRatio = 1 / 1;

    //rotational data
    public final static double DriveEnc2Meters = driveGearRatio * Math.PI * wheelDiameter; // How many encoder units are 1 meter? Needed for odometry.
    public final static double TurnEnc2Rad =  turnGearRatio * Math.PI * 2;
    public final static double kPTuning = 0.5; //This number changes the function of the PID controller that the wheel turning uses, needs to be tuned to driver's liking.
  }
  public static class DriveConstants {
    public final static double driveTrainWidth = Units.inchesToMeters(0); //Distance Between Left and Right Wheels
    public final static double driveTrainLength= Units.inchesToMeters(0); //Distance Between Front and Back Wheels

    public static final SwerveDriveKinematics driveKinematics = new SwerveDriveKinematics(
      new Translation2d(driveTrainLength/2,-driveTrainWidth/2), //distance from center of the robot to the wheels
      new Translation2d(driveTrainLength/2,driveTrainWidth/2),
      new Translation2d(-driveTrainLength/2,-driveTrainWidth/2),
      new Translation2d(-driveTrainLength/2,driveTrainWidth/2)
    );


    public final static double maxVelocity = 0; //The physical max speed of the robot, as if all drive motors are running in the same direction at 100% output
    public final static double maxTurnSpeed= 0;//!!!! MEASURED IN radians/second          //The physical max rotational velocity of the robot, as if all turning motors are running at 100% output.

    public final static double velocityLimit = 0;     //The soft cap on driving speed
    public final static double turningSpeedLimit = 0; //The soft cap on tunring speed

    /*
     * FL = Front Left
     * FR = Front Right
     * BL = Back Left
     * BR = Back Right
     * 
     * 
     * d = drive
     * t = turn
     *  IE: FL_dID = front left, drive motor, id
     */
    public final static int FL_dMotor_ID = 0;
    public final static int FR_dMotor_ID = 0;     //Driving MOTOR IDS
    public final static int BL_dMotor_ID = 0;
    public final static int BR_dMotor_ID = 0;

    public final static int FL_tMotor_ID = 0;
    public final static int FR_tMotor_ID = 0;     //TURNING MOTOR IDS
    public final static int BL_tMotor_ID = 0;
    public final static int BR_tMotor_ID = 0;

    public final static boolean FL_dMotor_Reversed = false;
    public final static boolean FR_dMotor_Reversed = false;     //Are any motors turning the wrong way? Set the coresponding variable = 'true'
    public final static boolean BL_dMotor_Reversed = false;
    public final static boolean BR_dMotor_Reversed = false;

    public final static boolean FL_tMotor_Reversed = false;
    public final static boolean FR_tMotor_Reversed = false;  
    public final static boolean BL_tMotor_Reversed = false;
    public final static boolean BR_tMotor_Reversed = false;

    public final static boolean FL_dEnc_Reversed = false;
    public final static boolean FR_dEnc_Reversed = false;     //Are any encoders counting the wrong way? Set the coresponding variable = 'true'
    public final static boolean BL_dEnc_Reversed = false;
    public final static boolean BR_dEnc_Reversed = false;

    public final static boolean FL_tEnc_Reversed = false;
    public final static boolean FR_tEnc_Reversed = false;
    public final static boolean BL_tEnc_Reversed = false;
    public final static boolean BR_tEnc_Reversed = false;

    public final static int FL_absEncoder_ID = 0;
    public final static int FR_absEncoder_ID = 0;     //ABSOLUTE ENCODER IDs
    public final static int BL_absEncoder_ID = 0;
    public final static int BR_absEncoder_ID = 0;

    public final static double FL_absEncoder_offset = 0; //Offset of absolute encoder in Radians
    public final static double FR_absEncoder_offset = 0;
    public final static double BL_absEncoder_offset = 0;
    public final static double BR_absEncoder_offset = 0;

    public final static boolean FL_absEncoder_Reversed = false;
    public final static boolean FR_absEncoder_Reversed = false;
    public final static boolean BL_absEncoder_Reversed = false;
    public final static boolean BR_absEncoder_Reversed = false;
  }
  
}
