package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.XeroConstants;
import org.firstinspires.ftc.teamcode.command.ArmToSetpoint;
import org.firstinspires.ftc.teamcode.command.FollowTrajectoryCommand;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsytem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.CameraSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.Drive;
import org.firstinspires.ftc.teamcode.subsytem.FingerSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.WristSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.pipelines.PropProcessor;
import org.firstinspires.inspection.InspectionState;
import org.stealthrobotics.library.Alliance;
import org.stealthrobotics.library.opmodes.StealthOpMode;

@Autonomous(name = "BlueFarAuto")
public class BlueFarAuto extends StealthOpMode {

    SampleMecanumDrive sampleMecanumDrive;
    Drive drive;
    ArmSubsystem arm;
    WristSubsystem wrist;
    FingerSubsystem finger;
    CameraSubsystem camera;
    PropProcessor.PropPosition propPosition;

    Trajectory toPropCenter;
    Trajectory toPropRight;
    Trajectory toPropLeft;
    Pose2d startPose;

    @Override
    public void initialize() {
        sampleMecanumDrive = new SampleMecanumDrive(hardwareMap);
        drive = new Drive(sampleMecanumDrive, true);
        arm = new ArmSubsystem(hardwareMap, telemetry);
        wrist = new WristSubsystem(hardwareMap, telemetry);
        finger = new FingerSubsystem(hardwareMap, telemetry);
        camera = new CameraSubsystem(hardwareMap, Alliance.BLUE, telemetry);
        startPose = new Pose2d(-36, 60, Math.toRadians(270));
        toPropCenter = drive.trajectoryBuilder(startPose)
                .forward(15)
                .build();
        toPropRight = drive.trajectoryBuilder(startPose)
                .splineToSplineHeading(new Pose2d(-43,56,Math.toRadians(255)),Math.toRadians(0))
                .build();
        toPropLeft = drive.trajectoryBuilder(startPose).
                splineToSplineHeading(new Pose2d(-48,36,Math.toRadians(0)),Math.toRadians(0))
                .build();
    }

    @Override
    public void whileWaitingToStart() {
        propPosition = camera.getResult();
        telemetry.addData("position", propPosition);
        telemetry.update();

        CommandScheduler.getInstance().run();
    }

    @Override
    public Command getAutoCommand() {
        if(propPosition == PropProcessor.PropPosition.CENTER)
        {
            return new SequentialCommandGroup(
                    new InstantCommand(()->drive.setPoseEstimate(startPose)),
                    new InstantCommand(() -> wrist.wristRotate(XeroConstants.WristSafePosition), wrist),
                    new InstantCommand(() -> arm.setRunPID(true)),
                    new WaitCommand(2000),
                    new ParallelCommandGroup(
                            new ArmToSetpoint(arm, XeroConstants.ArmPropDropPosition),
                            new FollowTrajectoryCommand(drive, toPropCenter)),
                    new WaitCommand(1000),
                    new InstantCommand(() -> wrist.wristRotate(XeroConstants.WristPropDropPosition), wrist),
                    new WaitCommand(1000),
                    new InstantCommand(() -> finger.moveFinger(XeroConstants.FingerBlock1Position), finger),
                    new WaitCommand(1000),
                    new InstantCommand(() -> finger.moveFinger(XeroConstants.FingerBlock2Position)),
                    new InstantCommand(()->wrist.wristRotate(XeroConstants.WristSafePosition)),
                    new WaitCommand(1000),
                    new ArmToSetpoint (arm, 0),
                    new InstantCommand(()->wrist.wristRotate(XeroConstants.WristIntakePosition))
            );

        } else if (propPosition == PropProcessor.PropPosition.LEFT) {
            return new SequentialCommandGroup(
                    new InstantCommand(()->drive.setPoseEstimate(startPose)),
                    new InstantCommand(() -> wrist.wristRotate(XeroConstants.WristSafePosition), wrist),
                    new InstantCommand(() -> arm.setRunPID(true)),
                    new WaitCommand(2000),
                    new ParallelCommandGroup(
                            new ArmToSetpoint(arm, XeroConstants.ArmPropDropPosition),
                            new FollowTrajectoryCommand(drive, toPropLeft)),
                    new WaitCommand(1000),
                    new InstantCommand(() -> wrist.wristRotate(XeroConstants.WristPropDropPosition), wrist),
                    new WaitCommand(1000),
                    new InstantCommand(() -> finger.moveFinger(XeroConstants.FingerBlock1Position), finger),
                    new WaitCommand(1000),
                    new InstantCommand(() -> finger.moveFinger(XeroConstants.FingerBlock2Position)),
                    new InstantCommand(()->wrist.wristRotate(XeroConstants.WristSafePosition)),
                    new WaitCommand(1000),
                    new ArmToSetpoint (arm, 0),
                    new InstantCommand(()->wrist.wristRotate(XeroConstants.WristIntakePosition))
            );

        } else {
            return new SequentialCommandGroup(
                    new InstantCommand(()->drive.setPoseEstimate(startPose)),
                    new InstantCommand(() -> wrist.wristRotate(XeroConstants.WristSafePosition), wrist),
                    new InstantCommand(() -> arm.setRunPID(true)),
                    new WaitCommand(2000),
                    new ParallelCommandGroup(
                            new ArmToSetpoint(arm, XeroConstants.ArmPropDropPosition),
                            new FollowTrajectoryCommand(drive, toPropRight)),
                    new WaitCommand(1000),
                    new InstantCommand(() -> wrist.wristRotate(XeroConstants.WristPropDropPosition), wrist),
                    new WaitCommand(1000),
                    new InstantCommand(() -> finger.moveFinger(XeroConstants.FingerBlock1Position), finger),
                    new WaitCommand(1000),
                    new InstantCommand(() -> finger.moveFinger(XeroConstants.FingerBlock2Position)),
                    new InstantCommand(()->wrist.wristRotate(XeroConstants.WristSafePosition)),
                    new WaitCommand(1000),
                    new ArmToSetpoint (arm, 0),
                    new InstantCommand(()->wrist.wristRotate(XeroConstants.WristIntakePosition))
            );
        }
    }
}
