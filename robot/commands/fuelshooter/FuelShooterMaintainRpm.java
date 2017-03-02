package org.usfirst.frc.team1188.robot.commands.fuelshooter;

import org.usfirst.frc.team1188.robot.subsystems.FuelShooter;

import edu.wpi.first.wpilibj.command.Command;

public class FuelShooterMaintainRpm extends Command {
	FuelShooter shooter;
	double targetRpm;
	
    public FuelShooterMaintainRpm(FuelShooter shooter, double targetRpm) {
        this.shooter = shooter;
        requires(shooter);
        
        this.targetRpm = targetRpm;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter.setRpm(targetRpm);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.setRpm(targetRpm);
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
