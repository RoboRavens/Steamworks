package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.fuelindexer.FuelIndexerFeed;
import org.usfirst.frc.team1188.robot.commands.fuelindexer.FuelIndexerStop;
import org.usfirst.frc.team1188.robot.commands.fuelpump.FuelPumpPump;
import org.usfirst.frc.team1188.robot.commands.fuelpump.FuelPumpPumpStaggered;
import org.usfirst.frc.team1188.robot.commands.fuelpump.FuelPumpStop;
import org.usfirst.frc.team1188.robot.commands.fuelshooter.*;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FuelShooter extends Subsystem {
	public CANTalon shooterMotorLead;
	CANTalon shooterMotorFollower;
	FuelIndexer indexer;
	FuelPump pump;
	Timer pityTimer;
	
	public FuelShooter(CANTalon shooterMotorLead, CANTalon shooterMotorFollower, FuelIndexer indexer, FuelPump pump) {
		this.shooterMotorLead = shooterMotorLead;
		this.shooterMotorFollower = shooterMotorFollower;
		this.indexer = indexer;
		this.pump = pump;
		this.pityTimer = new Timer();
	}
    
    public void initDefaultCommand() {
    	setDefaultCommand(new FuelShooterStop(this));
    }
    
    public void startPityTimer() {
    	this.pityTimer.start();
    }
    
    public void stopPityTimer() {
    	this.pityTimer.stop();
    	this.pityTimer.reset();
    }
    
    public void rev() {
    	this.set(Calibrations.ShooterRevPowerMagnitude);
    }
    
    public void stop() {
    	this.set(0);
    }
    
    public void allStop() {
    	indexer.stop();
    	pump.stop();
    	this.stopPumping();
    	this.stop();
    }
    
    public void set(double magnitude) {
    	shooterMotorLead.set(magnitude);
    	// shooterMotorFollower.set(magnitude);
    }
    
    public void shoot() {
    	this.setRpm(Calibrations.ShooterEncoderTargetSpeed);
    	
    	double rpm = this.getShooterRpm();
    	
    	// System.out.print("EncVel: " + rpm);
    	
    	// If the RPM is between the minimum and maximum acceptable, shoot.
    	// Otherwise, do not, and stop the indexer and pump.
    	if (rpm >= Calibrations.ShooterMinimumShootRpm && rpm <= Calibrations.ShooterMaximumShootRpm) {
    		this.pumpFuel();
    	}
    	else {
    		this.stopPumping();
    	}
    }
    
    public void pumpFuel() {
    	System.out.println(" SHOOTING!");
    	Scheduler.getInstance().add(new FuelIndexerFeed(indexer));
    	// Scheduler.getInstance().add(new FuelPumpPumpStaggered(pump));
    	Scheduler.getInstance().add(new FuelPumpPump(pump));
    	
    }
    
    public void stopPumping() {
    	// System.out.println(" Stopping pump.");
    	Scheduler.getInstance().add(new FuelIndexerStop(indexer));
    	Scheduler.getInstance().add(new FuelPumpStop(pump));
    }
    
    public double getShooterRpm() {
    	// System.out.println("Enc vel: " + Math.abs(shooterMotorLead.getEncVelocity()) + " ");
    	// return Math.abs(shooterMotorLead.getEncVelocity());
    	return Math.abs(shooterMotorLead.getSpeed() / 2);
    }
    
    public void setRpm(double targetRpm) {
    	double currentRpm = getShooterRpm();
    	if (currentRpm < Calibrations.ShooterMinimumShootRpm) {
    		// System.out.println("< Power: 1.");
    		this.set(1);
    	}
    	else if (currentRpm < targetRpm) {
    		this.set(Calibrations.ShooterRestorationPowerMagnitude);
    	}
    	else {
    		// The speed is greater than the target speed.
    		// Set the speed to the approximate maintenance power level, minus a small amount.
    		// It's better to error by dipping below, because then the power will get set back to 1.
    		double targetPowerMagnitude = targetRpm / Calibrations.ShooterOneHundredthPowerRpmRage;
    		
    		// Divide by 100 to bring the power level from a percentage to a decimal.
    		targetPowerMagnitude /= 100;
    		// targetPowerMagnitude += .15;
    		// targetPowerMagnitude -= .15;
    		targetPowerMagnitude += Calibrations.ShooterTargetPowerMagnitudeOffset;
    		
    		this.set(targetPowerMagnitude);
    		
    		// System.out.print("tRPM: " + rpm + " >=Power: " + targetPowerMagnitude);
    	}
    }

	public void shootSRXPID() {
		shooterMotorLead.changeControlMode(TalonControlMode.Speed);
		shooterMotorLead.set(Calibrations.ShooterPIDSpeedValue * -1);
		//shooterMotorLead.set(1);
		
    	double rpm = this.getShooterRpm();
    	
    	// System.out.print("EncVel: " + rpm);
    	
    	// If the RPM is between the minimum and maximum acceptable, shoot.
    	// Otherwise, do not, and stop the indexer and pump.
    	if ((rpm >= Calibrations.ShooterMinimumShootRpm && rpm <= Calibrations.ShooterMaximumShootRpm) || this.pityTimer.get() > Calibrations.ShooterPityTimerSeconds) {
    		if (this.pityTimer.get() > Calibrations.ShooterPityTimerSeconds) {
    			System.out.print("PITY TIMER");
    		}
    		this.pumpFuel();
    	}
    	else {
    		this.stopPumping();
    	}
	}
	
	public void pumpFuelBasedOnShooterRpm() {
		
	}

	/*
	public void lUDICROUSSPEED() {
		shooterMotorLead.setControlMode(mode);
		
	}
	*/
}

