package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.command.DefaultArmCommand;
import org.firstinspires.ftc.teamcode.command.DefaultFingerCommand;
import org.firstinspires.ftc.teamcode.command.DefaultIntakeCommand;
import org.firstinspires.ftc.teamcode.command.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.command.DefaultLauncherCommand;
import org.firstinspires.ftc.teamcode.command.DefaultWristCommand;
import org.firstinspires.ftc.teamcode.command.ResetArmCommand;
import org.firstinspires.ftc.teamcode.scaffolding.WristScaffolding;
import org.firstinspires.ftc.teamcode.subsytem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.FingerSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.LauncherSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;
import com.acmerobotics.dashboard.*;

@TeleOp(name = "TeleOp2")
public class TeleopOpmode extends CommandOpMode {

    DriveSubsystem driveSubsystem;
    IntakeSubsystem intakeSubsystem;
    WristSubsystem wristSubsystem;
    ArmSubsystem armSubsystem;
    FingerSubsystem fingerSubsystem;
    DefaultArmCommand defaultArmCommand;
    LauncherSubsystem launcherSubsystem;
    //separate gamepads for movement and driving
    GamepadEx driveGamepad;

    GamepadEx movementGamepad;

    FtcDashboard dashboard = FtcDashboard.getInstance();

    @Override
    public void initialize() {

        telemetry = dashboard.getTelemetry();

        driveGamepad = new GamepadEx(gamepad1);
        movementGamepad = new GamepadEx(gamepad2);

        driveSubsystem = new DriveSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        //these two have telemetry so they can print data to the screen
        wristSubsystem = new WristSubsystem(hardwareMap, telemetry);
        armSubsystem = new ArmSubsystem(hardwareMap, telemetry);
        launcherSubsystem = new LauncherSubsystem(hardwareMap, telemetry);
        fingerSubsystem = new FingerSubsystem(hardwareMap, telemetry);

        register(driveSubsystem);
        register(intakeSubsystem);

        driveSubsystem.setDefaultCommand(new DefaultDriveCommand(driveSubsystem,
                () -> driveGamepad.getRightX(),
                () -> driveGamepad.getRightY(),
                () -> driveGamepad.getLeftX(),
                () -> driveGamepad.getGamepadButton(GamepadKeys.Button.A).get()));
        //Decided to make leftTrigger use the Right Trigger because it felt more natural to use the right trigger for intake
        intakeSubsystem.setDefaultCommand(new DefaultIntakeCommand(intakeSubsystem,
                () -> driveGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER),
                () -> driveGamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)));

        //wristSubsystem.setDefaultCommand(new DefaultArmCommand(armSubsystem, wristSubsystem, () -> movementGamepad.getLeftY()));
        //wristSubsystem.setDefaultCommand(new DefaultWristCommand(wristSubsystem, armSubsystem.getPosition(), defaultArmCommand));

        movementGamepad.getGamepadButton(GamepadKeys.Button.A).whenPressed(new InstantCommand(() -> wristSubsystem.wristRotate(0.6)));
        movementGamepad.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(() -> wristSubsystem.wristRotate(0.8)));
        movementGamepad.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new InstantCommand(() -> wristSubsystem.wristRotate(1)));

        armSubsystem.setDefaultCommand(new DefaultArmCommand(armSubsystem, () -> movementGamepad.getLeftY()));
        driveGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new ResetArmCommand(armSubsystem));

        fingerSubsystem.setDefaultCommand(new DefaultFingerCommand(fingerSubsystem,
                () -> movementGamepad.getButton(GamepadKeys.Button.B),
                () -> driveGamepad.getButton(GamepadKeys.Button.X),
                () -> movementGamepad.getButton(GamepadKeys.Button.Y),
                () -> movementGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)));
        //launcherSubsystem.setDefaultCommand(new DefaultLauncherCommand(launcherSubsystem,()->movementGamepad.getButton(GamepadKeys.Button.A)));
        movementGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new InstantCommand(() -> launcherSubsystem.rotateLever(0.7
        )));
        movementGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new InstantCommand(() -> launcherSubsystem.rotateLever(1)));

    }
}