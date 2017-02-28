package org.usfirst.frc.team1188.robot.commands.gearcarriage;

import org.usfirst.frc.team1188.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1188.robot.subsystems.GearCarriage;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


public class GearCarriageExtend extends Command {
	GearCarriage gearCarriage;
	DriveTrain driveTrain;
	Timer safetyTimer;
	
    public GearCarriageExtend(GearCarriage gearCarriage, DriveTrain driveTrain) {
    	this.gearCarriage = gearCarriage;
    	requires(gearCarriage);
    	
    	this.driveTrain = driveTrain;
    	this.safetyTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	safetyTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(" extending gear carraige.");
    	if (gearCarriage.getIsAtExensionLimit() == false) {
        	gearCarriage.extend();
        	driveTrain.ravenTank.setCutPower(true);
        	driveTrain.ravenTank.userControlOfCutPower = false;
        	System.out.println("Setting to cut power.");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = false;
    	isFinished = safetyTimer.get() >= 1 || gearCarriage.getIsAtExensionLimit();
    	
    	return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	gearCarriage.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
