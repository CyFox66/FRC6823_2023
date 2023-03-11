package frc.robot.commands;

import javax.swing.text.Position;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Pigeon2Handler;
import frc.robot.PositionHandler;
import frc.robot.RobotContainer;
import frc.robot.subsystems.GripperAngleSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import frc.robot.subsystems.PulleySubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class AutoCommandGroup extends SequentialCommandGroup{
    private PulleySubsystem pulley;
    private LiftSubsystem lift;
    private GripperAngleSubsystem gripperAngle;
    private PneumaticSubsystem pneumatic;
    private SwerveDriveSubsystem swerve;
    private Pigeon2Handler pigeon;
    private PositionHandler positionHandler;

    public AutoCommandGroup(RobotContainer container){
        pulley = container.getPulley();
        lift = container.getLift();
        gripperAngle = container.getGripperAngle();
        pneumatic = container.getPneumatics();
        swerve = container.getSwervedriveSubsystem();
        pigeon = container.getPigeon2Handler();
        positionHandler = container.getPositionHandler();

        addCommands(new InstantCommand(() -> positionHandler.setPose(5)), new WaitUntilPose(lift, pulley, gripperAngle));
        addCommands(new InstantCommand(() -> positionHandler.setPose(4)), new WaitUntilPose(lift, pulley, gripperAngle), new WaitCommand(1), new InstantCommand(() -> pneumatic.togglePneumaticState()));
        addCommands(new WaitCommand(1), new InstantCommand(() -> positionHandler.setPose(2)), new WaitUntilPose(lift, pulley, gripperAngle));
        addCommands(new WaitCommand(1), new Reverse(swerve, pigeon));
        addCommands(new WaitCommand(1), new InstantCommand(() -> positionHandler.setPose(0)), new WaitUntilPose(lift, pulley, gripperAngle));
        //addCommands(new WaitCommand(0.5), new Unbalance(pigeon, swerve), new Rebalance(pigeon, swerve));
        addCommands(new WaitCommand(15)); 
    }
}