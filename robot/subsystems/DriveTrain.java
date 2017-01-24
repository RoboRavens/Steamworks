package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.ravenhardware.RavenTank;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	public Robot robot;
	Joystick driveController;
	public RavenTank ravenTank;
	
	public DriveTrain(Robot robot, Joystick driveController) {
		this.robot = robot;
		this.driveController = driveController;
		this.ravenTank = new RavenTank(robot);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
     
    	//setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TankDrive(this, driveController));
    }
    
}

