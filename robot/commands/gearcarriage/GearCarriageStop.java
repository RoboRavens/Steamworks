package org.usfirst.frc.team1188.robot.commands.gearcarriage;

import org.usfirst.frc.team1188.robot.subsystems.GearCarriage;
import edu.wpi.first.wpilibj.command.Command;

public class GearCarriageStop extends Command {
	GearCarriage gearCarriage;

    public GearCarriageStop(GearCarriage gearCarriage) {
    	this.gearCarriage = gearCarriage;
    	requires(gearCarriage);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Re-retracting the carriage doesn't do anything if the carriage
    	// is at the limit, but if it ever drifts from the limit, this will
    	// restore it to that position.
    	if (gearCarriage.retractedState) {
    		gearCarriage.retract();
    	}
    	else {
    		gearCarriage.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
