package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsytem.DriveSubsystem;

import java.util.concurrent.TimeUnit;

public class DriveForTime extends CommandBase {

    DriveSubsystem driveSubsystem;

    Timing.Timer timer;

    double driveX;
    double driveY;
    double driveTheta;

    public DriveForTime(DriveSubsystem driveSubsystem, long milliseconds, double driveX, double driveY, double driveTheta)
    {
        this.driveSubsystem = driveSubsystem;
        this.timer = new Timing.Timer(milliseconds, TimeUnit.MILLISECONDS);

        this.driveX = driveX;
        this.driveY = driveY;
        this.driveTheta = driveTheta;
    }

    @Override
    public void initialize() {
        driveSubsystem.driveTeleop(driveY, driveX, driveTheta);
        timer.start();
    }

    @Override
    public boolean isFinished() {
        return timer.done();
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.driveTeleop(0,0,0);
    }
}
