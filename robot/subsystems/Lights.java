package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.ravenhardware.Lighting;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.lights.LightsMaintainMatchState;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Lights extends Subsystem {
	Lighting intakeReadyLighting;
	Lighting stalledLighting;
	Lighting successLighting;
	Robot robot;

	public Lights(Lighting intakeReadyLighting, Lighting stalledLighting, Lighting successLighting, Robot robot) {
		this.intakeReadyLighting = intakeReadyLighting;
		this.stalledLighting = stalledLighting;
		this.successLighting = successLighting;
		this.robot = robot;
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new LightsMaintainMatchState(this, robot));
    }
    
    public void maintainState() {
    	intakeReadyLighting.maintainState();
    	stalledLighting.maintainState();
    	successLighting.maintainState();
    }
    
    public void quickToggle() {
    	intakeReadyLighting.quickToggle();
    	stalledLighting.quickToggle();
    	successLighting.quickToggle();
    }
}

