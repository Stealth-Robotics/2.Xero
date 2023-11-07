package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;

public class AutonomousArmCommand extends CommandBase {
    final ArmSubsystem arm;
    public AutonomousArmCommand(ArmSubsystem arm){
        this.arm = arm;
        addRequirements(arm);
    }
    public void execute(){

    }

}
