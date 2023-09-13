// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants;
import frc.robot.Constants.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drive m_Drive = new Drive();

  public static Joystick driverJoystick = new Joystick(Constants.OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }

  public static double DriverLeftStickX(){
    return driverJoystick.getRawAxis(OperatorConstants.kLeftXAxisID);
  }
  public static double DriverLeftStickY(){
    return driverJoystick.getRawAxis(OperatorConstants.kLeftYAxisID);
  }
  public static double DriverRightStickX(){
    return driverJoystick.getRawAxis(OperatorConstants.kRightXAxisID);
  }
}
