package org.firstinspires.ftc.teamcode.scaffolding;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;

import java.util.function.BooleanSupplier;

public class WristScaffolding extends CommandBase {

    final WristSubsystem wrist;
    BooleanSupplier aButton;
    BooleanSupplier bButton;
    BooleanSupplier xButton;
    final double intakePosition = 0.3;
    final double barClearPosition = 0;
    final double scoringPosition = 0.5;

    public WristScaffolding (WristSubsystem wrist, BooleanSupplier aButton, BooleanSupplier bButton, BooleanSupplier xButton){
        this.wrist = wrist;
        this.aButton = aButton;
        this.bButton = bButton;
        this.xButton = xButton;
        addRequirements(wrist);
    }

    @Override
    public void execute() {
        if(aButton.getAsBoolean() == true) wrist.wristRotate(intakePosition);
        if(bButton.getAsBoolean() == true) wrist.wristRotate(barClearPosition);
        if(xButton.getAsBoolean() == true) wrist.wristRotate(scoringPosition);

    }


}
