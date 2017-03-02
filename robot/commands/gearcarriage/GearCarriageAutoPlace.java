package org.usfirst.frc.team1188.robot.commands.gearcarriage;

import org.usfirst.frc.team1188.ravenhardware.RavenTank;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesForGearCarriage;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1188.robot.subsystems.GearCarriage;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;


public class GearCarriageAutoPlace extends Command {
	GearCarriage gearCarriage;
	DriveTrain driveTrain;
	RavenTank ravenTank;
	Timer safetyTimer;
	boolean reorienting = false;
	int reorientationAttempts = 0;
	
    public GearCarriageAutoPlace(GearCarriage gearCarriage, DriveTrain driveTrain) {
    	this.gearCarriage = gearCarriage;
    	requires(gearCarriage);
    	
    	this.driveTrain = driveTrain;
    	this.ravenTank = driveTrain.ravenTank;
    	this.safetyTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	safetyTimer.start();
    	gearCarriage.startStallTimer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println(" extending gear carriage.");
    	
    	if (gearCarriage.getIsAtExensionLimit()) {
    		return;
    	}
    	
    	// If the robot is currently re-orienting, wait for it to finish.
    	if (reorienting) {
    		return;
    	}
    	
    	if (gearCarriage.getIsStalled()) {
    		initializeReorientation();
    		return;
    	}
    	
    	gearCarriage.extend();
    	ravenTank.setCutPower(true);
    	ravenTank.userControlOfCutPower = false;
    	// System.out.println("Setting to cut power.");
    	
    }

    public void setReorientationComplete() {
    	reorienting = false;
    }
    
    public void initializeReorientation() {
		gearCarriage.retract();
		ravenTank.setCutPower(false);
		reorienting = true;
		reorientationAttempts++;
		
		// Each successive attempt, change direction and re-orient further.
		// This creates a growing, oscillating pattern back and forth.
		double degreesToTurn = reorientationAttempts * Calibrations.GearCarriageAutoPlaceReorientationBaseDegrees;
		
		if (reorientationAttempts % 2 == 0) {
			degreesToTurn *= 1;
		}
		
		Command reorientationCommand = new DriveTrainTurnRelativeDegreesForGearCarriage(driveTrain, degreesToTurn, this);
		Scheduler.getInstance().add(reorientationCommand);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = false;
    	isFinished = /* safetyTimer.get() >= 1 || */ gearCarriage.getIsAtExensionLimit();
    	
    	return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearCarriage.stop();
    	ravenTank.setCutPower(false);
    	ravenTank.userControlOfCutPower = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// Clean up as though the command ended.
    	end();
    }
}
