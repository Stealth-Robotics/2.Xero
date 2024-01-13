package org.firstinspires.ftc.teamcode.subsytem.pipelines;

import android.graphics.Canvas;

import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.stealthrobotics.library.Alliance;

public class PropProcessor implements VisionProcessor {

    public enum PropPosition{
        LEFT, CENTER, RIGHT
    }

    private final Alliance alliance;

    Mat testMat = new Mat();
    Mat highMat = new Mat();
    Mat lowMat = new Mat();
    Mat finalMat = new Mat();

    double redThreshold = 0.5;

    PropPosition result = PropPosition.CENTER;
    //TODO: tune these values

    static final Rect LEFT_RECTANGLE = new Rect(
            new Point(0, 0),
            new Point(240, 480)
    );

    static final Rect RIGHT_RECTANGLE = new Rect(
            new Point(600, 0),
            new Point(640, 480)
    );
    static final Rect CENTER_RECT = new Rect(
            new Point(240, 0),
            new Point(600, 480)
    );

    Scalar lowHSVColorUpper;
    Scalar highHSVColorLower;
    Scalar lowHSVColorLower;
    Scalar highHSVColorUpper;


    public PropProcessor(Alliance alliance){

        this.alliance = alliance;

        //sets the color thresholds based on alliance
        if(alliance == Alliance.RED){
            lowHSVColorLower = new Scalar(0, 150, 150); //beginning of red
            lowHSVColorUpper = new Scalar(20, 255, 255);

            highHSVColorLower = new Scalar(138, 150, 150); //end of red
            highHSVColorUpper = new Scalar(255, 255, 255);
        }

        else if(alliance == Alliance.BLUE){
            lowHSVColorLower = new Scalar(100, 100, 100); //blue thresholding
            lowHSVColorUpper = new Scalar(160, 255, 255);

            highHSVColorLower = new Scalar(160, 255, 255);
            highHSVColorUpper = new Scalar(160, 255, 255);
        }
    }
    @Override
    public void init(int width, int height, CameraCalibration calibration) {
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {

        Mat copy = frame;


        //convert to hsv for thresholding
        Imgproc.cvtColor(frame, testMat, Imgproc.COLOR_RGB2HSV);


        //maps white to everything in color range and black to everything out of color range
        Core.inRange(testMat, lowHSVColorLower, lowHSVColorUpper, lowMat);
        Core.inRange(testMat, highHSVColorLower, highHSVColorUpper, highMat);

        testMat.release();

        //combines ranges since red is split between 0 and 180
        Core.bitwise_or(lowMat, highMat, finalMat);

        lowMat.release();
        highMat.release();

        Imgproc.rectangle(copy, LEFT_RECTANGLE, new Scalar(0, 255, 0));

        //gets the sum of the pixels in each rectangle
        double leftBox = Core.sumElems(finalMat.submat(LEFT_RECTANGLE)).val[0];
        double rightBox = Core.sumElems(finalMat.submat(RIGHT_RECTANGLE)).val[0];
        double centerBox = Core.sumElems(finalMat.submat(CENTER_RECT)).val[0];

        //finds max of 3 areas and sets the output string to the corresponding area
        double max = Math.max(Math.max(leftBox, rightBox), centerBox);
        if(leftBox == max) result = PropPosition.LEFT;
        else if(rightBox == max) result = PropPosition.RIGHT;
        else result = PropPosition.CENTER;

        if(leftBox < 1500000 && centerBox < 1500000 && rightBox < 1500000 ){
            result = PropPosition.RIGHT;
        }
        FtcDashboard.getInstance().getTelemetry().addData("left", leftBox);
        FtcDashboard.getInstance().getTelemetry().addData("center", centerBox);
        FtcDashboard.getInstance().getTelemetry().addData("right", rightBox);
        FtcDashboard.getInstance().getTelemetry().update();

        finalMat.copyTo(frame);
        //frame.copyTo(frame);


        finalMat.release();

        return null;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {

    }
    public PropPosition getPropPosition(){

//        if(alliance == Alliance.RED)
//        {
//            switch (result)
//            {
//                case LEFT:
//                    result = PropPosition.RIGHT;
//                    break;
//
//                case RIGHT:
//                    result = PropPosition.LEFT;
//                    break;
//            }
//        }

        return result;
    }

}