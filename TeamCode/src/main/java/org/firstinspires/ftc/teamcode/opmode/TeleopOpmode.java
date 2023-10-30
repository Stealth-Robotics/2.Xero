package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.command.DefaultArmCommand;
import org.firstinspires.ftc.teamcode.command.DefaultIntakeCommand;
import org.firstinspires.ftc.teamcode.command.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.scaffolding.WristScaffolding;
import org.firstinspires.ftc.teamcode.subsytem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;
import com.acmerobotics.dashboard.*;

@TeleOp(name = "TeleOp2")
public class TeleopOpmode extends CommandOpMode {

    DriveSubsystem driveSubsystem;

    IntakeSubsystem intakeSubsystem;

    WristSubsystem wristSubsystem;

    ArmSubsystem armSubsystem;
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

        register(driveSubsystem);
        register(intakeSubsystem);

        driveSubsystem.setDefaultCommand(new DefaultDriveCommand(driveSubsystem,
                () -> driveGamepad.getRightX(),
                () -> driveGamepad.getRightY(),
                () -> driveGamepad.getLeftX(),
                () -> driveGamepad.getGamepadButton(GamepadKeys.Button.A).get()));
        //Decided to make leftTrigger use the Right Trigger because it felt more natural to use the right trigger for intake
        /*intakeSubsystem.setDefaultCommand(new DefaultIntakeCommand(intakeSubsystem,
                () -> driveGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER),
                () -> driveGamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)));

        //wristSubsystem.setDefaultCommand(new DefaultArmCommand(armSubsystem, wristSubsystem, () -> movementGamepad.getLeftY()));

        /*wristSubsystem.setDefaultCommand(new WristScaffolding(wristSubsystem,
                () -> movementGamepad.getButton(GamepadKeys.Button.A),
                () -> movementGamepad.getButton(GamepadKeys.Button.B),
                () -> movementGamepad.getButton(GamepadKeys.Button.X))); */

        armSubsystem.setDefaultCommand(new DefaultArmCommand(armSubsystem, wristSubsystem, () -> movementGamepad.getLeftY()));

    }
}
