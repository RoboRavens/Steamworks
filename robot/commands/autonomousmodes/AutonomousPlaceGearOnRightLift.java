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

/**
 *
 */
public class AutonomousPlaceGearOnRightLift extends CommandGroup {

    public AutonomousPlaceGearOnRightLift(DriveTrain driveTrain, GearCarriage gearCarriage, Lighting carriageStalledLighting, Lighting carriageExtendedLighting) {
    	
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			Calibrations.AutonomousPlaceGearOnRightLiftDriveFromWallInches,
    			Calibrations.AutonomousPlaceGearOnMiddleLiftDriveForwardPowerMagnitude,
    			Calibrations.drivingForward,
    			6.5));
    	
    	
    	addSequential(new DriveTrainTurnRelativeDegrees(driveTrain, Calibrations.AutonomousPlaceGearOnRightLiftTurnDegrees));
    	
    	
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			Calibrations.AutonomousPlaceGearOnRightLiftDriveToLiftInches,
    			Calibrations.AutonomousPlaceGearOnMiddleLiftDriveForwardPowerMagnitude,
    			Calibrations.drivingForward,
    			6.5));
    	
    	addSequential(new GearCarriageExtend(gearCarriage, driveTrain, carriageStalledLighting, carriageExtendedLighting));
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			Calibrations.AutonomousPlaceGearOnMiddleLiftDriveBackwardInches + 10,
    			Calibrations.AutonomousPlaceGearOnMiddleLiftDriveBackwardPowerMagnitude,
    			Calibrations.drivingBackward));
    	addSequential(new GearCarriageRetract(gearCarriage, carriageStalledLighting, carriageExtendedLighting));
    	
    	
    	addSequential(new DriveTrainTurnRelativeDegrees(driveTrain, -1 * Calibrations.AutonomousPlaceGearOnRightLiftTurnDegrees));
    	
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			Calibrations.AutonomousPlaceGearOnRightLiftDriveFromWallInches,
    			1,
    			Calibrations.drivingForward,
    			6.5));
    	
    }
}
