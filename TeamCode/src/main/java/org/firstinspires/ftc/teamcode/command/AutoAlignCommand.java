package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsytem.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.subsytem.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class AutoAlignCommand extends SequentialCommandGroup {

    private final DriveSubsystem drive;
    private final DistanceSensorSubsystem distanceSensor;
    private final DoubleSupplier leftY;

    public AutoAlignCommand(DriveSubsystem drive, DistanceSensorSubsystem distanceSensor, DoubleSupplier leftY){
        this.drive = drive;
        this.distanceSensor = distanceSensor;
        this.leftY = leftY;

        addCommands(
                new ZeroHeadingWithDistanceSensors(drive, distanceSensor).withTimeout(2500),
                new AlignTranslationWithDistanceSensors(drive, distanceSensor),
                new ZeroHeadingWithDistanceSensors(drive, distanceSensor).withTimeout(1000),
                new DefaultDriveCommand(drive, leftY, () -> 0.0, () -> 0.0)
        );

    }
    public void execute(){

    }

}
