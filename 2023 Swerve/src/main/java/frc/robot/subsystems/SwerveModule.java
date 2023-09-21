package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.moduleConstants;
import com.ctre.phoenix.sensors.CANCoder;

public class SwerveModule extends SubsystemBase {
    private final String Name;
    private final CANSparkMax driveMotor;
    private final CANSparkMax turningMotor;

    private final RelativeEncoder driveEncoder;
    private final RelativeEncoder turningEncoder;

    private final PIDController modulePID;

    private final CANCoder absoluteEncoder;
    private final boolean absoluteEncoderReversed;
    private final double absEncOffset;
    public SwerveModule(
            String name,
            int driveMotor_ID, 
            int turningMotor_ID, 
            int CancoderID, 
            double absoluteEncoderOffset, 
            boolean driveReversed, 
            boolean turnReversed, 
            boolean encoderReversed) {
        this.Name = name;
        this.driveMotor   = new CANSparkMax(driveMotor_ID, MotorType.kBrushless);
        this.turningMotor = new CANSparkMax(turningMotor_ID, MotorType.kBrushless);
        this.driveEncoder  = driveMotor.getEncoder();
        this.turningEncoder= turningMotor.getEncoder();
        this.absoluteEncoder = new CANCoder(CancoderID);
        this.absEncOffset = absoluteEncoderOffset;
        this.absoluteEncoderReversed = encoderReversed;
        this.modulePID = new PIDController(0.5, 0.0, 0.0);
        modulePID.enableContinuousInput(-Math.PI, Math.PI);

        
    }

    public void putData2Dashboard(){
        SmartDashboard.putString("Swerve Module " + Name, getState().toString());
        SmartDashboard.putNumber("Module "+Name+" Angle",getModuleAngle());
        
    }

    public double getModuleAngle(){
        return turningEncoder.getPosition();
    }
    public double getAbsoluteEncoder(){
        return (((absoluteEncoder.getPosition()/360)*2*Math.PI) - absEncOffset) * (absoluteEncoderReversed ? -1.0:1.0);
    }
    public SwerveModuleState getState(){
        return new SwerveModuleState(driveEncoder.getVelocity(),new Rotation2d(turningEncoder.getPosition()));
    }
    public void setState(SwerveModuleState desiredState){
        if (Math.abs(desiredState.speedMetersPerSecond)<0.005){
            stop(); return;
        }
        desiredState = SwerveModuleState.optimize(desiredState, new Rotation2d(getModuleAngle()));
        driveMotor.set(desiredState.speedMetersPerSecond/moduleConstants.maxModuleSpeed);
        turningMotor.set(modulePID.calculate(getModuleAngle(),desiredState.angle.getRadians()));
    }
    public void stop(){
        driveMotor.set(0.0);
        turningMotor.set(0.0);
    }
}
