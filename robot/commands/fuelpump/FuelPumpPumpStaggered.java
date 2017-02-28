package org.usfirst.frc.team1188.robot.commands.fuelpump;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.subsystems.FuelPump;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class FuelPumpPumpStaggered extends Command {
	FuelPump fuelPump;
	Timer pumpTimer;
	boolean pumpingForward;
	
    public FuelPumpPumpStaggered(FuelPump fuelPump) {
        this.fuelPump = fuelPump;
        requires(fuelPump);
        
        pumpTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pumpingForward = true;
    	pumpTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (pumpingForward) {
    		pumpForward();
    	}
    	else {
    		pumpBackward();
    	}
    }
    	
	protected void pumpForward() {
		// For the first half second, ALWAYS pump forward.
		if (pumpTimer.get() < .5) {
			fuelPump.pump();
			
			return;
		}
		
		// If after the first half second, the pump is stalled, pump backward.
		if (fuelPump.getRpm() < Calibrations.fuelPumpStallRpm) {
			pumpingForward = false;
			pumpTimer.reset();
			pumpTimer.start();
			fuelPump.reverse();
		}
	}
	
	protected void pumpBackward() {
		// When pumping backward, simply run the pump for a period of time.
		// Then try forward again.
		if (pumpTimer.get() < Calibrations.fuelPumpMinimumBackwardPumpSeconds) {
			fuelPump.reverse();
		}
		else {
			pumpingForward = true;
			pumpTimer.reset();
			pumpTimer.start();
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
