package frc.robot.commands;

import frc.robot.subsystems.Drive;
import frc.robot.RobotContainer;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class JoystickDrive extends CommandBase {
    private final Drive m_subsystem;
    public JoystickDrive(Drive drive) {
        m_subsystem = drive;
        addRequirements(drive);
    }
    @Override
    public void execute(){
        double translateX = RobotContainer.DriverLeftStickX();
        double translateY = RobotContainer.DriverLeftStickY();
        double steer      = RobotContainer.DriverRightStickX();

        translateX *= 0.1;
        translateY *= 0.1;
        steer *=      0.1;

        m_subsystem.setChassisSpeed(
            ChassisSpeeds.fromFieldRelativeSpeeds(
                translateX,
                translateY,
                steer,
                m_subsystem.getGyroRotation()
            )
        );
        SmartDashboard.putNumber("Joystick Translate X", translateX);
        SmartDashboard.putNumber("Joystick Translate Y", translateY);
        SmartDashboard.putNumber("Steer", steer);
    }
    @Override
    public void end(boolean interrupted){
        m_subsystem.setChassisSpeed(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}
