package org.usfirst.frc.team1188.robot.commands.gearcarriage;

import org.usfirst.frc.team1188.ravenhardware.Lighting;
import org.usfirst.frc.team1188.robot.subsystems.GearCarriage;
import edu.wpi.first.wpilibj.command.Command;


public class GearCarriageRetract extends Command {
	GearCarriage gearCarriage;
	Lighting stalledLighting;
	Lighting successLighting;

    public GearCarriageRetract(GearCarriage gearCarriage, Lighting stalledLighting, Lighting successLighting) {
    	this.gearCarriage = gearCarriage;
    	requires(gearCarriage);
    	
    	this.stalledLighting = stalledLighting;
    	this.successLighting = successLighting;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.stalledLighting.turnOff();
    	this.successLighting.turnOff();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (gearCarriage.getIsAtRetractionLimit() == false) {
        	gearCarriage.retract();
    	}

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return gearCarriage.getIsAtRetractionLimit();
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
