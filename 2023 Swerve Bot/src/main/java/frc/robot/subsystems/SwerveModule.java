package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.math.kinematics.SwerveModuleState;

import frc.robot.Constants.ModuleConstants;
import frc.robot.Constants.DriveConstants;

public class SwerveModule {
    
    // Variables associated with the Swerve Module Class.
    //motor objects
    private final CANSparkMax dMotor;   // henceforth; d = drive, t = turning.
    private final CANSparkMax tMotor;
    //encoder objects
    private final RelativeEncoder dEncoder;
    private final RelativeEncoder tEncoder;
    //pid controller
    private final PIDController tPIDController;
    //absolute encoder
    private final AnalogInput absoluteEncoder;
    private final boolean absoluteEncoderReveresed;
    private final double absoluteEncoderOffset; //measured in radians

    public SwerveModule ( 
        int driveMotorID, 
        int turnMotorID, 
        boolean driveMotorReversed, 
        boolean turningMotorReversed, 
        int absoluteEncoderID, 
        double absoluteEncoderOffset, 
        boolean absoluteEncoderReveresed
    ) {
        this.absoluteEncoderOffset = absoluteEncoderOffset;                             //this is a contstructor method. it is the initialization of the swerve module object.
        this.absoluteEncoderReveresed = absoluteEncoderReveresed;                         //it runs on the creation of the object.

        absoluteEncoder = new AnalogInput(absoluteEncoderID);

        dMotor = new CANSparkMax(driveMotorID, MotorType.kBrushless);
        tMotor = new CANSparkMax(turnMotorID, MotorType.kBrushless);

        dEncoder = dMotor.getEncoder();
        tEncoder = tMotor.getEncoder(); //create encoder objects

        dMotor.setInverted(driveMotorReversed);
        tMotor.setInverted(turningMotorReversed);  //Check to see if any motors are inverted.
        
        tPIDController = new PIDController(ModuleConstants.kPTuning, 0, 0); // Should not need to alter the integral and derivitave of the controller.

        tPIDController.enableContinuousInput(0, Math.PI * 2);
        //This tells the PID Controller that the value loops back around to 0 at 2PI (1 full turn of a circle in radians)

        dMotor.restoreFactoryDefaults();
        tMotor.restoreFactoryDefaults();

        dMotor.setSmartCurrentLimit(30);
        tMotor.setSmartCurrentLimit(30);
    }
    public void ResetEncoders() {
        dEncoder.setPosition(0);
        tEncoder.setPosition(getAbsoluteEncoder());
    }
    public double getDrivePosition() {
        return dEncoder.getPosition();
    }
    public double getTurnPosition() {
        return tEncoder.getPosition();
    }
    public double getDriveVelocity() {
        return dEncoder.getVelocity();
    }
    public double getTurnVelocity() {
        return tEncoder.getVelocity();
    }
    /**
    * angle = absoluteEncoder.getVoltage() / RobotController.getVoltage5V();         
    * 
    *    Returns the gradient of the encoder (Percentage of a circle)
    * 
    * @return Absolute Encoder Value, in Radians
    */
    public double getAbsoluteEncoder() {
        double angle = absoluteEncoder.getVoltage() / RobotController.getVoltage5V(); //This is some wacky stuff I dont really understand, but this is how you read the absolute encoder value according to cheif delphi - Danny
        angle *= 2 * Math.PI; //Convert to RADs
        angle -= absoluteEncoderOffset; // Subtract offset to get real angle
        if (absoluteEncoderReveresed) {angle *= -1;} //Multiply angle by -1 if the encoder is set to reversed.
        return angle;
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(getDriveVelocity(), new Rotation2d(getTurnPosition()));
    }


    public void setState(SwerveModuleState target) {
        target = SwerveModuleState.optimize(target, getState().angle);


        if (Math.abs(target.speedMetersPerSecond) < 0.05) {
            dMotor.set(target.speedMetersPerSecond / DriveConstants.maxVelocity); // Sets the value of the drive motor to target speed adjusted to be within the maximum velocity.
            tMotor.set(tPIDController.calculate(getTurnPosition(), target.angle.getRadians() )); //Sets the turning motor to the value calculated by the PID Controller
        }
        else {stop();} 
        // deadzone 

    }

    public void stop() {
        dMotor.set(0);
        tMotor.set(0);
    }

    public void driveMotor (double speed){
        dMotor.set(speed);
    }
    public void turnMotor (double speed){
        tMotor.set(speed);
    }
    public void zeroRelativeEncoder (){
        tEncoder.setPosition(0);
    }

}
