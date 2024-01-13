package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.XeroConstants;
import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;
import org.firstinspires.ftc.teamcode.XeroConstants.*;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class AutonomousWristCommand extends CommandBase {
    WristSubsystem wrist;
    ArmSubsystem arm;
    DoubleSupplier armPosition;
    Double armTarget;
    Double armCurrent;
    BooleanSupplier limitSwitch;
    Telemetry telemetry = null;
    boolean firstTime = true;

    public AutonomousWristCommand(WristSubsystem wrist, BooleanSupplier limitSwitch, DoubleSupplier armPosition, Double armTarget, Telemetry telemety){
        this.wrist = wrist;
        this.limitSwitch = limitSwitch;
        this.armPosition = armPosition;
        this.armTarget = armTarget;
        this.telemetry = telemety;
        addRequirements(wrist);

        // Start Code in intake
        telemetry.addData("wrist state","wrist intake");
        wrist.wristRotate(XeroConstants.WristIntakePosition);

    }

    public void execute(){
        if (firstTime == true){
            armCurrent = armPosition.getAsDouble();
            firstTime = false;
        }
        if (((armPosition.getAsDouble() >= XeroConstants.ArmAboveBarMin) && (armTarget > armCurrent))
                || (armPosition.getAsDouble() <= XeroConstants.ArmBelowBarMax && armPosition.getAsDouble() >= XeroConstants.ArmAboveBarMin && armTarget < armCurrent)){
            telemetry.addData("wrist state","wrist safe");
            wrist.wristRotate(XeroConstants.WristSafePosition);
        } else if ((armPosition.getAsDouble() >= XeroConstants.ArmBelowBarMax && armTarget < armCurrent)){
            telemetry.addData("wrist state","wrist intake");
            wrist.wristRotate(XeroConstants.WristIntakePosition);
        } else if (armPosition.getAsDouble() <= XeroConstants.ArmEnterScoringRange){
            telemetry.addData("wrist state","wrist rotating");
            //wrist.wristRotate(XeroConstants.WristScorePosition + (armPosition.getAsDouble()-XeroConstants.ArmEnterScoringRange)/XeroConstants.ArmWristCoeficcient);
            wrist.wristRotate(XeroConstants.WristScorePosition);
        } else if (armPosition.getAsDouble() <= XeroConstants.ArmAboveBarMin && armTarget > armCurrent){
            telemetry.addData("wrist state","wrist score");
            wrist.wristRotate(XeroConstants.WristScorePosition);
        }
    }
}