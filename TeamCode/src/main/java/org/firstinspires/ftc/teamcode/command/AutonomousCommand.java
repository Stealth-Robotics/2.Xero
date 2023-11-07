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
                new DriveForTime(driveSubsystem,400,0,1,0),
                new InstantCommand(()-> wristSubsystem.wristRotate(0.5)),
                new ArmToSetpoint(armSubsystem, -4000),
                new InstantCommand(()->armSubsystem.setRunPID(false)),
                new InstantCommand(()->armSubsystem.setRunPID(true)),
                new InstantCommand(()-> wristSubsystem.wristRotate(1)),
                new WaitCommand(2000),
                new InstantCommand(()->fingerSubsystem.moveFinger(0.7)),
                new DriveForTime(driveSubsystem,400,0,-1,0),
                new InstantCommand(()->fingerSubsystem.moveFinger(1))

        );

    }

}