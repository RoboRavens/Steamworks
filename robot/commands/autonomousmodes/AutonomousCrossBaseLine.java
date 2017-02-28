package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInches;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCrossBaseLine extends CommandGroup {

    public AutonomousCrossBaseLine(DriveTrain driveTrain) {
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			Calibrations.AutonomousCrossBaseLineDriveForwardInches,
    			Calibrations.AutonomousCrossBaselineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    }
}
