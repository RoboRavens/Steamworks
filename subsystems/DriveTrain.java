package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.ravenhardware.RavenTank;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveFPS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	Joystick driveController;
	public RavenTank ravenTank;
	
	public DriveTrain() {
		this.driveController = Robot.driveController;
		this.ravenTank = new RavenTank();
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
     
    	//setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveTrainDriveFPS(this, driveController));
    }
    
}

