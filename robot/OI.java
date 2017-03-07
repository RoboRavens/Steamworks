package org.usfirst.frc.team1188.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	Joystick driveController;
	Joystick operationController;
	
	// Fuel Intake
	public Button fuelIntakeCollectButton;
	public Button fuelIntakeReverseButton;
	
	// Fuel Pump
	public Button fuelPumpPumpStaggeredButton;
	public Button fuelPumpReverseButton;
	public Button fuelPumpOverrideShooterIsSpinningButton;
	
	// Shooter
	public Button shooterRevButton;
	public Button shooterOverrideRpmButton;
	public Button shooterShootButton;
	
	// Climber
	public Button climberClimbButton;
	public Button climberOverrideStallProtectionButton;
	public Button climberOverrideClimberDirectionButton;
	
	// Gear Intake
	public Button gearIntakeExtendButton;
	public Button gearIntakeRetractButton;
	
	// Gear Carriage
	public Button gearCarriageExtendButton;
	public Button gearCarriageRetractButton;
	public Button gearCarriageOverrideExtensionLimitButton;
	public Button gearCarriageOverrideRetractionLimitButton;
	
	
	public OI(Joystick driveController, Joystick operationController) {
		this.driveController = driveController;
		this.operationController = operationController;
		
		initializeButtons();
	}
	
	protected void initializeButtons() {
		// Fuel Intake
		fuelIntakeCollectButton = new JoystickButton(operationController, ControlsMap.fuelIntakeCollectButton);
		fuelIntakeReverseButton = new JoystickButton(operationController, ControlsMap.fuelIntakeReverseButton);
		
		// Fuel Pump
		fuelPumpPumpStaggeredButton = new JoystickButton(operationController, ControlsMap.fuelPumpPumpButton);
		fuelPumpReverseButton = new JoystickButton(operationController, ControlsMap.fuelPumpReverseButton);
		fuelPumpOverrideShooterIsSpinningButton = new JoystickButton(operationController, ControlsMap.fuelPumpOverrideShooterIsSpinningButton);
		
		// Shooter
		shooterRevButton = new JoystickButton(operationController, ControlsMap.shooterRevButton);
		shooterOverrideRpmButton = new JoystickButton(operationController, ControlsMap.shooterOverrideShootButton);
		shooterShootButton = new JoystickButton(operationController, ControlsMap.shooterShootButton);

		// Climber	
		climberClimbButton = new JoystickButton(operationController, ControlsMap.climberClimbButton);
		climberOverrideStallProtectionButton = new JoystickButton(operationController, ControlsMap.climberOverrideStallProtectionButton);
		climberOverrideClimberDirectionButton = new JoystickButton(operationController, ControlsMap.climberOverrideClimberDirectionButton);
		
		// Gear Intake
		gearIntakeExtendButton = new JoystickButton(operationController, ControlsMap.gearIntakeExtendButton);
		// gearIntakeRetractButton = new JoystickButton(operationController, ControlsMap.gearIntakeRetractButton);
		
		// Gear Carriage
		gearCarriageExtendButton = new JoystickButton(operationController, ControlsMap.gearCarriageExtendButton);
		gearCarriageRetractButton = new JoystickButton(operationController, ControlsMap.gearCarriageRetractButton);
		gearCarriageOverrideExtensionLimitButton = new JoystickButton(operationController, ControlsMap.gearCarriageOverrideExtensionLimitButton);
		gearCarriageOverrideRetractionLimitButton = new JoystickButton(operationController, ControlsMap.gearCarriageOverrideRetractionLimitButton);
	}
	
	// Shifters
	public boolean getDriveShiftLowButton() {
		boolean shiftLow = driveController.getRawButton(ControlsMap.driveShiftToLowGearButton) || operationController.getRawButton(ControlsMap.operationShiftToLowGearButton);  
		
		return shiftLow; 
	}
	
	public boolean getDriveShiftHighButton() {
		return driveController.getRawButton(ControlsMap.driveShiftToHighGearButton);
	}
	
	public boolean getDriveCutPowerMode() {
		return driveController.getRawAxis(ControlsMap.driveCutPowerAxis) > .25;
	}
	
	public boolean getOperatorIsOverriding() {
		return operationController.getRawAxis(ControlsMap.operatorOverrideAxis) > .25;
	}
	
	public boolean getOperatorIsAiming() {
		return operationController.getRawAxis(ControlsMap.operationFlashlightAimAxis) > .25;
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
	
	/*
	public boolean getShooterStopButton() {
		return operationController.getRawButton(ControlsMap.shooterStopButton);
	}
	
	public boolean getShooterOverrideShootButton() {
		return operationController.getRawButton(ControlsMap.shooterOverrideShootButton);
	}
	*/
	
	// Gear intake/placement
	public boolean getGearIntakeExtendButton() {
		return operationController.getRawButton(ControlsMap.gearIntakeExtendButton);
	}
	
	/*
	public boolean getGearIntakeRetractButton() {
		return operationController.getRawButton(ControlsMap.gearIntakeRetractButton);
	}
	*/
	
	public boolean getGearCarriageExtendButton() {
		return operationController.getRawButton(ControlsMap.gearCarriageExtendButton);
	}
	
	public boolean getGearCarriageRetractButton() {
		return operationController.getRawButton(ControlsMap.gearCarriageRetractButton);
	}
	
	public boolean getClimberClimbButton() {
		return operationController.getRawButton(ControlsMap.climberClimbButton);
	}
	
	public boolean getFuelPumpPumpButton() {
		return operationController.getRawButton(ControlsMap.fuelPumpPumpButton);
	}
	
	public boolean getFuelPumpReverseButton() {
		return operationController.getRawButton(ControlsMap.fuelPumpReverseButton);
	}
	
}
