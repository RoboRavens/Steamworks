package org.usfirst.frc.team1188.robot.commands.lights;

import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.Lights;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LightsMaintainMatchState extends Command {
	Lights lights;
	Robot robot;
	
    public LightsMaintainMatchState(Lights lights, Robot robot) {
        this.lights = lights;
        this.robot = robot;
        requires(lights);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double matchTime = robot.driverStation.getMatchTime();
    	
    	if (matchTime <= 60 && matchTime > 57) {
    		toggleLights();
    	}
    	
    	if (matchTime <= 30 && matchTime > 25) {
    		toggleLights();
    	}
    	
    }
    
    protected void toggleLights() {
    	lights.quickToggle();
    	lights.maintainState();
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
