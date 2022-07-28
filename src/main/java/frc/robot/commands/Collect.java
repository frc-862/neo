package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Collector;

public class Collect extends CommandBase {
    private Collector collector;
    private DoubleSupplier power;
  
    public Collect(Collector collector, DoubleSupplier power) {
        this.collector = collector;
        this.power = power;
        addRequirements(collector); 
    }

    @Override
    public void execute() {
        collector.setDeployed(power.getAsDouble() != 0);
        collector.setPower(power.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        collector.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
