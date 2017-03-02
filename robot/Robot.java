
package org.usfirst.frc.team1188.robot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1188.robot.subsystems.*;
import org.usfirst.frc.team1188.robot.commands.fuelintake.*;
import org.usfirst.frc.team1188.robot.commands.fuelpump.*;
import org.usfirst.frc.team1188.robot.commands.fuelshooter.*;
import org.usfirst.frc.team1188.robot.commands.gearcarriage.*;
import org.usfirst.frc.team1188.robot.commands.gearintake.*;
import org.usfirst.frc.team1188.robot.commands.fuelindexer.*;
import org.usfirst.frc.team1188.ravenhardware.Lighting;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.*;
import org.usfirst.frc.team1188.robot.commands.climber.*;

import com.ctre.CANTalon;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static OI oi;
	
	public DriverStation driverStation;

	Diagnostics diagnostics = new Diagnostics(this);
	
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
	CANTalon fuelShooterMotorLead = new CANTalon(RobotMap.fuelShooterMotorLead);
	CANTalon fuelShooterMotorFollower = new CANTalon(RobotMap.fuelShooterMotorFollower);
	CANTalon fuelIndexerMotor = new CANTalon(RobotMap.fuelIndexerMotor);
	
	public final DriveTrain driveTrain = new DriveTrain(this, driveController, shiftToLowGearSolenoid, shiftToHighGearSolenoid);
	public final GearIntake gearIntake = new GearIntake(this.operationController, gearIntakeExtensionSolenoid, gearIntakeRetractionSolenoid);
	public final GearCarriage gearCarriage = new GearCarriage(this.operationController, gearCarriageMotor, gearCarriageExtensionLimit, gearCarriageRetractionLimit);
	public final FuelIntake fuelIntake = new FuelIntake(fuelIntakeMotor);
	public final Climber climber = new Climber(climberMotor);
	public final FuelPump fuelPump = new FuelPump(fuelPumpMotor);
	public final FuelIndexer fuelIndexer = new FuelIndexer(fuelIndexerMotor);
	public final FuelShooter fuelShooter = new FuelShooter(fuelShooterMotorLead, fuelShooterMotorFollower, fuelIndexer, fuelPump);
	
	public boolean isRedAlliance;
	
	// boolean hasDriven = false;
	
	Timer autonomousTimer = new Timer();
	String autoFromDashboard;

	Relay intakeReadyRelay = new Relay(RobotMap.intakeReadyLightRelay);
	Relay carriageStalledRelay = new Relay(RobotMap.carriageStalledLightRelay);
	Relay carriageExtendedRelay = new Relay(RobotMap.carriageExtendedLightRelay);
	// Relay underglowRelay = new Relay(RobotMap.underglowLightRelay);
	Relay flashlightRelay = new Relay(RobotMap.flashlightRelay);
 	
	Lighting intakeReadyLighting = new Lighting(intakeReadyRelay);
	Lighting carriageStalledLighting = new Lighting(carriageStalledRelay);
	Lighting carriageExtendedLighting = new Lighting(carriageExtendedRelay);
	// Lighting underglowLighting = new Lighting(underglowRelay);
	Lighting flashlightLighting = new Lighting(flashlightRelay);
	
	Lights lights = new Lights(intakeReadyLighting, carriageStalledLighting, carriageExtendedLighting, this);
	
 	
 	CameraServer server;
	
 	public Robot() {
		server = CameraServer.getInstance();		
		server.startAutomaticCapture();
	}
 	
	@Override
	public void robotInit() {
		driverStation = DriverStation.getInstance();
		oi = new OI(driveController, operationController);
		// underglowLighting.turnOn();
		
		SmartDashboard.putData("Auto mode", chooser);
	}

	@Override
	public void disabledInit() {
		
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
				
		autoFromDashboard = SmartDashboard.getString("DB/String 0", "myDefaultData");
		
		outputAutoModeToDashboardStringOne(autoFromDashboard);
		
		Alliance alliance = driverStation.getAlliance();
		
		String allianceString = "";
		
		if (alliance.compareTo(Alliance.Blue) == 0) {
			allianceString = "Blue alliance";
			this.isRedAlliance = false;
		}
		else if (alliance.compareTo(Alliance.Red) == 0) {
			allianceString = "Red alliance";
			this.isRedAlliance = true;
		}
		else {
			allianceString = "Alliance not identified.";
			this.isRedAlliance = false;
		}
	
		SmartDashboard.putString("DB/String 4", allianceString);
	}
	
	public void outputAutoModeToDashboardStringOne(String autoMode) {
		String autonomousModeConfirmation = "Confirmed - auto mode: ";
		String autonomousModeName = "";
	
		switch (autoFromDashboard.toUpperCase()) {
			case Calibrations.AutonomousGearToMiddleLift:
				autonomousModeName += "Gear to middle peg.";
				break;
			case Calibrations.AutonomousGearToLeftLift:
				autonomousModeName += "Gear to left peg.";
				break;
			case Calibrations.AutonomousGearToRightLift:
				autonomousModeName += "Gear to right peg.";
				break;
			case Calibrations.AutonomousShootHighGoal:
				autonomousModeName += "Shoot high goals.";
				
				if (this.isRedAlliance) {
					autonomousModeName += " Red.";;
				}
				else {
					autonomousModeName += " Blue.";
				}
				
				break;
			case Calibrations.AutonomousRankingPoint:
				autonomousModeName += "Ranking point.";
				if (this.isRedAlliance) {
					autonomousModeName += " Red.";;
				}
				else {
					autonomousModeName += " Blue.";
				}
				break;
			case Calibrations.AutonomousCrossBaseLine:
				autonomousModeName += "Driving forward to cross base line.";
				break;
				
			case Calibrations.AutonomousGeartoMiddleLiftScoreHigh:
				autonomousModeName += "Gear->mid, goals.";
				if (this.isRedAlliance) {
					autonomousModeName += " Red.";;
				}
				else {
					autonomousModeName += " Blue.";
				}
				
			default:
				autonomousModeConfirmation = "ERROR!";
				autonomousModeName = "Mode not recognized.";
				break;	
		}
		
		putSmartDashboardAutonomousMode(autonomousModeConfirmation, autonomousModeName);
	}
	
	public boolean isRedAlliance() {
		boolean isRedAlliance = false;

		Alliance alliance = driverStation.getAlliance();
		
		if (alliance.compareTo(Alliance.Red) == 0) {
			isRedAlliance = true;
		}
		
		return isRedAlliance;
	}
	
	
	public Command getAutonomousCommand() {
		Command autonomousCommand = new AutonomousDoNothing();
		
		switch (autoFromDashboard.toUpperCase()) {
			case Calibrations.AutonomousGearToMiddleLift:
				autonomousCommand = new AutonomousPlaceGearOnMiddleLift(driveTrain, gearCarriage, carriageStalledLighting, carriageExtendedLighting);
				break;
			case Calibrations.AutonomousGearToLeftLift:
				autonomousCommand = new AutonomousPlaceGearOnLeftLift(driveTrain, gearCarriage);
				break;
			case Calibrations.AutonomousGearToRightLift:
				autonomousCommand = new AutonomousPlaceGearOnRightLift(driveTrain, gearCarriage);
				break;
			case Calibrations.AutonomousShootHighGoal:
				if (this.isRedAlliance) {
					autonomousCommand = new AutonomousShootHighGoalCrossBaseLineRedAlliance(driveTrain, fuelPump, fuelIndexer, fuelShooter);
				}
				else {
					autonomousCommand = new AutonomousShootHighGoalCrossBaseLineBlueAlliance(driveTrain, fuelPump, fuelIndexer, fuelShooter);
				}
				break;
			case Calibrations.AutonomousRankingPoint:
				if (this.isRedAlliance) {
					autonomousCommand = new AutonomousCollectHopperShootGoalsRedAlliance(driveTrain, fuelPump, fuelIndexer, fuelShooter);
				}
				else {
					autonomousCommand = new AutonomousCollectHopperShootGoalsBlueAlliance(driveTrain, fuelPump, fuelIndexer, fuelShooter);
				}
				
				break;
			case Calibrations.AutonomousGeartoMiddleLiftScoreHigh:
				if (this.isRedAlliance) {
					autonomousCommand = new AutonomousPlaceGearOnMiddleLiftShootHighGoalsRedAlliance(driveTrain, gearCarriage, carriageStalledLighting, carriageExtendedLighting, fuelPump, fuelIndexer, fuelShooter);
				}
				else {
					autonomousCommand = new AutonomousPlaceGearOnMiddleLiftShootHighGoalsBlueAlliance(driveTrain, gearCarriage, carriageStalledLighting, carriageExtendedLighting, fuelPump, fuelIndexer, fuelShooter);
				}
				
				break;
			case Calibrations.AutonomousCrossBaseLine:
				autonomousCommand = new AutonomousCrossBaseLine(driveTrain);
				break;
		}
	
		return autonomousCommand;
	}
	
	public void putSmartDashboardAutonomousMode(String autonomousModeConfirmation, String autonomousModeName) {
		SmartDashboard.putString("DB/String 1", autonomousModeConfirmation);
		SmartDashboard.putString("DB/String 6", autonomousModeName);
	}
	
	public void autonomousInit() {
		// Zero the gyro, grab the selected autonomous mode, and get to work.
		driveTrain.ravenTank.setGyroTargetHeadingToCurrentHeading();
		autonomousCommand = getAutonomousCommand();
		
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}		
	}

	// Nothing needs to happen here other than running the scheduler.
	// The chosen command will be executed and eventually finish.
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		diagnostics.outputAutonomousDiagnostics();
	}

	public void teleopInit() {
		driveTrain.ravenTank.setGyroTargetHeadingToCurrentHeading();
		
		// Cancel the autonomous command in the case that it did not terminate.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();	
		}
	}
	
	// What is this method? Can it go away?
	public void operatorControl() {
		while (isOperatorControl() && isEnabled()) {
			Timer.delay(0.01);
		}
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		System.out.println("TELEOP DIAGNOSTICS");
		diagnostics.outputTeleopDiagnostics();
		
		runOperatorControls();
	}
	
	public void runOperatorControls() {
		// Drive Train
		if (oi.getDriveShiftLowButton()) {
			driveTrain.ravenTank.shiftToLowGear();
		}
		
		if (oi.getDriveShiftHighButton()) {
			driveTrain.ravenTank.shiftToHighGear();
		}
		
	    if (driveTrain.ravenTank.userControlOfCutPower) {
	      if (oi.getDriveCutPowerMode()) {
	        driveTrain.ravenTank.setCutPower(true);
	      }
	      else {
	        driveTrain.ravenTank.setCutPower(false);
	      }		
	    }
	    
	    if (oi.getOperatorIsAiming()) {
	    	flashlightLighting.turnOn();
	    }
	    else {
	    	flashlightLighting.turnOff();
	    }
		
		// Fuel Intake
		oi.fuelIntakeCollectButton.whileHeld(new FuelIntakeCollect(fuelIntake));
		oi.fuelIntakeReverseButton.whileHeld(new FuelIntakeReverse(fuelIntake));
		
		// Fuel Pump/Indexer
		oi.fuelPumpPumpStaggeredButton.whileHeld(new FuelPumpPumpStaggered(fuelPump));
		oi.fuelPumpPumpStaggeredButton.whileHeld(new FuelIndexerFeed(fuelIndexer));
		
		oi.fuelPumpReverseButton.whileHeld(new FuelPumpReverse(fuelPump));
		
		// Shooter
		// oi.shooterRevButton.whileHeld(new FuelShooterRev(fuelShooter));
		
		oi.shooterShootButton.whileHeld(new FuelShooterShoot(fuelShooter, fuelIndexer, fuelPump));
		
		// Climber
		oi.climberClimbButton.whileHeld(new ClimberClimb(climber));
		
		// Gear Intake
		oi.gearIntakeExtendButton.whileHeld(new GearIntakeExtend(gearIntake));
		// Any time the gear intake is extended, make sure the carriage is retracted.
		oi.gearIntakeExtendButton.whenPressed(new GearCarriageRetract(gearCarriage, carriageStalledLighting, carriageExtendedLighting));
		
		// Gear Carriage
		oi.gearCarriageExtendButton.whenPressed(new GearCarriageExtend(gearCarriage, driveTrain, carriageStalledLighting, carriageExtendedLighting));
		oi.gearCarriageRetractButton.whenPressed(new GearCarriageRetract(gearCarriage, carriageStalledLighting, carriageExtendedLighting));;
		
		
	}
	
	public void testPeriodic() {
		LiveWindow.run();
	}
}