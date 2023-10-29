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
        /*telemetry.addData("leftTrigger", leftTrigger.getAsDouble());
        telemetry.addData("rightTrigger", rightTrigger.getAsDouble());
        telemetry.update();*/
        if (leftTrigger.getAsDouble()>.5){
            intake.pullIn(leftTrigger.getAsDouble());
        }
        else {
            if (rightTrigger.getAsDouble() > .5) {
                intake.pushOut(rightTrigger.getAsDouble());
            }
            else{
                intake.stop();
            }
        }
    }
}
