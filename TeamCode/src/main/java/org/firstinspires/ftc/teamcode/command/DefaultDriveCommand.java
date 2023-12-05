package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.DriveSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DefaultDriveCommand extends CommandBase {

    DriveSubsystem driveSubsystem;
// here be dragons
    DoubleSupplier leftStickX;
    DoubleSupplier leftStickY;
    DoubleSupplier rightStickX;
    BooleanSupplier AButton;

    public DefaultDriveCommand (DriveSubsystem driveSubsystem, DoubleSupplier leftStickX, DoubleSupplier leftStickY, DoubleSupplier rightStickX)
    {
        this.driveSubsystem = driveSubsystem;

        this.leftStickX = leftStickX;
        this.leftStickY = leftStickY;
        this.rightStickX = rightStickX;

        this.AButton = AButton;

        addRequirements(driveSubsystem);
    }



    @Override
    public void execute() {
        driveSubsystem.driveTeleop(leftStickY.getAsDouble()*-1, leftStickX.getAsDouble()*-1, rightStickX.getAsDouble());
    }

}
