package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.command.AutonomousCommand;
import org.firstinspires.ftc.teamcode.subsytem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.FingerSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;
@Autonomous(name = "AutnomousOpMode")
public class AutonomousOpmode extends LinearOpMode {

    DriveSubsystem driveSubsystem;

    IntakeSubsystem intakeSubsystem;

    ArmSubsystem elevatorSubsystem;
    WristSubsystem wristSubsystem;
    FingerSubsystem fingerSubsystem;

    GamepadEx driveGamepad;

    @Override
    public void runOpMode() throws InterruptedException {

        driveSubsystem = new DriveSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        elevatorSubsystem = new ArmSubsystem(hardwareMap, telemetry);
        wristSubsystem = new WristSubsystem(hardwareMap, telemetry);
        fingerSubsystem = new FingerSubsystem(hardwareMap, telemetry);
        CommandScheduler.getInstance().registerSubsystem(driveSubsystem, intakeSubsystem);

        while (!isStarted()) {

        }
        CommandScheduler.getInstance().schedule(new AutonomousCommand(driveSubsystem,intakeSubsystem,elevatorSubsystem,wristSubsystem,fingerSubsystem));
        while (!isStopRequested()&&opModeIsActive()) {
            CommandScheduler.getInstance().run();
        }

        CommandScheduler.getInstance().reset();

    }
}
