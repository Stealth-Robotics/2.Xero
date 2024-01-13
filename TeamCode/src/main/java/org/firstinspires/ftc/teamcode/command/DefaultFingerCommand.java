package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.FingerSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DefaultFingerCommand extends CommandBase {
    double intakePosition = 1;
    double block2Position = 0.75;
    double block1Position = 0.6;
    double openPosition = 0.4;
    BooleanSupplier bButton;
    //below is right trigger
    DoubleSupplier rightTrigger;
    BooleanSupplier yButton;
    FingerSubsystem finger;

    public DefaultFingerCommand(FingerSubsystem finger, BooleanSupplier bButton, DoubleSupplier xButton, BooleanSupplier yButton){
        this.finger = finger;
        this.bButton = bButton;
        this.rightTrigger = xButton;
        this.yButton = yButton;
        addRequirements(finger);
    }
    public void moveFinger(double y){
        finger.moveFinger(y);
    }
    public void execute(){;
        if(rightTrigger.getAsDouble()>0.5){
            finger.moveFinger(intakePosition);
        } else if (bButton.getAsBoolean()) {
            finger.moveFinger(openPosition);
        } else if(yButton.getAsBoolean()) {
            finger.moveFinger(block1Position);
        } else {
            finger.moveFinger(block2Position);
        }

    }
    /*public void execute(){;
        if(xButton.getAsDouble()>0.5){
            finger.moveFinger(intakePosition);
        } else if(RB.getAsBoolean() ^ LB.getAsBoolean()) {
            finger.moveFinger(block1Position);
        } else if (RB.getAsBoolean() && LB.getAsBoolean()) {
            finger.moveFinger(openPosition);
        } else {
            finger.moveFinger(block2Position);
        }

    }*/
    /*public void execute(){
        if(bButton.getAsBoolean() == true) {
            finger.moveFinger(openPosition);
        } else if(xButton.getAsBoolean() == true) {
            finger.moveFinger(closedPosition);
        } else if(yButton.getAsBoolean() == true) {
            finger.moveFinger(intakePosition);
        } else finger.moveFinger(closedPosition-(openPosition-closedPosition)* rightTrigger.getAsDouble());
    }*/
    /*public void Open(){
        finger.moveFinger(openPosition);
    }
    public void Close(){
        finger.moveFinger(closedPosition);
    }
    public void IntakePosition(){
        finger.moveFinger(intakePosition);
    }*/
}
