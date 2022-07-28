package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Collect;
import frc.robot.commands.LauncherFire;
import frc.robot.commands.LauncherToLimit;
import frc.robot.commands.TankDrive;
import frc.robot.constants.Constants;
import frc.robot.constants.JoystickConstants;
import frc.robot.constants.RobotMap;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Shifty;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;



/* TODO: This stuff:
 * verify pwm ids (thanks electrical)
 * verify CAN ids (ELECTRICAL)
 * verify PCM ids (ELECTRICAL)
 * get some values from the encoder
 * add encoder angle stuff
 * verify shifting and deploying "inverts"
 * tune release angle
 * tune launcher powers
*/



public class RobotContainer {
   //subsystems
   private final Drivetrain drivetrain = new Drivetrain();
   private final Shifty shifty = new Shifty();
   private final Collector collector = new Collector();
   private final Launcher launcher = new Launcher();
   
   //controllers
   private final Joystick leftDrive = new Joystick(JoystickConstants.Ports.DRIVER_LEFT);
   private final Joystick rightDrive = new Joystick(JoystickConstants.Ports.DRIVER_RIGHT);
   private final XboxController copilot = new XboxController(JoystickConstants.Ports.COPILOT);

   Compressor pcmCompressor = new Compressor(RobotMap.PCM, PneumaticsModuleType.CTREPCM);

   public RobotContainer() {
      configureDefaultCommands();
      configureButtonBindings();
      pcmCompressor.enableDigital();
   }

   private void configureButtonBindings() {
      (new JoystickButton(copilot, JoystickConstants.XboxController.BUTTON_A)).whenPressed(
         new SequentialCommandGroup(
            new LauncherFire(launcher, collector),
            new LauncherToLimit(launcher, collector)
         )
      );
      (new JoystickButton(copilot, JoystickConstants.XboxController.BUTTON_B)).whenPressed(new InstantCommand(shifty::toggleShift, shifty));
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

   public Command getAutonomousCommand() { return null; }
}
