package org.usfirst.frc.team1188.robot.commands.fuelpump;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.subsystems.FuelPump;

import edu.wpi.first.wpilibj.command.Command;

public class FuelPumpPumpStaggered extends Command {
	FuelPump fuelPump;
	
    public FuelPumpPumpStaggered(FuelPump fuelPump) {
        this.fuelPump = fuelPump;
        requires(fuelPump);


    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	if (fuelPump.pumpTimer.get() == 0) {
    		fuelPump.pumpTimer.start();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (fuelPump.pumpingForward) {
    		pumpForward();
    	}
    	else {
    		pumpBackward();
    	}
    }
    	
	protected void pumpForward() {
		System.out.println("Pump timer: " + fuelPump.pumpTimer.get());
		
		// For the first half second, ALWAYS pump forward.
		if (fuelPump.pumpTimer.get() < .5) {
			System.out.println("less than a half second, pumping forward");
			fuelPump.pump();
			
			return;
		}
		
		// If after the first half second, the pump is stalled, pump backward.
		if (Math.abs(fuelPump.getRpm()) < Calibrations.fuelPumpStallRpm) {
			System.out.println("stall, pump backwards");
			fuelPump.pumpingForward = false;
			fuelPump.pumpTimer.reset();
			fuelPump.pumpTimer.start();
			fuelPump.reverse();
		}
	}
	
	protected void pumpBackward() {
		// When pumping backward, simply run the pump for a period of time.
		// Then try forward again.
		if (fuelPump.pumpTimer.get() < Calibrations.fuelPumpMinimumBackwardPumpSeconds) {
			fuelPump.reverse();
		}
		else {
			fuelPump.pumpingForward = true;
			fuelPump.pumpTimer.reset();
			fuelPump.pumpTimer.start();
			fuelPump.pump();
		}
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
