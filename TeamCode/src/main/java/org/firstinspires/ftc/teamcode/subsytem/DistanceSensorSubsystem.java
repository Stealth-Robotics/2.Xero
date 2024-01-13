package org.firstinspires.ftc.teamcode.subsytem;

import static java.lang.Boolean.TRUE;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DistanceSensorSubsystem extends SubsystemBase {
    public AnalogInput distanceSensorLeft;
    public AnalogInput distanceSensorRight;
    private double rightOffset = 0;
    private double distanceOffset = 0;

    Telemetry telemetry;

    public DistanceSensorSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        distanceSensorLeft = hardwareMap.get(AnalogInput.class, "leftDistanceSensor");
        distanceSensorRight = hardwareMap.get(AnalogInput.class, "rightDistanceSensor");
    }

    public double getDistanceLeft(){
        return distanceSensorLeft.getMaxVoltage();
    }
    public double getDistanceRight(){
        return distanceSensorRight.getVoltage();
    }
    public double getDistanceOffset(){
        return distanceOffset;
    }

    @Override
    public void periodic() {
        telemetry.addData("distanceSensorLeft", distanceSensorLeft.getVoltage());
        telemetry.addData("distanceSensorRight", distanceSensorRight.getVoltage());
        telemetry.update();

    }
}
