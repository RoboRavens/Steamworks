package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.commands.gearintake.GearIntakeRetract;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearIntake extends Subsystem {
	Joystick operationController;
	Solenoid extensionSolenoid;
	Solenoid retractionSolenoid;
	
	public GearIntake(Joystick operationController, Solenoid extensionSolenoid, Solenoid retractionSolenoid) {
		this.operationController = operationController;
		this.extensionSolenoid = extensionSolenoid;
		this.retractionSolenoid = retractionSolenoid;
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new GearIntakeRetract(this));
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

