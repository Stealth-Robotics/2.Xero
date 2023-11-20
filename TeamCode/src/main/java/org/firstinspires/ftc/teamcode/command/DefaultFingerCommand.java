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
    BooleanSupplier RB;
    BooleanSupplier xButton;
    BooleanSupplier LB;
    FingerSubsystem finger;

    public DefaultFingerCommand(FingerSubsystem finger, BooleanSupplier RB, BooleanSupplier xButton, BooleanSupplier LB){
        this.finger = finger;
        this.RB = RB;
        this.xButton = xButton;
        this.LB = LB;
        addRequirements(finger);
    }
    public void moveFinger(double y){
        finger.moveFinger(y);
    }
    public void execute(){;
        if(xButton.getAsBoolean()){
            finger.moveFinger(intakePosition);
        } else if(RB.getAsBoolean() ^ LB.getAsBoolean()) {
            finger.moveFinger(block1Position);
        } else if (RB.getAsBoolean() && LB.getAsBoolean()) {
            finger.moveFinger(openPosition);
        } else {
            finger.moveFinger(block2Position);
        }

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
