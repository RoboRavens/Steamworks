package org.usfirst.frc.team1188.robot.commands.climber;

import org.usfirst.frc.team1188.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.command.Command;

public class ClimberStop extends Command {
	Climber climber;

    public ClimberStop(Climber climber) {
    	this.climber = climber;
    	requires(climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	climber.stop();
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