/*package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.command.AutonomousCommand;
import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.IntakeSubsystem;

public class Autonomous extends LinearOpMode {

    DriveSubsystem driveSubsystem;

    IntakeSubsystem intakeSubsystem;
    Telemetry telemetry;

    ArmSubsystem armSubsystem;

    GamepadEx driveGamepad;

    @Override
    public void runOpMode() throws InterruptedException {

        driveSubsystem = new DriveSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        armSubsystem = new ArmSubsystem(hardwareMap, telemetry);
        CommandScheduler.getInstance().registerSubsystem(driveSubsystem, intakeSubsystem);

        while (!isStarted()) {

        }
        CommandScheduler.getInstance().schedule(new AutonomousCommand(driveSubsystem,intakeSubsystem,armSubsystem));
        while (!isStopRequested()&&opModeIsActive()) {
            CommandScheduler.getInstance().run();
        }

        CommandScheduler.getInstance().reset();

    }
}
*/