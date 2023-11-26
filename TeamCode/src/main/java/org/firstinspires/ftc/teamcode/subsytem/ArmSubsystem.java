package org.firstinspires.ftc.teamcode.subsytem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmSubsystem extends SubsystemBase {

    boolean runPID = true;

    private static DcMotorEx elevatorRotMotor;

    Telemetry telemetry;
    double kp = 0.07;
    double kd = 0.0001;
    double ki = 0;
    DigitalChannel limitSwitch;

    public ArmSubsystem(HardwareMap hardwareMap, Telemetry telemetry){

        elevatorRotMotor = hardwareMap.get(DcMotorEx.class,"elevatorRotMotor");
        limitSwitch = hardwareMap.get(DigitalChannel.class, "armLimitSwitch");
        this.telemetry = telemetry;
        elevatorRotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setSetPoint(0);
        elevatorRotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorRotMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorRotMotor.setVelocity(1700);
    }


    public int getPosition() {
        return (elevatorRotMotor.getCurrentPosition());
    }

    public void setPower(double newPower)
    {
        setSetPoint((int)(elevatorRotMotor.getTargetPosition()+(newPower*-75)));
    }

    public boolean atSetPoint()
    {
        return elevatorRotMotor.getTargetPosition()==elevatorRotMotor.getCurrentPosition();
    }

    public void setSetPoint(double setPoint) {
        if (setPoint <= 0) {
            elevatorRotMotor.setTargetPosition((int)setPoint);
        }
    }

    public void setRunPID(boolean newValue)
    {
        runPID = newValue;
    }

    public boolean getLimitSwitch(){
        return(!limitSwitch.getState());
    }

    @Override
    public void periodic() {
        if(getLimitSwitch()){
            elevatorRotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setSetPoint(0);
            elevatorRotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorRotMotor.setVelocity(1700);
        }

        telemetry.addData("arm position: ", getPosition());
        telemetry.addData("set point: ", elevatorRotMotor.getTargetPosition());
        telemetry.addData("limit switch", getLimitSwitch());
        telemetry.addData("motor state", elevatorRotMotor.isBusy());

        telemetry.update();
    }
}


/* Original Code before control loop fix
public class ArmSubsystem extends SubsystemBase {

    boolean runPID = true;

    private static DcMotor elevatorRotMotor;

    private final PIDController armController;

    Telemetry telemetry;
    double kp = 0.07;
    double kd = 0.0001;
    double ki = 0;
    DigitalChannel limitSwitch;



    public ArmSubsystem(HardwareMap hardwareMap, Telemetry telemetry){

        elevatorRotMotor = hardwareMap.get(DcMotor.class,"elevatorRotMotor");
        limitSwitch = hardwareMap.get(DigitalChannel.class, "armLimitSwitch");
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
        //armController.setSetPoint(armController.getSetPoint()+newPower);
    }

    public boolean getLimitSwitchStatus(){
        return(limitSwitch.getState());
    }

    public void setSetPoint(double setPoint){
        armController.setSetPoint(setPoint);
    }
    public boolean atSetpoint()
    {
        return armController.atSetPoint();
    }
    public void setRunPID(boolean newValue)
    {
        runPID = newValue;
    }

    public boolean getLimitSwitch(){
        return(limitSwitch.getState());
    }

    @Override
    public void periodic() {
        if(limitSwitch.getState()){
            elevatorRotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevatorRotMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            setSetPoint(0);
        }

        telemetry.addData("arm position: ", getPosition());

        double calc = armController.calculate(getPosition());

        telemetry.addData("calcualtion", calc);
        if(runPID){
            elevatorRotMotor.setPower(-calc);
        }
        telemetry.addData("Limit Switch", limitSwitch.getState());
        telemetry.addData("error: ", armController.getPositionError());
        telemetry.update();


    }
}
*/