
package org.usfirst.frc.team1188.robot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import org.usfirst.frc.team1188.robot.subsystems.*;
import org.usfirst.frc.team1188.robot.commands.fuelintake.*;
import org.usfirst.frc.team1188.robot.commands.fuelpump.*;
import org.usfirst.frc.team1188.robot.commands.gearcarriage.*;
import org.usfirst.frc.team1188.robot.commands.gearintake.*;
import org.usfirst.frc.team1188.robot.commands.drivetrain.*;
import org.usfirst.frc.team1188.robot.commands.shooter.*;
import org.usfirst.frc.team1188.robot.commands.climber.*;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static OI oi;
	
	Joystick driveController = new Joystick(0);
	Joystick operationController = new Joystick(1);
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	Compressor compressor = new Compressor();
	
	Solenoid gearIntakeExtensionSolenoid = new Solenoid(0);
	Solenoid gearIntakeRetractionSolenoid = new Solenoid(1);
	Solenoid gearCarriageExtensionSolenoid = new Solenoid(2);
	Solenoid gearCarriageRetractionSolenoid = new Solenoid(3);
	Solenoid shiftToLowGearSolenoid = new Solenoid(4);
	Solenoid shiftToHighGearSolenoid = new Solenoid(5);
	DigitalInput gearCarriageExtensionLimit = new DigitalInput(RobotMap.gearCarriageExtensionLimit);
	DigitalInput gearCarriageRetractionLimit = new DigitalInput(RobotMap.gearCarriageRetractionLimit);
	
	CANTalon gearCarriageMotor = new CANTalon(RobotMap.gearCarriageMotor);
	CANTalon fuelIntakeMotor = new CANTalon(RobotMap.fuelIntakeMotor);
	CANTalon climberMotor = new CANTalon(RobotMap.climberMotor);
	CANTalon fuelPumpMotor = new CANTalon(RobotMap.fuelPumpMotor);

	public final DriveTrain driveTrain = new DriveTrain(this, driveController, shiftToLowGearSolenoid, shiftToHighGearSolenoid);
	public final GearIntake gearIntake = new GearIntake(this.operationController, gearIntakeExtensionSolenoid, gearIntakeRetractionSolenoid);
	public final GearCarriage gearCarriage = new GearCarriage(this.operationController, gearCarriageMotor, gearCarriageExtensionLimit, gearCarriageRetractionLimit);
	public final FuelIntake fuelIntake = new FuelIntake(fuelIntakeMotor);
	public final Climber climber = new Climber(climberMotor);
	public final FuelPump fuelPump = new FuelPump(fuelPumpMotor);
	
	Encoder leftEncoder = new Encoder(0,1);
	Encoder rightEncoder = new Encoder(2,3);
	
	@Override
	public void robotInit() {
		//myDrive = new RobotDrive(0,1,2,3);
		
		oi = new OI(driveController, operationController);
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
		
		this.driveTrain.ravenTank.resetDriveGyro();
		
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
		//System.out.println("Navx.getAngle: " + navX.getAngle());
		Scheduler.getInstance().run();
		System.out.println("REncoder: " + rightEncoder.get() + " LEncoder: " + leftEncoder.get());
		

		
		if (oi.getDriveShiftLowButton()) {
			driveTrain.ravenTank.shiftToLowGear();
		}
		
		if (oi.getDriveShiftHighButton()) {
			driveTrain.ravenTank.shiftToHighGear();
		}
		
		if (oi.getDriveCutPowerMode()) {
			driveTrain.ravenTank.setCutPower(true);
		}
		else {
			driveTrain.ravenTank.setCutPower(false);
		}
		
		if (oi.getFuelIntakeCollectButton()) {
			Command fuelIntakeCollectCommand = new FuelIntakeCollect(fuelIntake);
			fuelIntakeCollectCommand.start();
		}
		else if (oi.getFuelIntakeReverseButton()) {
			Command fuelIntakeReverseCommand = new FuelIntakeReverse(fuelIntake);
			fuelIntakeReverseCommand.start();
			
		}
		else {
			Command fuelIntakeStopCommand = new FuelIntakeStop(fuelIntake);
			fuelIntakeStopCommand.start();
		}
		
		if (oi.getClimberClimbButton()) {
			Command climberClimbCommand = new ClimberClimb(climber);
			climberClimbCommand.start();
		}
		else {
			Command climberStopCommand = new ClimberStop(climber);
			climberStopCommand.start();
		}
		
		if (oi.getFuelPumpPumpButton()) {
			Command fuelPumpPumpCommand = new FuelPumpPump(fuelPump);
			fuelPumpPumpCommand.start();
		}
		else if (oi.getFuelPumpReverseButton()) {
			Command fuelPumpReverseCommand = new FuelPumpReverse(fuelPump);
			fuelPumpReverseCommand.start();
		}
		
		if (oi.getGearIntakeExtendButton()) {
			Command gearIntakeExtend = new GearIntakeExtend(gearIntake);
			gearIntakeExtend.start();
		}		
		else if (oi.getGearIntakeRetractButton()) {
			Command gearIntakeRetract = new GearIntakeRetract(gearIntake);
			gearIntakeRetract.start();
		}
		
		if (oi.getGearCarriageExtendButton()) {
			Command gearCarriageExtend = new GearCarriageExtend(gearCarriage);
			gearCarriageExtend.start();
		}		
		else if (oi.getGearCarriageRetractButton()) {
			Command gearCarriageRetract = new GearCarriageRetract(gearCarriage);
			gearCarriageRetract.start();
		}
		
		
		//if (oi.getGearIntakeRetractButton()) {
		//	gearIntake.retractGearIntake();
		//}
		
		/*else {
			gearIntakeExtensionSolenoid.set(false);
		}
		*/
		
		//if (oi.getGearIntakeExtendButton()) {
		//	gearIntake.extendGearIntake();
		//}
		
		/*else {
		 * 
			gearIntakeRetractionSolenoid.set(false);
		}
		*/
		//if (oi.getGearCarriageExtendButton()) {
		//	gearCarriage.extendGearCarriage();
		//}
		/*else {
			solenoidGearTipA.set(false);
		}
		*/
		//if (oi.getGearCarriageRetractButton()) {
		//	gearCarriage.retractGearCarriage();
		//}
		/*else {
			solenoidGearTipB.set(false);
		}
		*/
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