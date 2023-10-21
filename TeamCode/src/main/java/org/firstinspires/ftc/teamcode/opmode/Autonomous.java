package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.command.AutonomousCommand;
import org.firstinspires.ftc.teamcode.subsytem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.IntakeSubsystem;

public class Autonomous extends LinearOpMode {

    DriveSubsystem driveSubsystem;

    IntakeSubsystem intakeSubsystem;

    ElevatorSubsystem elevatorSubsystem;

    GamepadEx driveGamepad;

    @Override
    public void runOpMode() throws InterruptedException {

        driveSubsystem = new DriveSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        elevatorSubsystem = new ElevatorSubsystem(hardwareMap);
        CommandScheduler.getInstance().registerSubsystem(driveSubsystem, intakeSubsystem);

        while (!isStarted()) {

        }
        CommandScheduler.getInstance().schedule(new AutonomousCommand(driveSubsystem,intakeSubsystem,elevatorSubsystem));
        while (!isStopRequested()&&opModeIsActive()) {
            CommandScheduler.getInstance().run();
        }

        CommandScheduler.getInstance().reset();

    }
}
