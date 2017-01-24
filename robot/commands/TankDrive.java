package org.usfirst.frc.team1188.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1188.robot.subsystems.*;
import org.usfirst.frc.team1188.robot.*;

/**
 *
 */
public class TankDrive extends Command {
	/*
	Talon talonZero = new Talon(0);
	Talon talonOne = new Talon(1);
	Talon talonTwo = new Talon(2);
	
	Talon talonThree = new Talon(3);
	Talon talonFour = new Talon(4);
	Talon talonFive = new Talon(5);
	Joystick left = new Joystick(0);
	Joystick right = new Joystick(1);
	*/
	
	Robot robot;
    DriveTrain driveTrain;
    Joystick driveController;

    public TankDrive(DriveTrain driveTrain, Joystick driveController) {
    	requires(driveTrain);
    	this.driveTrain = driveTrain;
    	this.robot = driveTrain.robot;
    	this.driveController = driveController;
	}

	// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// System.out.println("Executing tank drive command.");
        double leftYAxisValue = driveController.getRawAxis(1);
    	double rightYAxisValue = driveController.getRawAxis(5);
    	double rightXAxisValue = driveController.getRawAxis(4);
    	
    	
    	driveTrain.ravenTank.drive(leftYAxisValue, rightYAxisValue, rightXAxisValue);
    	
    	/*
    	double leftYAxisValue = left.getRawAxis(1);
    	double rightYAxisValue = right.getRawAxis(1);
    	
    	double filteredLeftStickValue = Robot.getFilteredJoystickInput(leftYAxisValue, left);
    	double filteredRightStickValue = Robot.getFilteredJoystickInput(rightYAxisValue, left);
    	talonZero.set(filteredRightStickValue);
    	talonOne.set(filteredRightStickValue);
    	talonTwo.set(filteredRightStickValue);
    	
    	talonThree.set(filteredLeftStickValue * -1);
    	talonFour.set(filteredLeftStickValue * -1);
    	talonFive.set(filteredLeftStickValue * -1);
    	*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
