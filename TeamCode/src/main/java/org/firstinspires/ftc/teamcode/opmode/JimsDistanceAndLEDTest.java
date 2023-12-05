package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Distance and LED Test", group="zJims Tests")

public class JimsDistanceAndLEDTest extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private AnalogInput rightDistSensor = null;
    private LED rightGreenLED = null;
    private LED rightYellowLED = null;
    private AnalogInput leftDistSensor = null;
    private LED leftGreenLED = null;
    private LED leftYellowLED = null;

    private void setLEDS(double dist, LED greenLED, LED yellowLED)
    {
        if((dist > 1.7)&&(dist < 2.2))
        {
            greenLED.enable(false);
            yellowLED.enable(true);
        }
        else
        {
            if((dist > 1.3)&&(dist < 2.5))
            {
                greenLED.enable(true);
                yellowLED.enable(false);
            }
            else
            {
                greenLED.enable(true);
                yellowLED.enable(true);
                                                                    }
        }
    }

    @Override
    public void runOpMode() {
        rightDistSensor = hardwareMap.get(AnalogInput.class, "rightDist");
        rightGreenLED = hardwareMap.get(LED.class, "rightGreenLED");
        rightYellowLED = hardwareMap.get(LED.class, "rightYellowLED");
        leftDistSensor = hardwareMap.get(AnalogInput.class, "leftDist");
        leftGreenLED = hardwareMap.get(LED.class, "leftGreenLED");
        leftYellowLED = hardwareMap.get(LED.class, "leftYellowLED");

        waitForStart();

        // While the Op Mode is running, show the motor's status via telemetry
        while (opModeIsActive()) {
            double rightDist = rightDistSensor.getVoltage();
            double leftDist = leftDistSensor.getVoltage();
            setLEDS(rightDist, rightGreenLED, rightYellowLED);
            setLEDS(leftDist, leftGreenLED, leftYellowLED);

            telemetry.addData("right Dist", rightDist);
            telemetry.addData("left Dist", leftDist);
            telemetry.update();
        }
    }
}
// if this is not working, try  using a glow squid, redstone, and a pentagram. it works, trust me.