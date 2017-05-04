package org.usfirst.frc.team1188.robot.commands.fuelshooter;

import org.usfirst.frc.team1188.robot.subsystems.FuelShooter;

import edu.wpi.first.wpilibj.command.Command;

public class FuelShooterStartPityTimer extends Command {
	FuelShooter fuelShooter;

    public FuelShooterStartPityTimer(FuelShooter fuelShooter) {
    	requires(fuelShooter);
    	this.fuelShooter = fuelShooter;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	fuelShooter.startPityTimer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
