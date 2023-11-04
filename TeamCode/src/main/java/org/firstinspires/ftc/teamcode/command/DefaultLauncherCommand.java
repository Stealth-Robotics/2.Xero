package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.LauncherSubsystem;

import java.util.function.BooleanSupplier;

public class DefaultLauncherCommand extends CommandBase {
    LauncherSubsystem launcher;
    BooleanSupplier aButton;
    final double launchPosition = 1;
    final double closedPosition = 0.2;

    public DefaultLauncherCommand(LauncherSubsystem launcher, BooleanSupplier aButton){
        this.launcher = launcher;
        this.aButton = aButton;
        addRequirements(launcher);
    }

    @Override
    public void initialize() {
        launcher.rotateLever(closedPosition);
    }

    public void execute() {
        if(aButton.getAsBoolean() == true){
            launcher.rotateLever(launchPosition);
        }
    }
}
