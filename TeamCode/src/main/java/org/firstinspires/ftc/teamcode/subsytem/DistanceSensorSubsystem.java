package org.firstinspires.ftc.teamcode.subsytem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DistanceSensorSubsystem extends SubsystemBase {
    public AnalogInput distanceSensorLeft;
    public AnalogInput distanceSensorRight;
    private double rightOffset = 0;
    private double distanceOffset = 0;

    public DistanceSensorSubsystem(HardwareMap hardwareMap) {
        distanceSensorLeft = hardwareMap.get(AnalogInput.class, "leftDistanceSensor");
        distanceSensorRight = hardwareMap.get(AnalogInput.class, "rightDistanceSensor");
    }

    public double getDistanceLeft(){
        return distanceSensorLeft.getVoltage();
    }
    public double getDistanceRight(){
        return distanceSensorRight.getVoltage();
    }
    public double getDistanceOffset(){
        return distanceOffset;
    }
}
