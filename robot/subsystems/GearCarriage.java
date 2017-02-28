package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.gearcarriage.*;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearCarriage extends Subsystem {
	Joystick operationController;
	CANTalon extensionMotor;
	DigitalInput extensionLimit;
	DigitalInput retractionLimit;
	
	public GearCarriage(Joystick operationController, CANTalon extensionMotor, DigitalInput extensionLimit, DigitalInput retractionLimit) {
		this.operationController = operationController;
		this.extensionMotor = extensionMotor;
		this.extensionLimit = extensionLimit;
		this.retractionLimit = retractionLimit;
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new GearCarriageStop(this));
    }
    
    public void extend() {
    	System.out.println("extend method called");
    	if (this.getIsAtExensionLimit() == false) {
    		this.set(Calibrations.GearCarriagePowerMagnitude);
    	}
    	else {
    		this.set(0);
    	}
    }
    
    public void retract() {
    	if (this.getIsAtRetractionLimit() == false) {
    		this.set(-1 * Calibrations.GearCarriagePowerMagnitude);
    	}
    	else {
    		this.set(0);
    	}
    }
    
    public void stop() {
    	this.set(0);
    }
    
    public void set(double magnitude) {
    	extensionMotor.set(-1 * magnitude);
    }
    
    public boolean getIsAtExensionLimit() {
    	return extensionLimit.get();
    }
    
    public boolean getIsAtRetractionLimit() {
    	return retractionLimit.get();
    }
}

