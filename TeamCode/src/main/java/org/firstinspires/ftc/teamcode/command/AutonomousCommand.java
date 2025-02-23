package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsytem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.FingerSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;
public class AutonomousCommand extends SequentialCommandGroup {
    public AutonomousCommand(DriveSubsystem driveSubsystem, IntakeSubsystem intakeSubsystem,
                             ArmSubsystem armSubsystem, WristSubsystem wristSubsystem, FingerSubsystem fingerSubsystem) {

        addCommands(
                new InstantCommand(()->fingerSubsystem.moveFinger(1)),
                new DriveForTime(driveSubsystem,800,0,0.5,0),
                new AutonomousIntakeCommand(intakeSubsystem,-1),
                new WaitCommand(1000),
                new AutonomousIntakeCommand(intakeSubsystem, 0)
//                new InstantCommand(()-> wristSubsystem.wristRotate(0.5)),v
                //new ArmToSetpoint(armSubsystem, -4000),
//                new InstantCommand(()->armSubsystem.setRunPID(false)),
//                new InstantCommand(()->armSubsystem.setRunPID(true)),
//                new InstantCommand(()-> wristSubsystem.wristRotate(1)),
//                new WaitCommand(2000),
//                new DriveForTime(driveSubsystem,400,0,-1,0),
//                new InstantCommand(()->fingerSubsystem.moveFinger(1))
//                new DriveForTicksCommand(driveSubsystem, 1.0, 0.0, 0.0, 5000.0)

        );

    }

}
// Autonomous died because of the holy duck cult
// all worship the holy duck cult
// death to heretics and chickens