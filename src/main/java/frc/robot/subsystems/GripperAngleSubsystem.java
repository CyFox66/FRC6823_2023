package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GripperAngleSubsystem extends SubsystemBase{
    private final SparkMaxAlternateEncoder.Type kType;
    private final int kCPR = 8192;

    private CANSparkMax angleMotor;
    private RelativeEncoder encoder;
    private PIDController pid;
    private boolean mode; //true is position mode (default), false is velocity mode (driver controlled)
    private double setPoint;
    private double speed;
    private boolean disabled;
    
    public GripperAngleSubsystem () {
        angleMotor = new CANSparkMax(11, MotorType.kBrushed);
        angleMotor.restoreFactoryDefaults();
        kType = SparkMaxAlternateEncoder.Type.kQuadrature;
        encoder = angleMotor.getAlternateEncoder(kType, kCPR);
        mode = false;
        disabled = false;
        setPoint = 0.5;
        speed = 0;

        SendableRegistry.addLW(this, "Pulley");

        pid = new PIDController(5, 0, 0);

        //mode = true;
    }

    public void setMode(boolean mode)
    {
        this.mode = mode;
    }

    public void setSetPoint(double setPoint)
    {
        this.setPoint = setPoint;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public double getPosition()
    {
        return encoder.getPosition();
    }

    public void disable(){
        disabled = true;
    }

    public void enable(){
        disabled = false;
    }

    @Override
    public void periodic()
    {
        if (!disabled){
            if (mode){
                pid.setSetpoint(setPoint);
                angleMotor.set(pid.calculate(getPosition()));
                SmartDashboard.putNumber("Wrist angle", getPosition());
            }
            else{
                angleMotor.set(speed);
                setPoint = getPosition();
            }
        }
        else{
            angleMotor.disable();
        }
    }
}

