package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.fuelpump.*;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class FuelPump extends Subsystem {
	CANTalon pumpMotor;

	public FuelPump(CANTalon pumpMotor) {
		this.pumpMotor = pumpMotor; 
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new FuelPumpStop(this));
    }
    
    public void pump() {
    	this.set(Calibrations.FuelPumpPowerMagnitude);
    }
    
    // To test - this will set the pump to speed mode, and then set it to a certain speed.
    // As far as I can tell, speed is in units of "position change per 10ms",
    // where position is "encoder ticks". So encoder ticks per 10ms.
    // So speed is units of "100 encoder ticks per second."
    public void setToSpeed() {
    	TalonControlMode controlMode;
    	controlMode = TalonControlMode.Speed;
    	pumpMotor.changeControlMode(controlMode);
    	pumpMotor.set(5);
    }
    
    public void reverse() {
    	this.set(-1 * Calibrations.FuelPumpPowerMagnitude);
    }
    
    public void stop() {
    	this.set(0);
    }
    
    public void set(double magnitude){
    	pumpMotor.set(magnitude);
    }
}

