package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.DriveSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DefaultDriveCommand extends CommandBase {

    DriveSubsystem driveSubsystem;

    DoubleSupplier leftStickX;
    DoubleSupplier leftStickY;
    DoubleSupplier rightStickY;
    BooleanSupplier halfSpeedButton;

    public DefaultDriveCommand (DriveSubsystem driveSubsystem, DoubleSupplier leftStickX, DoubleSupplier leftStickY, DoubleSupplier rightStickX, BooleanSupplier halfSpeedButton)
    {
        this.driveSubsystem = driveSubsystem;

        this.leftStickX = leftStickX;
        this.leftStickY = leftStickY;
        this.rightStickY = rightStickX;

        this.halfSpeedButton = halfSpeedButton;

        addRequirements(driveSubsystem);
    }


    @Override
    public void execute() {
        driveSubsystem.driveTeleop(leftStickY.getAsDouble(), leftStickX.getAsDouble(), rightStickY.getAsDouble());
    }

}
