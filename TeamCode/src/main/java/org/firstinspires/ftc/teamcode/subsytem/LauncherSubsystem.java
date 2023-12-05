package org.firstinspires.ftc.teamcode.subsytem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LauncherSubsystem extends SubsystemBase {

    public Servo leverServo;
    Telemetry telemetry;
    public LauncherSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        leverServo = hardwareMap.get(Servo.class, "leverServo");
        this.telemetry = telemetry;
    }
    //rotates lever to a position
    public void rotateLever(double y){
        leverServo.setPosition(y);
    }
    @Override
    public void periodic(){
        //telemetry.addData("launcher position: ", leverServo.getPosition());
    }



}
