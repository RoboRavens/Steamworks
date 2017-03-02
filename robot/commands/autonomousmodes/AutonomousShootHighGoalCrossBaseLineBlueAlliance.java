package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInches;
import org.usfirst.frc.team1188.robot.commands.fuelshooter.FuelShooterShootForNumberOfSeconds;
import org.usfirst.frc.team1188.robot.commands.fuelshooter.FuelShooterStop;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1188.robot.subsystems.FuelIndexer;
import org.usfirst.frc.team1188.robot.subsystems.FuelPump;
import org.usfirst.frc.team1188.robot.subsystems.FuelShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousShootHighGoalCrossBaseLineBlueAlliance extends CommandGroup {
    public AutonomousShootHighGoalCrossBaseLineBlueAlliance(DriveTrain driveTrain, FuelPump fuelPump, FuelIndexer fuelIndexer, FuelShooter fuelShooter) {
    	addSequential(new FuelShooterShootForNumberOfSeconds(fuelShooter,
    			fuelIndexer,
    			fuelPump, 
    			Calibrations.AutonomousShootHighGoalShootingSeconds));
    	addSequential(new FuelShooterStop(fuelShooter));
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			Calibrations.AutonomousCrossBaseLineDriveForwardInches,
    			Calibrations.AutonomousCrossBaselineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    	
    }
}
