package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.Constants;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Launcher;

public class LauncherFire extends CommandBase {
    private Launcher launcher;
    private Collector collector;

    public LauncherFire(Launcher launcher, Collector collector) {
        this.launcher = launcher;
        this.collector = collector;
        addRequirements(launcher, collector);
    }

    @Override
    public void initialize() {
      collector.setDeployed(true);
      launcher.setPower(Constants.LAUNCHER_FIRE_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        launcher.stop();
    }

    @Override
    public boolean isFinished() {
        return launcher.getAngle() > Constants.LAUNCHER_RELEASE_ANGLE;
    }
}
