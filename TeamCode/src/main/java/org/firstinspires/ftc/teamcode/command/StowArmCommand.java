package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.XeroConstants;
import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;

public class StowArmCommand extends CommandBase {
    WristSubsystem wrist;
    ArmSubsystem arm;

    public StowArmCommand(WristSubsystem wrist, ArmSubsystem arm){
        this.wrist = wrist;
        this.arm = arm;
    }

    @Override
    public void execute() {
        wrist.wristRotate(XeroConstants.WristSafePosition);
        arm.setSetPoint(6500);
        arm.periodic();
    }

    @Override
    public boolean isFinished() {
        wrist.wristRotate(XeroConstants.WristIntakePosition);
        return arm.getLimitSwitch();
    }
}
