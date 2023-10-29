package org.firstinspires.ftc.teamcode.subsytem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FingerSubsystem extends SubsystemBase {
    private Servo fingerServo;

    public FingerSubsystem(HardwareMap hardwareMap){
        fingerServo = hardwareMap.get(Servo.class,"fingerServo");
    }
}
