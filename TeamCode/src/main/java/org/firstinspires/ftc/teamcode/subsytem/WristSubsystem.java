package org.firstinspires.ftc.teamcode.subsytem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.SerialNumber;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class WristSubsystem extends SubsystemBase {
    private static Servo wristServo;
    Telemetry telemetry;

    public WristSubsystem (HardwareMap hardwareMap, Telemetry telemetry){
        //wristServo = hardwareMap.get(Servo.class, (SerialNumber) wristServo);
        wristServo = hardwareMap.get(Servo.class,"wristServo");
        this.telemetry = telemetry;
    }

    public static void wristRotate(double y){
        wristServo.setPosition(y);
    }

    @Override
    public void periodic() {
        telemetry.addData("wrist position: ", wristServo.getPosition());
        telemetry.update();
    }
}
