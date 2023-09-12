package frc.robot.commands;

import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class JoystickDrive extends CommandBase {
    private final Drive m_subsystem;
    public JoystickDrive(Drive drive) {
        m_subsystem = drive;
        addRequirements(drive);
    }
    @Override
    public void initialize(){
        
    }

}
