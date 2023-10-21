package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.command.DefaultIntakeCommand;
import org.firstinspires.ftc.teamcode.command.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.subsytem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.IntakeSubsystem;

@TeleOp(name = "TeleOp")
public class TeleopOpmode extends CommandOpMode {

    DriveSubsystem driveSubsystem;

    IntakeSubsystem intakeSubsystem;

    ElevatorSubsystem elevatorSubsystem;

    GamepadEx driveGamepad;

    GamepadEx movementGamepad;

    @Override
    public void initialize() {

        driveGamepad = new GamepadEx(gamepad1);
        movementGamepad = new GamepadEx(gamepad2);

        driveSubsystem = new DriveSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        //elevatorSubsystem

        //register(driveSubsystem);
        register(intakeSubsystem);

        //driveSubsystem.setDefaultCommand(new DefaultDriveCommand(driveSubsystem, () -> driveGamepad.getLeftY(), () -> driveGamepad.getLeftX(), () -> driveGamepad.getRightX(), () -> driveGamepad.getGamepadButton(GamepadKeys.Button.A).get()));

        intakeSubsystem.setDefaultCommand(new DefaultIntakeCommand(intakeSubsystem,
                () -> movementGamepad.gamepad.left_trigger,
                () -> movementGamepad.gamepad.right_trigger
                )
        );

    }
}
