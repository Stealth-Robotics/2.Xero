package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.FingerSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DefaultFingerCommand extends CommandBase {
    double intakePosition = 0.9;
    double closedPosition = 0.7;
    double openPosition = 1;
    BooleanSupplier bButton;
    BooleanSupplier xButton;
    BooleanSupplier yButton;
    DoubleSupplier rightTrigger;
    FingerSubsystem finger;

    public DefaultFingerCommand(FingerSubsystem finger, BooleanSupplier bButton, BooleanSupplier xButton, BooleanSupplier yButton, DoubleSupplier rightTrigger){
        this.finger = finger;
        this.bButton = bButton;
        this.xButton = xButton;
        this.yButton = yButton;
        this.rightTrigger = rightTrigger;
        addRequirements(finger);
    }
    public void moveFinger(double y){
        finger.moveFinger(y);
    }
    public void execute(){;
        if(rightTrigger.getAsDouble()>0.5) {
            finger.moveFinger(openPosition);
        }
        else if (xButton.getAsBoolean()) {
            finger.moveFinger(intakePosition);
        } else finger.moveFinger(closedPosition);

    }
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
