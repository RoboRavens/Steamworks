package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.gearcarriage.*;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearCarriage extends Subsystem {
	Joystick operationController;
	CANTalon extensionMotor;
	DigitalInput extensionLimit;
	DigitalInput retractionLimit;
	Timer stallTimer;
	DigitalInput leftGearSensor;
	DigitalInput rightGearSensor;
	
	public boolean retractedState = true;
	public boolean overrideState = false;
	
	public GearCarriage(Joystick operationController, CANTalon extensionMotor, DigitalInput extensionLimit, DigitalInput retractionLimit, DigitalInput leftGearSensor, DigitalInput rightGearSensor) {
		this.operationController = operationController;
		this.extensionMotor = extensionMotor;
		this.extensionLimit = extensionLimit;
		this.retractionLimit = retractionLimit;
		this.stallTimer = new Timer();
		this.leftGearSensor = leftGearSensor;
		this.rightGearSensor = rightGearSensor;
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new GearCarriageStop(this));
    }
    
    public void setOverrideState(boolean state) {
    	this.overrideState = state;
    }
    
    
    public void extend() {
    	// System.out.println("extend method called");
    	retractedState = false;
    	
    	//if (this.getIsAtExensionLimit() == false) {
    		this.set(Calibrations.GearCarriagePowerMagnitude);
    	//}
    	//else {
    	//	this.set(0);
    	//}
    }
    
    public void retract() {
    	retractedState = true;
    	//if (this.getIsAtRetractionLimit() == false) {
    		this.set(-1 * Calibrations.GearCarriageRetractionPowerMagnitude);
    	//}
    	//else {
    	//	this.set(0);
    	//}
    }
    
    public void stop() {
    	this.set(0);
    }
    
    public void set(double magnitude) {
    	extensionMotor.set(-1 * magnitude);
    }
    
    // Commands that activate the carriage will call this method.
    public void startStallTimer() {
    	this.stallTimer.reset();
    	this.stallTimer.start();
    }
    
    public double getCurrent() {
    	double current = 0;
    	
    	current = extensionMotor.getOutputCurrent();
    	
    	
    	return current;
    }
    
    public boolean getIsStalled() {
    	System.out.print("Getting is stalled.");
    	System.out.println("Stall timer: " + stallTimer.get());
    	
    	boolean stalled = false;
    	
    	if (stallTimer.get() > Calibrations.GearCarriageStallTimerSeconds) {
    		stalled = true;
    		System.out.println("STALLED STALLED STALLED STALLED");
    	}
    	
    	/*
    	System.out.print("Getting is stalled.");
    	System.out.println("Output current: " + extensionMotor.getOutputCurrent());
    	boolean stalled = false;
    	
    	// If the stall timer *just* started, return false.
    	if (stallTimer.get() < Calibrations.GearCarriageStallTimerMinimumDurationForStall) {
    		stalled = false;
    	}
    	else {
    		if (extensionMotor.getOutputCurrent() >= Calibrations.GearCarriageStallCurrentThresholdAmps) {
    			stalled = true;
    		}
    	}*/
    	
    	return stalled;
    }
    
    public boolean getIsAtExensionLimit() {
    	/*
    	if (overrideState) {
    		return false;
    	}
    	*/
    	
    	return extensionLimit.get();
    }
    
    public boolean getIsAtRetractionLimit() {
    	/*
    	if (overrideState) {
    		return false;
    	}*/
    	
    	return retractionLimit.get();
    }
    
    public boolean getCarriageHasGear() {
    	boolean carriageHasGear = false;
    	
    	if (leftGearSensor.get() || rightGearSensor.get()) {
    		carriageHasGear = true;
    	}
    	
    	return carriageHasGear;
    }
}

