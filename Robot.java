
package org.usfirst.frc.team1188.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1188.robot.commands.fuelintake.*;
import org.usfirst.frc.team1188.robot.subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	/*
	Talon talonZero = new Talon(0);
	Talon talonOne = new Talon(1);
	Talon talonTwo = new Talon(2);
	
	Talon talonThree = new Talon(3);
	Talon talonFour = new Talon(4);
	Talon talonFive = new Talon(5);
	*/
	
	//Encoder rightEncoder = new Encoder(0,1);
	//Encoder leftEncoder = new Encoder(2,3);
	
	// Joystick left = new Joystick(0);
	// Joystick right = new Joystick(1);
	public static final Joystick driveController = new Joystick(0);
	public static final Joystick operationController = new Joystick(1);
	
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final FuelIntake fuelIntake = new FuelIntake();
	public OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		//myDrive = new RobotDrive(0,1,2,3);
		
		oi = new OI(driveController, operationController);
		// chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
	}
	
	public void operatorControl() {
		while (isOperatorControl() && isEnabled()) {
			Timer.delay(0.01);
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		// double rightYAxisValue = left.getRawAxis(1);
		// System.out.println("REncoder: " + rightEncoder.get() + " LEncoder: " + leftEncoder.get());
		
		// Fuel intake system
		if (oi.getFuelIntakeCollectButton()) {
			new FuelIntakeCollect();
		}
		else if (oi.getFuelIntakeReverseButton()) {
			new FuelIntakeReverse();
		}
		else {
			new FuelIntakeStop();
		}	
	}
	
	public static double getFilteredJoystickInput(double unfilteredInput, Joystick stick) {
		double filteredInput = unfilteredInput;
		double deadbandValue = 0.05;
		
		if (unfilteredInput >= deadbandValue * -1 && unfilteredInput <= deadbandValue) {
			filteredInput = 0;
		}
		

    	// System.out.println(deadbandValue);
		
		return filteredInput;
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}