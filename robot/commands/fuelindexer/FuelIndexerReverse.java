package org.usfirst.frc.team1188.robot.commands.fuelindexer;

import org.usfirst.frc.team1188.robot.subsystems.FuelIndexer;

import edu.wpi.first.wpilibj.command.Command;

public class FuelIndexerReverse extends Command {
	FuelIndexer fuelIndexer;
	
    public FuelIndexerReverse(FuelIndexer fuelIndexer) {
    	this.fuelIndexer = fuelIndexer;
    	requires(fuelIndexer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	fuelIndexer.reverse();
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
