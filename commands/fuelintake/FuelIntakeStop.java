package org.usfirst.frc.team1188.robot.commands.fuelintake;

import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.FuelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class FuelIntakeStop extends Command {
	FuelIntake fuelIntake;
	
    public FuelIntakeStop() {
    	this.fuelIntake = Robot.fuelIntake;
    	requires(fuelIntake);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	fuelIntake.stop();
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