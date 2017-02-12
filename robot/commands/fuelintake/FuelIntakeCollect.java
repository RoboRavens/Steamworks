package org.usfirst.frc.team1188.robot.commands.fuelintake;

import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.FuelIntake;

import edu.wpi.first.wpilibj.command.Command;

public class FuelIntakeCollect extends Command {
	FuelIntake fuelIntake;
	
    public FuelIntakeCollect(FuelIntake fuelIntake) {
    	this.fuelIntake = fuelIntake;
    	requires(fuelIntake);

		System.out.println("Creating FI collect command.");
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {

		System.out.println("Initializing FI collect command.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		System.out.println("Executing FI collect command.");
    	fuelIntake.collect();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {

    	System.out.println("FI collect ended.");

    	System.out.println("FI collect ended.");

    	System.out.println("FI collect ended.");
    	System.out.println("FI collect ended.");
    	System.out.println("FI collect ended.");
    	System.out.println("FI collect ended.");
    	System.out.println("FI collect ended.");
    	System.out.println("FI collect ended.");
    	System.out.println("FI collect ended.");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("FI collect interrrrrrrrrrrrrrrrrrrupted.");
    	System.out.println("FI collect interrrrrrrrrrrrrrrrrrrupted.");
    	System.out.println("FI collect interrrrrrrrrrrrrrrrrrrupted.");
    	System.out.println("FI collect interrrrrrrrrrrrrrrrrrrupted.");
    	System.out.println("FI collect interrrrrrrrrrrrrrrrrrrupted.");
    	System.out.println("FI collect interrrrrrrrrrrrrrrrrrrupted.");
    	System.out.println("FI collect interrrrrrrrrrrrrrrrrrrupted.");
    	System.out.println("FI collect interrrrrrrrrrrrrrrrrrrupted.");
    }
}
