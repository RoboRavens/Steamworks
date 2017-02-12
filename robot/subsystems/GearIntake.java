package org.usfirst.frc.team1188.robot.subsystems;

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
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void extendGearIntake() {
		if (operationController.getRawButton(6)) {
			extensionSolenoid.set(true);
		}
		else {
			extensionSolenoid.set(false);
		}
	}
	
	public void retractGearIntake() {
		System.out.println("something else");
		if (operationController.getRawButton(7)) {
			retractionSolenoid.set(true);
			System.out.println("inside of whatever");
		}
		else {
			retractionSolenoid.set(false);
		}
	}
}
