package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;

public class ResetArmCommand extends CommandBase {
    ArmSubsystem arm;

    public ResetArmCommand(ArmSubsystem arm){
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.setPower(-0.4);
    }

    @Override
    public boolean isFinished() {
        return(arm.getLimitSwitch());
    }

    @Override
    public void end(boolean interrupted) {
        arm.setPower(0);
    }
}
