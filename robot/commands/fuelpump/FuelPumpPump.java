package org.usfirst.frc.team1188.robot.commands.fuelpump;

import org.usfirst.frc.team1188.robot.subsystems.FuelPump;
import edu.wpi.first.wpilibj.command.Command;

public class FuelPumpPump extends Command {
	FuelPump fuelPump;
	
    public FuelPumpPump(FuelPump fuelPump) {
        this.fuelPump = fuelPump;
        requires(fuelPump);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// System.out.println("Pumping.");
    	fuelPump.pump();
    	// fuelPump.setToSpeed(2000);
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
