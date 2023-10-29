package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;

import java.util.function.DoubleSupplier;

public class DefaultArmCommand extends CommandBase {
    final ArmSubsystem arm;

    final WristSubsystem wrist;

    final DoubleSupplier leftStickY;

    final double ArmMax = 1;
    final double ArmMin = 1;


    public DefaultArmCommand(ArmSubsystem arm, WristSubsystem wrist, DoubleSupplier leftStickY) {
        this.arm = arm;
        this.leftStickY = leftStickY;
        this.wrist = wrist;

        addRequirements(arm);

        addRequirements(wrist);
    }

    @Override
    public void execute() {
        /*if ((arm.getPosition() >= ArmMax && leftStickY.getAsDouble() < 0)||
                (arm.getPosition() <= ArmMin && leftStickY.getAsDouble() > 0)||
                (ArmMin < arm.getPosition() && arm.getPosition() < ArmMax)){
            ArmSubsystem.elevatorRotate(leftStickY.getAsDouble());
        }*/
        ArmSubsystem.elevatorRotate(leftStickY.getAsDouble()/5);
        arm.periodic();
        wrist.periodic();
   }
}
