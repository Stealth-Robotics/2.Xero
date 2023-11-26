package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "LED Stick Test", group = "Tests")
public class QwiicLEDStickTest extends LinearOpMode
{
    private QwiicLEDStick led;

    public void runOpMode() throws InterruptedException
    {
        led = hardwareMap.get(QwiicLEDStick.class, "leds");

        led.setColor(0, 10, 0);
        
        waitForStart();

        while(opModeIsActive())
        {
            telemetry.addData("Running", "Running");
            telemetry.update();
            idle();
        }
    }
}
