
package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import frc.robot.constants.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Collector extends SubsystemBase {
    private Talon collector = new Talon(RobotMap.COLLECTOR);
    private DigitalInput deploySwitch = new DigitalInput(RobotMap.COLLECTOR_SWITCH);
    private DoubleSolenoid deploy = new DoubleSolenoid(RobotMap.PCM, PneumaticsModuleType.CTREPCM, RobotMap.DEPLOY_1, RobotMap.DEPLOY_2);

    private ShuffleboardTab demoTab = Shuffleboard.getTab("demo");

	private NetworkTableEntry collectorSwitchEntry = demoTab.add("collector switch", false).getEntry();

    public Collector() {
        collector.setInverted(Constants.COLLECTOR_INVERT);

        CommandScheduler.getInstance().registerSubsystem(this);
    }
    
    public void setPower(double power) {
        collector.set(power);
    }

    public void toggleDeploy() {
        setDeployed(!getDeployed());
    }

    //(assume) forward is deployed, reverse isn't
    public void setDeployed(boolean state) {
        if(state) {
            deploy.set(Value.kForward);
        } else {
            deploy.set(Value.kReverse); 
        }
    }
    
    public boolean getDeployed() {
        return deploySwitch.get();
    }

    public void stop() {
        setPower(0d);
    }

@Override
public void periodic() {
    collectorSwitchEntry.setBoolean(getDeployed());
}

}
