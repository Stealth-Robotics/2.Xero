package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsytem.IntakeSubsystem;

import java.util.function.DoubleSupplier;

public class DefaultIntakeCommand extends CommandBase {
    final IntakeSubsystem intake;
    final DoubleSupplier leftTrigger;
    final DoubleSupplier rightTrigger;

    Telemetry telemetry;

    public DefaultIntakeCommand(IntakeSubsystem intake, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
        this.intake = intake;
        this.leftTrigger = leftTrigger;
        this.rightTrigger = rightTrigger;

        addRequirements(intake);
    }
    @Override
    public void execute() {
        //runs intake at speed controlled by leftTrigger when leftTrigger is pressed
        if (leftTrigger.getAsDouble()>.5){
            intake.pullIn(leftTrigger.getAsDouble());
        }
        else {
            //runs intake backwards at speed controlled by rightTrigger when rightTrigger is pressed.
            if (rightTrigger.getAsDouble() > .5) {
                intake.pushOut(rightTrigger.getAsDouble());
            }
            else{
                intake.stop();
            }
        }
    }
}
