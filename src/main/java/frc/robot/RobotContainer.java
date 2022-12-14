package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.*;
import frc.robot.constants.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
   //subsystems
   private final Drivetrain drivetrain = new Drivetrain();
   private final Shifty shifty = new Shifty();
   private final Collector collector = new Collector();
   private final Launcher launcher = new Launcher();
   private final Voltage voltage = new Voltage(); // read only
   
   //controllers
   private final Joystick leftDrive = new Joystick(JoystickConstants.Ports.DRIVER_LEFT);
   private final Joystick rightDrive = new Joystick(JoystickConstants.Ports.DRIVER_RIGHT);
   private final XboxController copilot = new XboxController(JoystickConstants.Ports.COPILOT);

   Compressor pcmCompressor = new Compressor(RobotMap.PCM, PneumaticsModuleType.CTREPCM);

   public RobotContainer() {
      configureDefaultCommands();
      configureButtonBindings();
      pcmCompressor.enableDigital();

      initializeDashboardCommands();
   }

   private void configureButtonBindings() {
      (new JoystickButton(copilot, JoystickConstants.XboxController.BUTTON_A)).whenPressed(
         new SequentialCommandGroup(
            new CollectorDeploy(collector),
            new WaitCommand(1),
            new LauncherFire(launcher, collector),
            new LauncherToLimit(launcher, collector)
         ), false
      );
   }

   private void configureDefaultCommands() {
      drivetrain.setDefaultCommand(
         new TankDrive(
            drivetrain, 
            () -> MathUtil.applyDeadband(-leftDrive.getY(),  Constants.DRIVE_DEADBAND),
            () -> MathUtil.applyDeadband(-rightDrive.getY(), Constants.DRIVE_DEADBAND)
         )
      );

      collector.setDefaultCommand(
         new Collect(
            collector,
            () -> MathUtil.applyDeadband(
               copilot.getRightTriggerAxis()-copilot.getLeftTriggerAxis(), 
               Constants.COLLECTOR_DEADBAND
            )
         )
      );
   }

    protected void initializeDashboardCommands() { 
          var tab = Shuffleboard.getTab("demo");

          tab.add(new InstantCommand(shifty::toggleShift, shifty));
    }

   public Command getAutonomousCommand() { return null; }
}
