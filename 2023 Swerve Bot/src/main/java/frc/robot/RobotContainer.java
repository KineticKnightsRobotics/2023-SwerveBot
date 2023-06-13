// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static Drive m_drive = new Drive();

  // init driver controller
  public static Joystick drvStick = new Joystick(DriverConstants.DriverControllerPort);
  // driver buttons 
  public static JoystickButton drA = new JoystickButton(drvStick, DriverConstants.drA);
  public JoystickButton drB = new JoystickButton(drvStick, DriverConstants.drB);
  public JoystickButton drX = new JoystickButton(drvStick, DriverConstants.drX);
  public JoystickButton drY = new JoystickButton(drvStick, DriverConstants.drY);
  public JoystickButton drLB = new JoystickButton(drvStick, DriverConstants.drLB);
  public JoystickButton drRB = new JoystickButton(drvStick, DriverConstants.drRB);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_drive.setDefaultCommand(new JoystickDrive(m_drive));
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    drA.whileTrue(new Test_TurnModules(m_drive));
    drB.whileTrue(new Test_ZeroREncoders(m_drive));
    drX.whileTrue(new Test_DefaultPosition(m_drive));
  }

  public Command getAutonomousCommand() {
    return null;
  }

  public static double drvX() {//left joy left and right
    return drvStick.getRawAxis(DriverConstants.DriverX);
  }
  public static double drvY() {//left joystick forward back
    return drvStick.getRawAxis(DriverConstants.DriverY);
  }
  public static double drvZ() {// right joystick left and right
    return drvStick.getRawAxis(DriverConstants.DriverZ);
  }
}
