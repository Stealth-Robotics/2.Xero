package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.command.AutoAlignCommand;
import org.firstinspires.ftc.teamcode.command.DefaultArmCommand;
import org.firstinspires.ftc.teamcode.command.DefaultFingerCommand;
import org.firstinspires.ftc.teamcode.command.DefaultIntakeCommand;
import org.firstinspires.ftc.teamcode.command.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.command.DefaultWristCommand;
import org.firstinspires.ftc.teamcode.scaffolding.WristScaffolding;
import org.firstinspires.ftc.teamcode.subsytem.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.FingerSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.LauncherSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.pipelines.PropProcessor;
import org.stealthrobotics.library.Alliance;
import org.stealthrobotics.library.opmodes.StealthOpMode;

import com.acmerobotics.dashboard.*;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp(name = "TeleOp2")
public class TeleopOpmode extends StealthOpMode {
// "teleop Arbitrary"
    DriveSubsystem driveSubsystem;
    IntakeSubsystem intakeSubsystem;
    DistanceSensorSubsystem distanceSensorSubsystem;
    WristSubsystem wristSubsystem;
    ArmSubsystem armSubsystem;
    FingerSubsystem fingerSubsystem;
    DefaultArmCommand defaultArmCommand;
    LauncherSubsystem launcherSubsystem;
    CameraSubsystem cameraSubsystem;

    //separate gamepads for movement and driving
    GamepadEx driveGamepad;

    GamepadEx movementGamepad;

    FtcDashboard dashboard = FtcDashboard.getInstance();

    @Override
    public void whileWaitingToStart() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void initialize() {

        telemetry = dashboard.getTelemetry();

        driveGamepad = new GamepadEx(gamepad1);
        movementGamepad = new GamepadEx(gamepad2);

// duck
        distanceSensorSubsystem = new DistanceSensorSubsystem(hardwareMap);
        driveSubsystem = new DriveSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        cameraSubsystem = new CameraSubsystem(hardwareMap, Alliance.BLUE, telemetry);
        //these two have telemetry so they can print data to the screen
        wristSubsystem = new WristSubsystem(hardwareMap, telemetry);
        armSubsystem = new ArmSubsystem(hardwareMap, telemetry);
        launcherSubsystem = new LauncherSubsystem(hardwareMap, telemetry);
        fingerSubsystem = new FingerSubsystem(hardwareMap, telemetry);

        register(driveSubsystem, intakeSubsystem, wristSubsystem, armSubsystem, launcherSubsystem, fingerSubsystem, cameraSubsystem);

        driveSubsystem.setDefaultCommand(new DefaultDriveCommand(driveSubsystem,
                () -> driveGamepad.getLeftX(),
                () -> -driveGamepad.getLeftY(),
                () -> driveGamepad.getRightX()));
        driveGamepad.getGamepadButton(GamepadKeys.Button.A).whenPressed(new AutoAlignCommand(driveSubsystem, distanceSensorSubsystem, ()->driveGamepad.getLeftY()));
        //Decided to make leftTrigger use the Right Trigger because it felt more natural to use the right trigger for intake
        intakeSubsystem.setDefaultCommand(new DefaultIntakeCommand(intakeSubsystem,
                () -> driveGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER),
                () -> driveGamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)));

        armSubsystem.setDefaultCommand(new DefaultArmCommand(armSubsystem, () -> movementGamepad.getLeftY()));

        //wristSubsystem.setDefaultCommand(new DefaultArmCommand(armSubsystem, wristSubsystem, () -> movementGamepad.getLeftY()));
        //wristSubsystem.setDefaultCommand(new DefaultWristCommand(wristSubsystem, armSubsystem.getPosition(), defaultArmCommand));
        //This one:
        // there is a duck on this code:
        wristSubsystem.setDefaultCommand(new DefaultWristCommand(wristSubsystem,
                ()->armSubsystem.getLimitSwitch(),
                () -> armSubsystem.getPosition(),
                () -> movementGamepad.getLeftY(),
                telemetry));

        /*movementGamepad.getGamepadButton(GamepadKeys.Button.A).whenPressed(new InstantCommand(() -> wristSubsystem.wristRotate(0.5)));
        movementGamepad.getGamepadButton(GamepadKeys.Button.B).whenPressed(new InstantCommand(() -> wristSubsystem.wristRotate(0.55)));
        movementGamepad.getGamepadButton(GamepadKeys.Button.Y).whenPressed(new InstantCommand(() -> wristSubsystem.wristRotate(0.6)));*/

// a mushroom grew on the code too

        movementGamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenPressed(new InstantCommand(()->launcherSubsystem.rotateLever(1)));
        movementGamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(new InstantCommand(()->launcherSubsystem.rotateLever(0.024)));

        fingerSubsystem.setDefaultCommand(new DefaultFingerCommand(fingerSubsystem,
                () -> movementGamepad.getGamepadButton(GamepadKeys.Button.B).get(),
                () -> movementGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER),
                () -> movementGamepad.getGamepadButton(GamepadKeys.Button.Y).get()));
        /*movementGamepad.getGamepadButton(GamepadKeys.Button.A).whenPressed(new InstantCommand(()-> fingerSubsystem.moveFinger(0.75)));
        movementGamepad.getGamepadButton(GamepadKeys.Button.B).whenPressed(new InstantCommand(()-> fingerSubsystem.moveFinger(.6)));
        movementGamepad.getGamepadButton(GamepadKeys.Button.X).whenPressed(new InstantCommand(()-> fingerSubsystem.moveFinger(0.4)));*/
// the math is mathing


    }
}