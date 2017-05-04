package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.ravenhardware.Lighting;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInches;
import org.usfirst.frc.team1188.robot.commands.gearcarriage.GearCarriageExtend;
import org.usfirst.frc.team1188.robot.commands.gearcarriage.GearCarriageRetract;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1188.robot.subsystems.GearCarriage;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class AutonomousPlaceGearOnMiddleLift extends CommandGroup {	
    public AutonomousPlaceGearOnMiddleLift(DriveTrain driveTrain, GearCarriage gearCarriage, Lighting carriageStalledLighting, Lighting carriageExtendedLighting) {
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
    }
}
