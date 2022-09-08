package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotMap;

public class Voltage extends SubsystemBase {
    private ShuffleboardTab demoTab = Shuffleboard.getTab("demo");

    private NetworkTableEntry voltageEntry = demoTab.add("Voltage", 0).getEntry();

    private AnalogInput voltageSensor = new AnalogInput(RobotMap.VOLTAGE);
    public Voltage() {
        CommandScheduler.getInstance().registerSubsystem(this);
    }

    @Override
    public void periodic() {
        voltageEntry.setDouble(getVoltage());
    }

    public double getVoltage(){
        return voltageSensor.getVoltage() * 3;
    }
}
