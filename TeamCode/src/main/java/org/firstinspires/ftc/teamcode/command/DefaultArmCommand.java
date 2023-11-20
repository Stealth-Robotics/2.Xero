package org.firstinspires.ftc.teamcode.command;

import static com.sun.tools.doclint.Entity.or;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;

import java.util.function.DoubleSupplier;

public class DefaultArmCommand extends CommandBase {
    final ArmSubsystem arm;
    final DoubleSupplier leftStickY;

    boolean setpointSetOnce = false;

    //Top and bottom of where the arm can go without colliding with other parts of robot

    public final int ArmMax = -6300;
    public final int ArmMin = 0;


    public DefaultArmCommand(ArmSubsystem arm, DoubleSupplier leftStickY) {
        this.arm = arm;
        this.leftStickY = leftStickY;

        addRequirements(arm);
    }


    @Override
    public void execute() {
        if(!(((arm.getPosition() < ArmMax) && (leftStickY.getAsDouble() < 0)) || arm.getPosition() > ArmMin && leftStickY.getAsDouble() > 0)){
            arm.setPower(leftStickY.getAsDouble());
        }/*
        if (arm.getPosition() <= ArmMax && arm.getPosition() > armAboveBarMin)
        {
            wrist.wristRotate(wristScorePosition);
        } else if (arm.getPosition() < armAboveBarMin && arm.getPosition() > armBelowBarMax) {
            wrist.wristRotate(wristAvoidBarPosition);
        } else if (arm.getPosition() <= armBelowBarMax && arm.getPosition() >= ArmMin){
            wrist.wristRotate(wristIntakePosition);
        }*/
        //Runs the arm at speed (leftstickY/5)
        if (Math.abs(leftStickY.getAsDouble())>0.1){
            arm.setRunPID(false);
            arm.setPower(leftStickY.getAsDouble()*0.8);
            setpointSetOnce = false;
        }

        else if (!setpointSetOnce){
            setpointSetOnce = true;
            arm.setRunPID(true);
            arm.setSetPoint(arm.getPosition());
        }

   }
}
