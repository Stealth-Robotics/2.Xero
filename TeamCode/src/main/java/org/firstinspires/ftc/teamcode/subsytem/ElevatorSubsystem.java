package org.firstinspires.ftc.teamcode.subsytem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ElevatorSubsystem extends SubsystemBase {

    private DcMotor elevatorRotMotor;
    private Servo handRotMotor;

    public ElevatorSubsystem(HardwareMap hardwareMap){

        elevatorRotMotor = hardwareMap.get(DcMotor.class,"elevatorRotMotor");
        handRotMotor = hardwareMap.get(Servo.class,"handRotMotor");

    }

    public void run(){
        elevatorRotMotor.setPower(1);
    }
    public void run(int handPos){
        handRotMotor.setPosition(handPos);
    }
    public double getposition(){
        return elevatorRotMotor.getCurrentPosition();
    }
}
