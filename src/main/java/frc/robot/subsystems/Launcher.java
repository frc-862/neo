package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import frc.robot.constants.RobotMap;

public class Launcher extends SubsystemBase {
    private Talon left1 = new Talon(RobotMap.LAUNCHER_LEFT1);
    private Talon left2 = new Talon(RobotMap.LAUNCHER_LEFT2);
    private Talon right1 = new Talon(RobotMap.LAUNCHER_RIGHT1);
    private Talon right2 = new Talon(RobotMap.LAUNCHER_RIGHT2);    
    private DigitalInput launcherSwitch = new DigitalInput(RobotMap.LAUNCHER_SWITCH);
    private Encoder launcherEncoder = new Encoder(RobotMap.LAUNCHER_ENCODER1, RobotMap.LAUNCHER_ENCODER2);

    public Launcher() {
        left2.setInverted(Constants.LAUNCHER_LEFT1_INVERT);
        left1.setInverted(Constants.LAUNCHER_LEFT2_INVERT);
        right1.setInverted(Constants.LAUNCHER_RIGHT1_INVERT);
        right2.setInverted(Constants.LAUNCHER_RIGHT2_INVERT);

        launcherEncoder.setReverseDirection(Constants.LAUNCHER_ENCODER_INVERT);
        launcherEncoder.getDecodingScaleFactor();
        launcherEncoder.setDistancePerPulse(Constants.LAUNCHER_ENCODER_SCALE);

        CommandScheduler.getInstance().registerSubsystem(this);
        
    }

    @Override
    public void periodic() {
        if (getLimitSwitch()){
            launcherEncoder.reset();
        }
    }

    public void setPower(double power) {
        left1.set(power);
        left2.set(power);
        right1.set(power);
        right2.set(power);
    }

    public double getAngle(){
        return launcherEncoder.getDistance();
    }

    public boolean getLimitSwitch() {
        return launcherSwitch.get();
    }

    public void resetEncoder() {
        launcherEncoder.reset();
    }

    public void stop() {
        setPower(0d);
    }
}
