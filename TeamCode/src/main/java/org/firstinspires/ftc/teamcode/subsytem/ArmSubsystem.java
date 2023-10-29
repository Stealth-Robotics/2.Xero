package org.firstinspires.ftc.teamcode.subsytem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;''

public class ArmSubsystem extends SubsystemBase {

    private static DcMotor elevatorRotMotor;
    Telemetry telemetry;


    public ArmSubsystem(HardwareMap hardwareMap, Telemetry telemetry){

        elevatorRotMotor = hardwareMap.get(DcMotor.class,"elevatorRotMotor");
        this.telemetry = telemetry;
        elevatorRotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorRotMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public int getPosition() {
        return (elevatorRotMotor.getCurrentPosition());
    }

    public static void elevatorRotate(double y){
        elevatorRotMotor.setPower(y);
    }


    @Override
    public void periodic() {
        telemetry.addData("arm position: ", getPosition());
    }
}
