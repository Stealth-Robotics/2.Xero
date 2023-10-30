package org.firstinspires.ftc.teamcode.subsytem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem extends SubsystemBase {

    private DcMotor intakeMotor;

    public IntakeSubsystem(HardwareMap hardwareMap){
        intakeMotor = hardwareMap.get(DcMotor.class,"intakeMotor");

    }
    //pulls in at speed
    public void pullIn(double speed) {
        intakeMotor.setPower(speed);
    }
    //pushes out at speed
    public void pushOut(double speed) {
        intakeMotor.setPower(-speed);
    }
    public void stop() {
        intakeMotor.setPower(0);
    }
}
