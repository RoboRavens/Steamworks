package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.fuelpump.*;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FuelPump extends Subsystem {
	CANTalon pumpMotor;
	public Timer pumpTimer;
	public boolean pumpingForward = true;

	public FuelPump(CANTalon pumpMotor) {
		this.pumpMotor = pumpMotor; 
		this.pumpTimer = new Timer();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new FuelPumpStop(this));
    }
    
    public void pump() {
    	this.set(Calibrations.FuelPumpPowerMagnitude * -1);
    }
    
    // To test - this will set the pump to speed mode, and then set it to a certain speed.
    // As far as I can tell, speed is in units of "position change per 10ms",
    // where position is "encoder ticks". So encoder ticks per 10ms.
    // So speed is units of "100 encoder ticks per second."
    /*
     
     public void setToSpeed() {
    	TalonControlMode controlMode;
    	controlMode = TalonControlMode.Speed;
    	pumpMotor.changeControlMode(controlMode);
    	pumpMotor.set(5);
    }
*/
    
    public void reverse() {
    	this.set(-1 * Calibrations.FuelPumpPowerMagnitude);
    }
    
    public void stop() {
    	this.set(0);
    }
    
    public void set(double magnitude){
    	// By default, 1 goes backwards and -1 goes forwards, so reverse the power.
    	pumpMotor.set(-1 * magnitude);
    }
    
    public int getRpm() {
    	// System.out.println("Fuel pump encoder velocity: " + pumpMotor.getEncVelocity());
    	return pumpMotor.getEncVelocity();
    }
    
    public void setToSpeed(int speed) {/*
    	this.pumpMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
    	this.pumpMotor.setP(.5);
    	this.pumpMotor.set(2000);
    	*/
    }
}

