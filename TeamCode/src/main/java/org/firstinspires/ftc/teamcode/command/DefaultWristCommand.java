package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;

import java.util.function.DoubleSupplier;

public class DefaultWristCommand extends CommandBase {
    WristSubsystem wrist;
    DoubleSupplier armPosition;
    DefaultArmCommand defaultArmCommand;
    DefaultWristCommand(WristSubsystem wrist, DoubleSupplier armPosition, DefaultArmCommand defaultArmCommand){
        this.wrist = wrist;
        this.armPosition = armPosition;
        this.defaultArmCommand = defaultArmCommand;
        addRequirements(wrist);
    }

    public void execute(){
        if (armPosition.getAsDouble() <= defaultArmCommand.ArmMax && armPosition.getAsDouble() > defaultArmCommand.armAboveBarMin){
            wrist.wristRotate(defaultArmCommand.wristScorePosition);
        } else if (armPosition.getAsDouble() <= defaultArmCommand.armAboveBarMin && armPosition.getAsDouble() > defaultArmCommand.armBelowBarMax){
            wrist.wristRotate(defaultArmCommand.wristAvoidBarPosition);
        } else if (armPosition.getAsDouble() <= defaultArmCommand.armBelowBarMax && armPosition.getAsDouble() > defaultArmCommand.ArmMin){
            wrist.wristRotate(defaultArmCommand.wristIntakePosition);
        }
    }
}
