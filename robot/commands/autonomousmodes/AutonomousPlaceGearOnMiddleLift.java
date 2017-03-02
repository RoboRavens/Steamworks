package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInches;
import org.usfirst.frc.team1188.robot.commands.gearcarriage.GearCarriageAutoPlace;
import org.usfirst.frc.team1188.robot.commands.gearcarriage.GearCarriageExtend;
import org.usfirst.frc.team1188.robot.commands.gearcarriage.GearCarriageRetract;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1188.robot.subsystems.GearCarriage;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class AutonomousPlaceGearOnMiddleLift extends CommandGroup {	
    public AutonomousPlaceGearOnMiddleLift(DriveTrain driveTrain, GearCarriage gearCarriage) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			Calibrations.AutonomousPlaceGearOnMiddleLiftDriveForwardInches,
    			Calibrations.AutonomousPlaceGearOnMiddleLiftDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    	addSequential(new GearCarriageExtend(gearCarriage, driveTrain));
    	addSequential(new DriveTrainDriveInches(driveTrain, 
    			Calibrations.AutonomousPlaceGearOnMiddleLiftDriveBackwardInches,
    			Calibrations.AutonomousPlaceGearOnMiddleLiftDriveBackwardPowerMagnitude,
    			Calibrations.drivingBackward));
    	addSequential(new GearCarriageRetract(gearCarriage));
    }
}
