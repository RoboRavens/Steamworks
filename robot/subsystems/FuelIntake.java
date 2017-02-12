package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.fuelintake.*;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class FuelIntake extends Subsystem {
	
	CANTalon intakeMotor1 = new CANTalon(RobotMap.fuelIntakeMotor);

    public void initDefaultCommand() {
        setDefaultCommand(new FuelIntakeStop(this));
    }
    
    public void collect() {
    	this.set(-1 * Calibrations.FuelIntakePowerMagnitude);
    }
    
    public void reverse() {
    	this.set(Calibrations.FuelIntakePowerMagnitude);
    }
    
    public void stop() {
    	this.set(0);
    }
    
    public void set(double magnitude) {
    	intakeMotor1.set(magnitude);
    }
}

