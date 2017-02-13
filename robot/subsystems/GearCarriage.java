package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.commands.gearcarriage.*;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearCarriage extends Subsystem {
	Joystick operationController;
	CANTalon extensionMotor;
	
	public GearCarriage(Joystick operationController, CANTalon extensionMotor, DigitalInput extensionLimit, DigitalInput retractionLimit) {
		this.operationController = operationController;
		this.extensionMotor = extensionMotor;
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new GearCarriageStop(this));
    }
    
    public void extend() {
    	
    }
    
    public void retract() {
    	
    }
    
    public void stop() {
    	
    }
}

