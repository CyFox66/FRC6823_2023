package frc.robot.util;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Constants {
    // Default units: Meters (m/s, m/s/s), Degrees 
    // Assume default unless written otherwise
    // "Forward" (on boot) - +X axis
    // "Left" (on boot) - +Y axis

    public static boolean isRed = NetworkTableInstance.getDefault().getTable("FMSInfo").getEntry("IsRedAlliance").getBoolean(true);


    public static final double degToRad = Math.PI / 180;
    public static final double radToDeg = 180 / Math.PI;
    public static final double fLOffset = 221.66015625;
    public static final double fROffset = 205.6640450656414;
    public static final double bLOffset = 323.173828125;
    public static final double bROffset = 238.798828125;

    public static final double L1_RATIO = 8.14;
    public static final double L2_RATIO = 6.75;
    public static final double L3_RATIO = 6.12;
    public static final double STEER_RATIO = 150.0/7.0;
    public static final double WHEEL_CIRCUMFERENCE = 0.31918581324; 

    public static final double DRIVE_TRAIN_WIDTH = 0.5334; // left to right size of drivetrain
    public static final double DRIVE_TRAIN_LENGTH = 0.6858; // front to back size of drivetrain

    public static final double ELEVATOR_MIN = -0.2;
    public static final double ELEVATOR_MAX = 0;
    public static final double EXTENSION_MIN = -0.5;
    public static final double EXTENSION_MAX = -105;

    public static final double kMaxVelocity = 6380 * WHEEL_CIRCUMFERENCE / (60.0 * L2_RATIO);
    public static final double kMaxAccel = kMaxVelocity;
    public static final double kMaxAngularVelocity = kMaxVelocity / Math.hypot(DRIVE_TRAIN_LENGTH / 2.0, DRIVE_TRAIN_WIDTH / 2.0);
    public static final double kMaxAngularAccel = kMaxAngularVelocity;
    public static final TrapezoidProfile.Constraints kTurnControlConstraints = new TrapezoidProfile.Constraints(kMaxAngularVelocity, kMaxAngularAccel);
    public static final SupplyCurrentLimitConfiguration kdriveCurrentLimit = new SupplyCurrentLimitConfiguration(true, 60, 60, 0);
    
    //PID taken from Sonic Squirrels
    //Re-test with full bot
    public static final double kP = 2.2941;//2.2941
    public static final double kI = 0;
    public static final double kD = 0;
    public static final double kA = 0.435;
    public static final double kV = 2.344;
    public static final double kS = 0.628;

    public static final double kPThetaController = 2.0; //1.5;
    public static final double kIThetaController = 0.0;
    public static final double kDThetaController = 0.0;

    //PID setpoint constants for LineUp
    public static final double[] leftScore = new double[]{2, 0.6, 0};
    public static final double[] rightScore = new double[]{2, -0.6, 0};
    public static final double[] centerScore = new double[]{2, 0, 0};
    public static final double[] pickup = new double[]{1, 0.3, 0};

    public static final double[] highScorePose = new double[] {-0.093505859375,-105,0.369140625};
    public static final double[] lowScorePose = new double[] {-0.0906982421875,-40.6662483215332,0.3717041015625};
    public static final double[] pickupPose = new double[] {0,0,0};
    public static final double[] floorPose = new double[] {-0.2044677734375,-40.35673141479492,0.26806640625};
    public static final double[] startPose = new double[] {0,-15,0};

    public static final double[] node1Pose = new double[] {0,0};
    public static final double[] node2Pose = new double[] {0,0};
    public static final double[] node3Pose = new double[] {0,0};
    public static final double[] node4Pose = new double[] {0,0};
    public static final double[] node5Pose = new double[] {0,0};
    public static final double[] node6Pose = new double[] {0,0};
    public static final double[] node7Pose = new double[] {0,0};
    public static final double[] node8Pose = new double[] {0,0};
    public static final double[] node9Pose = new double[] {0,0};
}