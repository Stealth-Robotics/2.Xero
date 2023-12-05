package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

@TeleOp(name = "Concept: Scan Servo", group = "zJims")

public class Blinken extends LinearOpMode {

    static final double INCREMENT   = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int    CYCLE_MS    =   50;     // period of each cycle
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.0;     // Minimum rotational position

    // Define class members
    RevBlinkinLedDriver blink;
    double  position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    boolean rampUp = true;


    @Override
    public void runOpMode() {

        // Connect to servo (Assume Robot Left Hand)
        // Change the text in quotes to match any servo name on your robot.
        blink = hardwareMap.get(RevBlinkinLedDriver.class, "blink");

        waitForStart();


        // Scan servo till stop pressed.
        while(opModeIsActive()){
            blink.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
            if(true == gamepad1.a)
            {
                blink.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
            }
            if(true == gamepad1.b)
            {
                blink.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
            }
            if(true == gamepad1.x)
            {
                blink.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
            }
            if(true == gamepad1.y)
            {
                blink.setPattern(RevBlinkinLedDriver.BlinkinPattern.YELLOW);
            }

            if(true == gamepad1.dpad_down)
            {
                blink.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP1_END_TO_END_BLEND_TO_BLACK);
            }
            if(true == gamepad1.dpad_up)
            {
                blink.setPattern(RevBlinkinLedDriver.BlinkinPattern.SHOT_BLUE);
            }
            if(true == gamepad1.dpad_left)
            {
                blink.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP1_LIGHT_CHASE);
            }
            if(true == gamepad1.dpad_right)
            {
                blink.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP2_STROBE);
            }


            sleep(CYCLE_MS);
            idle();
        }

        // Signal done;
        telemetry.addData(">", "Done");
        telemetry.update();
    }
}
