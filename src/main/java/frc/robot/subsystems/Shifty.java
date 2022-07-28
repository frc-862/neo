package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotMap;

public class Shifty extends SubsystemBase {
    private DoubleSolenoid leftShift = new DoubleSolenoid(RobotMap.PCM, PneumaticsModuleType.CTREPCM, RobotMap.SHIFT_LEFT1, RobotMap.SHIFT_LEFT2);
    private DoubleSolenoid rightShift = new DoubleSolenoid(RobotMap.PCM, PneumaticsModuleType.CTREPCM, RobotMap.SHIFT_RIGHT1, RobotMap.SHIFT_RIGHT2);
    
    public Shifty() {
        setShift(false);
        CommandScheduler.getInstance().registerSubsystem(this);
    }
    
    //false is low, true is high
    public void setShift(boolean state) {
        if(state) {
            leftShift.set(Value.kForward);
            rightShift.set(Value.kForward);
        } else {
            leftShift.set(Value.kReverse);
            rightShift.set(Value.kReverse);  
        }
    }

    public void toggleShift() {
        setShift(leftShift.get() == Value.kReverse);
    }
}
