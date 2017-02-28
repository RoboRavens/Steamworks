package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.fuelshooter.*;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FuelShooter extends Subsystem {
	CANTalon shooterMotorLead;
	CANTalon shooterMotorFollower;
	
	public FuelShooter(CANTalon shooterMotorLead, CANTalon shooterMotorFollower) {
		this.shooterMotorLead = shooterMotorLead;
		this.shooterMotorFollower = shooterMotorFollower;
	}
    
    public void initDefaultCommand() {
    	setDefaultCommand(new FuelShooterStop(this));
    }
    
    public void rev() {
    	this.set(Calibrations.ShooterRevPowerMagnitude);
    }
    
    public void stop() {
    	this.set(0);
    }
    
    public void set(double magnitude) {
    	shooterMotorLead.set(magnitude);
    	shooterMotorFollower.set(magnitude);
    }
    
    public void getRpm() {
    	System.out.println("Shooter encoder velocity: " + shooterMotorLead.getEncVelocity());
    }
}

