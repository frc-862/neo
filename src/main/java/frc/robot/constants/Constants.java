package frc.robot.constants;

public final class Constants {
    //DRIVETRAIN
    public static final boolean DRIVE_LEFT1_INVERT = false;
    public static final boolean DRIVE_LEFT2_INVERT = false;
    public static final boolean DRIVE_RIGHT1_INVERT = true;
    public static final boolean DRIVE_RIGHT2_INVERT = true;
    public static final double  DRIVE_DEADBAND = 0.2;
    public static final double DEFAULT_SPEED_CAP =  0.8;

    //COLLECTOR
    public static final boolean COLLECTOR_INVERT = true;
    public static final double  COLLECTOR_DEADBAND = 0.05;

    //LAUNCHER
    //INVERTS
    public static final boolean LAUNCHER_LEFT1_INVERT = true;
    public static final boolean LAUNCHER_LEFT2_INVERT = true;
    public static final boolean LAUNCHER_RIGHT1_INVERT = false;
    public static final boolean LAUNCHER_RIGHT2_INVERT = false;

    //ENCODER
    public static final int LAUNCHER_ENCODER_TICKS = 360;
    public static final double LAUNCHER_GEAR_RATIO = 12/120; //12:120 gear ratio
    public static final boolean LAUNCHER_ENCODER_INVERT = false;

    //SHOOTING
    public static final double  LAUNCHER_RELEASE_ANGLE = 690d;
    public static final double  LAUNCHER_FIRE_POWER = 1;
    public static final double  LAUNCHER_RETURN_POWER = -0.2;
}
