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

    private DcMotorEx elevatorRotMotor;

    Telemetry telemetry;
    double kp = 0.007;
    double kd = 0.0001;
    double ki = 0;
    DigitalChannel limitSwitch;

    private final PIDController armController;

    boolean resetOnce = false;

    public ArmSubsystem(HardwareMap hardwareMap, Telemetry telemetry){

        armController = new PIDController(kp, ki, kd);
        armController.setTolerance(25);

        elevatorRotMotor = hardwareMap.get(DcMotorEx.class,"elevatorRotMotor");
        limitSwitch = hardwareMap.get(DigitalChannel.class, "armLimitSwitch");
        this.telemetry = telemetry;
        elevatorRotMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorRotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorRotMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


    public int getPosition() {
        return (elevatorRotMotor.getCurrentPosition());
    }

    public void setPower(double newPower)
    {
        elevatorRotMotor.setPower(newPower);
    }

    public boolean atSetPoint()
    {
        return armController.atSetPoint();
    }

    public void setSetPoint(double setPoint) {
        armController.setSetPoint(setPoint);
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
//        if(getLimitSwitch() && !resetOnce){
//            elevatorRotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            elevatorRotMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            setSetPoint(0);
//
//            resetOnce = true;
//        }
//
//        else if (!getLimitSwitch() && resetOnce)
//        {
//            resetOnce = false;
//        }

        double calc = armController.calculate(getPosition());

//        telemetry.addData("calcualtion", calc);
        if(runPID){
            elevatorRotMotor.setPower(calc);
        }
//
//        telemetry.addData("arm position: ", getPosition());
//        telemetry.addData("set point: ", armController.getSetPoint());
//        telemetry.addData("limit switch", getLimitSwitch());
//        telemetry.update();
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