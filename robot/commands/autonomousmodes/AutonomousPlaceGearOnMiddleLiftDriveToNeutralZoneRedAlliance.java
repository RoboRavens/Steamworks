package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.ravenhardware.Lighting;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInches;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegrees;
import org.usfirst.frc.team1188.robot.commands.gearcarriage.GearCarriageExtend;
import org.usfirst.frc.team1188.robot.commands.gearcarriage.GearCarriageRetract;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1188.robot.subsystems.GearCarriage;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class AutonomousPlaceGearOnMiddleLiftDriveToNeutralZoneRedAlliance extends CommandGroup {	
    public AutonomousPlaceGearOnMiddleLiftDriveToNeutralZoneRedAlliance(DriveTrain driveTrain, GearCarriage gearCarriage, Lighting carriageStalledLighting, Lighting carriageExtendedLighting) {
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			Calibrations.AutonomousPlaceGearOnMiddleLiftDriveForwardInches,
    			Calibrations.AutonomousPlaceGearOnMiddleLiftDriveForwardPowerMagnitude,
    			Calibrations.drivingForward,
    			Calibrations.AutonomousPlaceGearOnMiddleLiftDriveForwardTimeoutSeconds));
    	addSequential(new GearCarriageExtend(gearCarriage, driveTrain, carriageStalledLighting, carriageExtendedLighting));
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			Calibrations.AutonomousPlaceGearOnMiddleLiftDriveBackwardInches,
    			Calibrations.AutonomousPlaceGearOnMiddleLiftDriveBackwardPowerMagnitude,
    			Calibrations.drivingBackward));
    	addSequential(new GearCarriageRetract(gearCarriage, carriageStalledLighting, carriageExtendedLighting));
    	
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			15,
    			1,
    			Calibrations.drivingBackward));
    	
    	
    	addSequential(new DriveTrainTurnRelativeDegrees(driveTrain, -90));
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			Calibrations.AutonomousDriveToNeutralZoneDriveToWallInches,
    			1,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegrees(driveTrain, 90));
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			Calibrations.AutonomousDriveToNeutralZoneForwardInches,
    			1,
    			Calibrations.drivingForward));
    }
}
