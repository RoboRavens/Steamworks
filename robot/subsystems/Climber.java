package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.climber.ClimberStop;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	CANTalon climberMotor;
	
	public Climber(CANTalon climberMotor) {
		this.climberMotor = climberMotor;
	}

    public void initDefaultCommand() {
        setDefaultCommand(new ClimberStop(this));
    }
    
    public void climb() {
    	this.set(Calibrations.ClimberClimbPowerMagnitude);
    }
    
    public void stop() {
    	this.set(0);
    }
        
    public void set(double magnitude) {
    	// magnitude = magnitude *= -1;
    	climberMotor.set(magnitude);
    }
}