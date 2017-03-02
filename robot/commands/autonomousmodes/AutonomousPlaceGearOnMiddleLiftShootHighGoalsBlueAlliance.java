package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.ravenhardware.Lighting;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInches;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegrees;
import org.usfirst.frc.team1188.robot.commands.fuelshooter.FuelShooterShootForNumberOfSeconds;
import org.usfirst.frc.team1188.robot.commands.fuelshooter.FuelShooterStop;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1188.robot.subsystems.FuelIndexer;
import org.usfirst.frc.team1188.robot.subsystems.FuelPump;
import org.usfirst.frc.team1188.robot.subsystems.FuelShooter;
import org.usfirst.frc.team1188.robot.subsystems.GearCarriage;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousPlaceGearOnMiddleLiftShootHighGoalsBlueAlliance extends CommandGroup {
    public AutonomousPlaceGearOnMiddleLiftShootHighGoalsBlueAlliance(DriveTrain driveTrain, GearCarriage gearCarriage, Lighting carriageStalledLighting, Lighting carriageExtendedLighting, FuelPump fuelPump, FuelIndexer fuelIndexer, FuelShooter fuelShooter) {
    	
    	// 162 total inches from middle lift to side of field
    	// 90 forward, 30 back, need to move 17 more forward to re-align.
    	
    	addSequential(new AutonomousPlaceGearOnMiddleLift(driveTrain, gearCarriage, carriageStalledLighting, carriageExtendedLighting));
    	addSequential(new DriveTrainTurnRelativeDegrees(driveTrain, Calibrations.AutonomousDropHopperTurnToHopperDegreesBlueAlliance));
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			81,
    			Calibrations.AutonomousDropHopperDriveFromWallSpeed,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegrees(driveTrain, -1 * Calibrations.AutonomousDropHopperTurnToHopperDegreesBlueAlliance));
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			17,
    			Calibrations.AutonomousDropHopperDriveFromWallSpeed,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegrees(driveTrain, Calibrations.AutonomousDropHopperTurnToHopperDegreesBlueAlliance));
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			81,
    			Calibrations.AutonomousDropHopperDriveFromWallSpeed,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegrees(driveTrain, Calibrations.AutonomousDropHopperAimAtGoalDegreesBlueAlliance));
    	addSequential(new FuelShooterShootForNumberOfSeconds(fuelShooter,
    			fuelIndexer,
    			fuelPump, 
    			Calibrations.AutonomousShootHighGoalShootingSeconds));
    	addSequential(new FuelShooterStop(fuelShooter));
    }
    
    
    
}
