package org.usfirst.frc.team1188.robot.subsystems;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.command.Subsystem;


public class ArduinoLeds extends Subsystem {
	PWM oneBit;
	PWM twoBit;
	PWM threeBit;
	PWM fourBit;
	
    public void initDefaultCommand() {
        setDisabledIdle();
    }
    
    public void setDisabledIdle() {
    	fourBit.setSpeed(0);
    	threeBit.setSpeed(0);
    	twoBit.setSpeed(0);
     	oneBit.setSpeed(0);
    }
    
    public void readyToReceiveGear() {
    	fourBit.setSpeed(0);
    	threeBit.setSpeed(0);
    	twoBit.setSpeed(0);
     	oneBit.setSpeed(1);
    }
    
    public void gearReceivedFromHp() {
    	fourBit.setSpeed(0);
    	threeBit.setSpeed(0);
    	twoBit.setSpeed(1);
     	oneBit.setSpeed(0);
    }
    
    public void gearCarriageDeploying() {
    	fourBit.setSpeed(0);
    	threeBit.setSpeed(0);
    	twoBit.setSpeed(1);
     	oneBit.setSpeed(1);
    }
    
    public void gearCarriageStalled() {
    	fourBit.setSpeed(0);
    	threeBit.setSpeed(1);
    	twoBit.setSpeed(0);
     	oneBit.setSpeed(0);
    }
    
    public void gearCarriageDeployedSuccessfully() {
    	fourBit.setSpeed(0);
    	threeBit.setSpeed(1);
    	twoBit.setSpeed(0);
     	oneBit.setSpeed(1);
    }
    
    public void climberRunning() {
    	fourBit.setSpeed(0);
    	threeBit.setSpeed(1);
    	twoBit.setSpeed(1);
     	oneBit.setSpeed(0);
    }
    
    public void climberStalled() {
    	fourBit.setSpeed(0);
    	threeBit.setSpeed(1);
    	twoBit.setSpeed(1);
     	oneBit.setSpeed(1);
    }
    
    public void transmissionAutoShiftedToLow() {
    	fourBit.setSpeed(1);
    	threeBit.setSpeed(0);
    	twoBit.setSpeed(0);
     	oneBit.setSpeed(0);
    }
    
    public void robotDisabledAndReadyBlueAlliance() {
    	fourBit.setSpeed(1);
    	threeBit.setSpeed(0);
    	twoBit.setSpeed(0);
     	oneBit.setSpeed(1);
    }
    
    public void robotDisabledAndReadyRedAlliance() {
    	fourBit.setSpeed(1);
    	threeBit.setSpeed(0);
    	twoBit.setSpeed(1);
     	oneBit.setSpeed(0);
    }
    
    public void robotEnabledAndReadyBlueAlliance() {
    	fourBit.setSpeed(1);
    	threeBit.setSpeed(0);
    	twoBit.setSpeed(1);
     	oneBit.setSpeed(1);
    }
    
    public void robotEnabledAndReadyRedAlliance() {
    	fourBit.setSpeed(1);
    	threeBit.setSpeed(1);
    	twoBit.setSpeed(0);
     	oneBit.setSpeed(0);
    }
    
    public void shooterRunning() {
    	fourBit.setSpeed(1);
    	threeBit.setSpeed(1);
    	twoBit.setSpeed(0);
     	oneBit.setSpeed(1);
    }
}

