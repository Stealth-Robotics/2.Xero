package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;

import java.util.function.DoubleSupplier;

public class DefaultWristCommand extends CommandBase {
    WristSubsystem wrist;
    DoubleSupplier armPosition;
    DefaultArmCommand defaultArmCommand;
    DoubleSupplier leftStickY;
    final double wristIntakePosition = 0.23;
    final double wristSafePosition = 0;
    final double wristScorePosition = 0.5;
    //TODO: make a constants file and add these four
    public final int ArmMax = -6300;
    public final int armAboveBarMin = -2200;
    public final int armBelowBarMax = -50;
    public final int ArmMin = 0;
    public DefaultWristCommand(WristSubsystem wrist, DoubleSupplier armPosition, DoubleSupplier leftStickY){
        this.wrist = wrist;
        this.defaultArmCommand = defaultArmCommand;
        this.armPosition = armPosition;
        this.leftStickY = leftStickY;
        addRequirements(wrist);
    }

    public void execute(){
        /*if ((armPosition.getAsDouble() >= defaultArmCommand.armBelowBarMax && leftStickY.getAsDouble() > 0)||
                (armPosition.getAsDouble() >= defaultArmCommand.armAboveBarMin && leftStickY.getAsDouble() < 0)){
            wrist.wristRotate(wristSafePosition);
        } else if (armPosition.getAsDouble() >= defaultArmCommand.armBelowBarMax && leftStickY.getAsDouble() < 0){
            wrist.wristRotate(wristIntakePosition);
        } else if (armPosition.getAsDouble() <= defaultArmCommand.armAboveBarMin && leftStickY.getAsDouble() > 0){
            wrist.wristRotate(wristScorePosition);*/
        if ((armPosition.getAsDouble() >= armBelowBarMax && leftStickY.getAsDouble() > 0)||
                (armPosition.getAsDouble() <= armAboveBarMin && leftStickY.getAsDouble() < 0)){
            wrist.wristRotate(wristSafePosition);
        } else if (armPosition.getAsDouble() >= armBelowBarMax && leftStickY.getAsDouble() < 0){
            wrist.wristRotate(wristIntakePosition);
        } else if (armPosition.getAsDouble() <= armAboveBarMin && leftStickY.getAsDouble() > 0){
            wrist.wristRotate(wristScorePosition);
        }

    }

    /*public void execute(){
        if (armPosition.getAsDouble() <= defaultArmCommand.ArmMax && armPosition.getAsDouble() > defaultArmCommand.armAboveBarMin){
            wrist.wristRotate(defaultArmCommand.wristScorePosition);
        } else if (armPosition.getAsDouble() <= defaultArmCommand.armAboveBarMin && armPosition.getAsDouble() > defaultArmCommand.armBelowBarMax){
            wrist.wristRotate(defaultArmCommand.wristAvoidBarPosition);
        } else if (armPosition.getAsDouble() <= defaultArmCommand.armBelowBarMax && armPosition.getAsDouble() > defaultArmCommand.ArmMin){
            wrist.wristRotate(defaultArmCommand.wristIntakePosition);
        }
    }*/
}
