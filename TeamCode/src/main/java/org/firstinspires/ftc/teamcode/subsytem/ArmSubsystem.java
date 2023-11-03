package org.firstinspires.ftc.teamcode.subsytem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmSubsystem extends SubsystemBase {

    boolean runPID = true;

    private static DcMotor elevatorRotMotor;

    private final PIDController armController;

    Telemetry telemetry;
    double kp = 0.085;
    double kd = 0.0001;
    double ki = 0;



    public ArmSubsystem(HardwareMap hardwareMap, Telemetry telemetry){

        elevatorRotMotor = hardwareMap.get(DcMotor.class,"elevatorRotMotor");
        this.telemetry = telemetry;
        elevatorRotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorRotMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevatorRotMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        armController = new PIDController(kp, ki, kd);
        armController.setTolerance(10);
    }

    public int getPosition() {
        return (elevatorRotMotor.getCurrentPosition());
    }

    public void setPower(double newPower)
    {
        elevatorRotMotor.setPower(newPower);
    }

    public void setSetPoint(double setPoint){
        armController.setSetPoint(setPoint);
    }


    public void setRunPID(boolean newValue)
    {
        runPID = newValue;
    }

    @Override
    public void periodic() {
        telemetry.addData("arm position: ", getPosition());

        double calc = armController.calculate(getPosition());

        telemetry.addData("calcualtion", calc);
        if(runPID){
            elevatorRotMotor.setPower(-calc);
        }
        telemetry.addData("error: ", armController.getPositionError());


    }
}
