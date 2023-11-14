package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.FieldCentricDriveSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class FieldDriveCommand extends CommandBase {

    FieldCentricDriveSubsystem fieldCentricDriveSubsystem;

    DoubleSupplier leftStickX;
    DoubleSupplier leftStickY;
    DoubleSupplier rightStickX;
    BooleanSupplier halfSpeedButton;

    public FieldDriveCommand (FieldCentricDriveSubsystem driveSubsystem, DoubleSupplier leftStickX, DoubleSupplier leftStickY, DoubleSupplier rightStickX, BooleanSupplier halfSpeedButton)
    {
        this.fieldCentricDriveSubsystem = fieldCentricDriveSubsystem;

        this.leftStickX = leftStickX;
        this.leftStickY = leftStickY;
        this.rightStickX = rightStickX;

        this.halfSpeedButton = halfSpeedButton;

        addRequirements(driveSubsystem);
    }



    @Override
    public void execute() {
        fieldCentricDriveSubsystem.driveTeleop(leftStickY.getAsDouble()*-1, leftStickX.getAsDouble()*-1, rightStickX.getAsDouble());
    }

}
