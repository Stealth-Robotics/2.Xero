package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.IntakeSubsystem;

import java.util.function.DoubleSupplier;
// duck
public class AutonomousIntakeCommand extends CommandBase {
    final IntakeSubsystem intake;
    double speed;
    public AutonomousIntakeCommand(IntakeSubsystem intake, double speed) {
        this.intake = intake;
        this.speed = speed;

        addRequirements(intake);
    }
    @Override
    public void execute() {
        intake.pullIn(speed);
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
//minecraft is the best game ever
// not geometry dash or fortnite