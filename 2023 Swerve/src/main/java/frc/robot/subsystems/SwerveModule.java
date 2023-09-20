package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.SwerveConstants;

import com.ctre.phoenix.sensors.CANCoder;

public class SwerveModule extends SubsystemBase {
    private final CANSparkMax driveMotor;
    private final CANSparkMax turningMotor;

    private final RelativeEncoder driveEncoder;
    private final RelativeEncoder turningEncoder;

    private final PIDController turningPidController;

    private final CANCoder absoluteEncoder;
    private final boolean absoluteEncoderReversed;
    private final double absoluteEncoderOffsetRad;
    public SwerveModule(int driveMotor_ID, int turningMotor_ID, int CancoderID, double absoluteEncoderOffset, boolean driveReversed, boolean turnReversed, boolean encoderReversed) {
        this.driveMotor   = new CANSparkMax(driveMotor_ID, MotorType.kBrushless);
        this.turningMotor = new CANSparkMax(turningMotor_ID, MotorType.kBrushless);
        driveMotor = driveMotor.getEncoder();

    }
}
