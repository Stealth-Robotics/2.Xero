package org.firstinspires.ftc.teamcode.subsytem;

import android.sax.StartElementListener;
import android.util.Size;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.subsytem.pipelines.PropProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.stealthrobotics.library.Alliance;

public class CameraSubsystem extends SubsystemBase {

    private VisionPortal portal;
    private Telemetry telemetry;
    private PropProcessor processor;

    private PropProcessor.PropPosition result;

    public CameraSubsystem(HardwareMap hardwaremap, Alliance alliance, Telemetry telemetry) {
        this.result = PropProcessor.PropPosition.CENTER;
        this.telemetry = telemetry;
        this.processor = new PropProcessor(alliance);
        portal = new VisionPortal.Builder()
                .setCamera(hardwaremap.get(WebcamName.class, "Webcam"))
                .setCameraResolution(new Size(640, 480))
                .setCamera(BuiltinCameraDirection.BACK)
                .addProcessor(processor)
                .build();
    }

    @Override
    public void periodic() {
        result = processor.getPropPosition();
        telemetry.addData("position", getResult());
        telemetry.update();
    }

    public PropProcessor.PropPosition getResult() {
        return result;
    }
}

