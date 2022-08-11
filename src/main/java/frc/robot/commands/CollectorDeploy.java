package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Collector;

public class CollectorDeploy extends CommandBase {

    private Collector collector;

    public CollectorDeploy(Collector collector) {
        this.collector = collector;
        addRequirements(collector);
    }

    @Override
    public void initialize() {
        collector.setDeployed(true);
    }

    @Override   
    public boolean isFinished() {
        return collector.getDeployed();
    }
}
