package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import frc.robot.constants.RobotMap;

public class Drivetrain extends SubsystemBase {
    
	private ShuffleboardTab demoTab = Shuffleboard.getTab("demo");

	private NetworkTableEntry speedCap = demoTab.add("speed cap", Constants.DEFAULT_SPEED_CAP).getEntry();
    
    private Talon left1 = new Talon(RobotMap.LEFT1);
    private Talon left2 = new Talon(RobotMap.LEFT2);
    private Talon right1 = new Talon(RobotMap.RIGHT1);
    private Talon right2 = new Talon(RobotMap.RIGHT2);

    public Drivetrain() {
        left1.setInverted(Constants.DRIVE_LEFT1_INVERT);
        left2.setInverted(Constants.DRIVE_LEFT2_INVERT);
        right1.setInverted(Constants.DRIVE_RIGHT1_INVERT);
        right2.setInverted(Constants.DRIVE_RIGHT2_INVERT);

        CommandScheduler.getInstance().registerSubsystem(this);
    }

    public void setPower(double leftPower, double rightPower){
        leftPower *= speedCap.getDouble(Constants.DEFAULT_SPEED_CAP);
        rightPower *= speedCap.getDouble(Constants.DEFAULT_SPEED_CAP);
        left1.set(leftPower);
        right1.set(rightPower);
        left2.set(leftPower);
        right2.set(rightPower);
    }

    public void stop() {
        setPower(0, 0);
    }
}
