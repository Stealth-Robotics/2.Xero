package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;

public class ArmToSetpoint extends CommandBase {
    private final ArmSubsystem armSubsystem;
    private double setpoint;

    public ArmToSetpoint(ArmSubsystem armSubsystem, double setpoint){
        this.armSubsystem =  armSubsystem;
        this.setpoint = setpoint;

        addRequirements(armSubsystem);
    }

    @Override
    public void initialize() {
        armSubsystem.setSetPoint(setpoint);
    }

    @Override
    public boolean isFinished() {
        return armSubsystem.atSetPoint();
    }
}
