package org.usfirst.frc.team1188.robot.commands.fuelshooter;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.subsystems.FuelIndexer;
import org.usfirst.frc.team1188.robot.subsystems.FuelPump;
import org.usfirst.frc.team1188.robot.subsystems.FuelShooter;

import edu.wpi.first.wpilibj.command.Command;

public class FuelShooterShootSRXPID extends Command {
	FuelShooter shooter;
	FuelIndexer indexer;
	FuelPump pump;
	
    public FuelShooterShootSRXPID(FuelShooter shooter, FuelIndexer indexer, FuelPump pump) {
        requires(shooter);
        this.shooter = shooter;
        this.indexer = indexer;
        this.pump = pump;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooter.setRpm(Calibrations.ShooterEncoderTargetSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.shootSRXPID();

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	// shooter.stopPityTimer();
    	// stopPumping();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
