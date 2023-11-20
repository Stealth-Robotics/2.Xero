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
import org.firstinspires.ftc.teamcode.command.DefaultWristCommand;
import org.firstinspires.ftc.teamcode.command.FieldDriveCommand;
import org.firstinspires.ftc.teamcode.scaffolding.WristScaffolding;
import org.firstinspires.ftc.teamcode.subsytem.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.FieldCentricDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.FingerSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.LauncherSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.pipelines.PropProcessor;
import org.stealthrobotics.library.Alliance;

import com.acmerobotics.dashboard.*;

@TeleOp(name = "Skibidi Ohio")
public class FieldCentricTeleopOpmode extends CommandOpMode {

    DriveSubsystem driveSubsystem;
    IntakeSubsystem intakeSubsystem;
    WristSubsystem wristSubsystem;
    ArmSubsystem armSubsystem;
    FingerSubsystem fingerSubsystem;
    DefaultArmCommand defaultArmCommand;
    LauncherSubsystem launcherSubsystem;
    FieldCentricDriveSubsystem fieldCentricDriveSubsystem;
    CameraSubsystem cameraSubsystem;
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
        cameraSubsystem = new CameraSubsystem(hardwareMap, Alliance.BLUE);

        register(driveSubsystem);
        register(intakeSubsystem);

        driveSubsystem.setDefaultCommand(new FieldDriveCommand(fieldCentricDriveSubsystem,
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

        driveGamepad.getGamepadButton(GamepadKeys.Button.A).whenPressed(new InstantCommand(() -> wristSubsystem.wristRotate(1)));
        driveGamepad.getGamepadButton(GamepadKeys.Button.B).whenPressed(new InstantCommand(() -> wristSubsystem.wristRotate(0)));
        driveGamepad.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(() -> wristSubsystem.wristRotate(0.33)));
        driveGamepad.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new InstantCommand(() -> wristSubsystem.wristRotate(0.672)));

        armSubsystem.setDefaultCommand(new DefaultArmCommand(armSubsystem, () -> movementGamepad.getLeftY()));

        /*fingerSubsystem.setDefaultCommand(new DefaultFingerCommand(fingerSubsystem,
                () -> movementGamepad.getButton(GamepadKeys.Button.B),
                () -> movementGamepad.getButton(GamepadKeys.Button.X),
                () -> movementGamepad.getButton(GamepadKeys.Button.Y),
                () -> movementGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)));*/




    }
}