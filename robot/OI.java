package org.usfirst.frc.team1188.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	Joystick driveController;
	Joystick operationController;
	
	public OI(Joystick driveController, Joystick operationController) {
		this.driveController = driveController;
		this.operationController = operationController;
	}
	
	// Shifters
	public boolean getShifterShiftLowButton() {
		return operationController.getRawButton(ControlsMap.shifterShiftLowButton);
	}
	
	public boolean getShifterShiftHighButton() {
		return operationController.getRawButton(ControlsMap.shifterShiftHighButton);
	}
	
	// Fuel intake
	public boolean getFuelIntakeCollectButton() {
		return operationController.getRawButton(ControlsMap.fuelIntakeCollectButton);
	}
	
	public boolean getFuelIntakeReverseButton() {
		return operationController.getRawButton(ControlsMap.fuelIntakeReverseButton);
	}
	
	// Shooter
	public boolean getShooterRevButton() {
		return operationController.getRawButton(ControlsMap.shooterRevButton);
	}
	
	public boolean getShooterShootButton() {
		return operationController.getRawButton(ControlsMap.shooterShootButton);
	}
	
	public boolean getShooterStopButton() {
		return operationController.getRawButton(ControlsMap.shooterStopButton);
	}
	
	public boolean getShooterOverrideShootButton() {
		return operationController.getRawButton(ControlsMap.shooterOverrideShootButton);
	}
	
	// Gear intake/placement
	public boolean getGearIntakeExtendButton() {
		return operationController.getRawButton(ControlsMap.gearIntakeExtendButton);
	}
	
	public boolean getGearIntakeRetractButton() {
		return operationController.getRawButton(ControlsMap.gearIntakeRetractButton);
	}
	
	public boolean getGearCarriageExtendButton() {
		return operationController.getRawButton(ControlsMap.gearCarriageExtendButton);
	}
	
	public boolean getGearCarriageRetractButton() {
		return operationController.getRawButton(ControlsMap.gearCarriageRetractButton);
	}
	
}
