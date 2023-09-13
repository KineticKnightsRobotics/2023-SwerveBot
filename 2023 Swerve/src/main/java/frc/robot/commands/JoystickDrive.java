package frc.robot.commands;

import frc.robot.subsystems.Drive;
import frc.robot.RobotContainer;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
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
        double translateY = RobotContainer.DriverRightStickX();
        double steer      = RobotContainer.DriverRightStickX();

        m_subsystem.setChassisSpeed(
            ChassisSpeeds.fromFieldRelativeSpeeds(
                translateX,
                translateY,
                steer,
                m_subsystem.getGyroRotation()
            )
        );
    }
    @Override
    public void end(boolean interrupted){
        m_subsystem.setChassisSpeed(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}
