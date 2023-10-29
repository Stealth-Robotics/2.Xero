package org.firstinspires.ftc.teamcode.scaffolding;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;

public class WristScaffolding extends CommandBase {

    final WristSubsystem wrist;
    boolean aButton;
    boolean bButton;
    boolean xButton;
    final double intakePosition = 1;
    final double barClearPosition = 1;
    final double scoringPosition = 1;

    public WristScaffolding (WristSubsystem wrist, boolean aButton, boolean bButton, boolean xButton){
        this.wrist = wrist;
        this.aButton = aButton;
        this.bButton = bButton;
        this.xButton = xButton;
        addRequirements(wrist);
    }

    @Override
    public void execute() {
        if(aButton = true) wrist.wristRotate(intakePosition);
        if(bButton = true) wrist.wristRotate(barClearPosition);
        if(xButton = true) wrist.wristRotate(scoringPosition);

    }


}
