package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Voltage extends SubsystemBase {
    private ShuffleboardTab demoTab = Shuffleboard.getTab("demo");

    private NetworkTableEntry voltageEntry = demoTab.add("Voltage", 0).getEntry();

    private AnalogInput voltageSensor = new AnalogInput(0);
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
