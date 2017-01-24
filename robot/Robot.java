
package org.usfirst.frc.team1188.robot;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import org.usfirst.frc.team1188.robot.commands.ExampleCommand;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1188.robot.subsystems.ExampleSubsystem;


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
	Joystick driveController = new Joystick(0);
	
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public final DriveTrain driveTrain = new DriveTrain(this, driveController);
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//myDrive = new RobotDrive(0,1,2,3);
		
		oi = new OI();
		chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
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
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		// double rightYAxisValue = left.getRawAxis(1);
		// System.out.println("REncoder: " + rightEncoder.get() + " LEncoder: " + leftEncoder.get());
		
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