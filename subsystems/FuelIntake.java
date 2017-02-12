package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.fuelintake.*;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class FuelIntake extends Subsystem {
	
	CANTalon intakeMotor1 = new CANTalon(RobotMap.intakeMotor1);
	CANTalon intakeMotor2 = new CANTalon(RobotMap.intakeMotor2);

    public void initDefaultCommand() {
        setDefaultCommand(new FuelIntakeStop());
    }
    
    public void collect() {
    	this.set(1);
    }
    
    public void reverse() {
    	this.set(-1);
    }
    
    public void stop() {
    	this.set(0);
    }
    
    public void set(double magnitude) {
    	intakeMotor1.set(magnitude);
    	intakeMotor2.set(magnitude);
    }
}

