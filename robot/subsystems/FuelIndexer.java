package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.fuelindexer.FuelIndexerStop;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FuelIndexer extends Subsystem {
	CANTalon indexMotor;

	public FuelIndexer(CANTalon indexMotor) {
		this.indexMotor = indexMotor;
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new FuelIndexerStop(this));
    }
    
    public void feed() {
    	this.set(Calibrations.FuelIndexerPowerMagnitude);
    }
    
    public void reverse() {
    	this.set(-1 * Calibrations.FuelIndexerPowerMagnitude);
    }
    
    public void stop() {
    	this.set(0);
    }
    
    public void set(double magnitude) {
    	indexMotor.set(-1 * magnitude);
    }
}