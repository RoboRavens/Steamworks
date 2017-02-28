package org.usfirst.frc.team1188.robot.commands.gearcarriage;

import org.usfirst.frc.team1188.robot.subsystems.GearCarriage;
import edu.wpi.first.wpilibj.command.Command;


public class GearCarriageRetract extends Command {
	GearCarriage gearCarriage;

    public GearCarriageRetract(GearCarriage gearCarriage) {
    	this.gearCarriage = gearCarriage;
    	requires(gearCarriage);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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
