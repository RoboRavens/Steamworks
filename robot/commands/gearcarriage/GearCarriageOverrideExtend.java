package org.usfirst.frc.team1188.robot.commands.gearcarriage;

import org.usfirst.frc.team1188.ravenhardware.Lighting;
import org.usfirst.frc.team1188.ravenhardware.RavenTank;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1188.robot.subsystems.GearCarriage;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


public class GearCarriageOverrideExtend extends Command {
	GearCarriage gearCarriage;
	DriveTrain driveTrain;
	RavenTank ravenTank;
	Timer safetyTimer;
	Lighting stalledLighting;
	Lighting successLighting;
	
    public GearCarriageOverrideExtend(GearCarriage gearCarriage, DriveTrain driveTrain, Lighting stalledLighting, Lighting successLighting) {
    	this.gearCarriage = gearCarriage;
    	requires(gearCarriage);
    	
    	this.driveTrain = driveTrain;
    	this.ravenTank = driveTrain.ravenTank;
    	this.safetyTimer = new Timer();
    	
    	this.stalledLighting = stalledLighting;
    	this.successLighting = successLighting;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	safetyTimer.start();
    	this.stalledLighting.turnOff();
    	this.successLighting.turnOff();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        	gearCarriage.extend();
        	driveTrain.ravenTank.setCutPower(true);
        	driveTrain.ravenTank.userControlOfCutPower = false;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = false;
    	
    	if (safetyTimer.get() >= Calibrations.GearCarriageStallTimerSeconds) {
    		isFinished = true;
    		stalledLighting.turnOn();
    	}
    	    	
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
    	end();
    }
}
