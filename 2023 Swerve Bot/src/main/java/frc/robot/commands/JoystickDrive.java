package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.SwerveModule;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;

public class JoystickDrive extends CommandBase{
    private final Drive m_subsystem;

    public JoystickDrive(Drive subsystem) {
        m_subsystem = subsystem;
        addRequirements(subsystem);
    }
    @Override
    public void initialize() {

    }
    @Override
    public void execute() {
        //get joystick
        double speedx = RobotContainer.drvX();
        double speedy = RobotContainer.drvY(); //get controller axises
        double speedz = RobotContainer.drvZ();

        if (Math.abs(speedx) < 0.05) {speedx = 0;}
        if (Math.abs(speedy) < 0.05) {speedy = 0;} // apply deadzone
        if (Math.abs(speedz) < 0.05) {speedz = 0;}

        ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(speedx, speedy, speedz, m_subsystem.getRobotDirection());

        SwerveModuleState[] moduleStates = DriveConstants.driveKinematics.toSwerveModuleStates(chassisSpeeds);

        m_subsystem.setModules(moduleStates);

    }
     @Override
    public void end(boolean interrupted) {
        m_subsystem.stopModules();
    }
    @Override
    public boolean isFinished() {
      return false;
     }

}

