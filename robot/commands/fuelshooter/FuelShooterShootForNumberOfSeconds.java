package org.usfirst.frc.team1188.robot.commands.fuelshooter;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.subsystems.FuelIndexer;
import org.usfirst.frc.team1188.robot.subsystems.FuelPump;
import org.usfirst.frc.team1188.robot.subsystems.FuelShooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class FuelShooterShootForNumberOfSeconds extends Command {
	FuelShooter shooter;
	FuelIndexer indexer;
	FuelPump pump;
	Timer shooterTimer;
	double secondsToShootFor;
	
    public FuelShooterShootForNumberOfSeconds(FuelShooter shooter, FuelIndexer indexer, FuelPump pump, double seconds) {
        requires(shooter);
        this.shooter = shooter;
        this.indexer = indexer;
        this.pump = pump;
        this.shooterTimer = new Timer();
        secondsToShootFor = seconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter.setRpm(Calibrations.ShooterEncoderTargetSpeed);
    	shooterTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.shoot();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean timeElapsed = false;
    	
    	if (shooterTimer.get() >= secondsToShootFor) {
    		timeElapsed = true;
    	}
    	
        return timeElapsed;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.allStop();
    	// stopPumping();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
