package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInches;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegrees;
import org.usfirst.frc.team1188.robot.commands.fuelshooter.FuelShooterShootForNumberOfSeconds;
import org.usfirst.frc.team1188.robot.commands.fuelshooter.FuelShooterStop;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1188.robot.subsystems.FuelIndexer;
import org.usfirst.frc.team1188.robot.subsystems.FuelPump;
import org.usfirst.frc.team1188.robot.subsystems.FuelShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCollectHopperShootGoalsRedAlliance extends CommandGroup {
    public AutonomousCollectHopperShootGoalsRedAlliance(DriveTrain driveTrain, FuelPump fuelPump, FuelIndexer fuelIndexer, FuelShooter fuelShooter) {
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			65,
    			Calibrations.AutonomousCrossBaselineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegrees(driveTrain, -1 * 90));
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			40,
    			Calibrations.AutonomousCrossBaselineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    	
    	
    	addSequential(new FuelShooterShootForNumberOfSeconds(fuelShooter,
    			fuelIndexer,
    			fuelPump, 
    			Calibrations.AutonomousShootHighGoalShootingSeconds));
    	addSequential(new FuelShooterStop(fuelShooter));
    	
    }
}
