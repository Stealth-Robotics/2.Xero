package org.firstinspires.ftc.teamcode.subsytem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FingerSubsystem extends SubsystemBase {
    private Servo fingerServo;

    Telemetry telemetry;

    public FingerSubsystem(HardwareMap hardwareMap, Telemetry telemetry){
        fingerServo = hardwareMap.get(Servo.class,"fingerServo");
        this.telemetry = telemetry;
    }

    public void moveFinger(double fingerPosition){
        fingerServo.setPosition(fingerPosition);
    }

    @Override
    public void periodic(){
        telemetry.addData("finger position: ", fingerServo.getPosition());
    }
}
