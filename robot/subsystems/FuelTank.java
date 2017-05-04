package org.usfirst.frc.team1188.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FuelTank extends Subsystem {
	Joystick operationController;
	Solenoid extensionSolenoid;
	Solenoid retractionSolenoid;
	
	public FuelTank(Joystick operationController, Solenoid extensionSolenoid, Solenoid retractionSolenoid) {
		this.operationController = operationController;
		this.extensionSolenoid = extensionSolenoid;
		this.retractionSolenoid = retractionSolenoid;
	}

    public void initDefaultCommand() {
    }
    
    public void extend() {
    	extensionSolenoid.set(true);
    	retractionSolenoid.set(false);
    }
    
    public void retract() {
    	retractionSolenoid.set(true);
    	extensionSolenoid.set(false);
    }
}