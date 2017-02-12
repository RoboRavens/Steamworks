package org.usfirst.frc.team1188.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearCarriage extends Subsystem{

	Joystick operationController;
	Solenoid extensionSolenoid;
	Solenoid retractionSolenoid;
	
	
	public GearCarriage(Joystick driveController, Solenoid extensionSolenoid, Solenoid retractionSolenoid) {
		this.operationController = driveController;
		this.extensionSolenoid = extensionSolenoid;
		this.retractionSolenoid = retractionSolenoid;
	}
	
	public void extendGearCarriage() {
		if (operationController.getRawButton(3)) {
			extensionSolenoid.set(true);
		}
		else {
			extensionSolenoid.set(false);
		}
	}
	
	public void retractGearCarriage() {
		if (operationController.getRawButton(4)) {
			retractionSolenoid.set(true);
		}
		else {
			retractionSolenoid.set(false);
		}	
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}


}
