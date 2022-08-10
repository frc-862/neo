package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotMap;

public class Shifty extends SubsystemBase {
    private DoubleSolenoid shift = new DoubleSolenoid(RobotMap.PCM, PneumaticsModuleType.CTREPCM, RobotMap.SHIFT_1, RobotMap.SHIFT_2);
    
    public Shifty() {
        setShift(false);
        CommandScheduler.getInstance().registerSubsystem(this);
    }
    
    //false is low, true is high
    public void setShift(boolean state) {
        if(state) {
            shift.set(Value.kForward);
        } else {
            shift.set(Value.kReverse);
        }
    }

    public void toggleShift() {
        setShift(shift.get() == Value.kReverse);
    }
}
