package org.usfirst.frc.team1188.robot;

public final class Calibrations {
	// Drive calibration
	public static final double slewRate = 1; // Previously: .35.
	public static final double cutPowerModeMovementRatio = .3;
	public static final double cutPowerModeTurnRatio = .5;
	public static final double gyroAdjustmentScaleFactor = .03;
	public static final double gyroCooldownTimerTime = .5;
	public static final double translationMaxTurnScaling = .5;
	public static final double gyroAutoTurnAcceptableErrorDegrees = 3;
	public static final boolean DriveTrainStartingIsInHighGear = false;
	
	// Drive collision
	public static final double DriveTrainCollisionJerkThreshold = 4;
	
	// Drive and gyro modes
	public static final int bulldozerTank = 0;
	public static final int fpsTank = 1;
	
	public static final int gyroDisabled = 1;
	public static final int gyroEnabled = 0;
	
	// Any turn taking too long to complete (e.g. wheel scrub has halted the turn) will abandon after this number of seconds.
	public static final double DriveTrainTurnRelativeDegreesSafetyTimerSeconds = 1;
	
	// Deadband
	public static final double deadbandMagnitude = .1;
	
	// Default drive and gyro modes
	public static final int defaultDriveMode = Calibrations.fpsTank;
	public static final int defaultGyroMode = Calibrations.gyroEnabled;
	
	// Drive encoders
	// The *3 is for low gear. In high gear, it would just be 4096. Run all autonomous modes in low gear.
	public static final int encoderCUI103CyclesPerRevolution = 4096 * 3;
	// public static final int encoderE4TCyclesPerRevolution = 360;
	// public static final int encoderE4PCyclesPerRevolution = 250;
	public static final double driveWheelDiameterInches = 4;
	public static final double driveWheelCircumferenceInches = Calibrations.driveWheelDiameterInches * Math.PI;
	//public static final double driveEncoderE4TCyclesPerInch = (double) Calibrations.encoderE4TCyclesPerRevolution / Calibrations.driveWheelCircumferenceInches;
	//public static final double driveEncoderE4PCyclesPerInch = (double) Calibrations.encoderE4PCyclesPerRevolution / Calibrations.driveWheelCircumferenceInches;
	
	// We're using CUI 103 encoders on both sides of the drivetrain.
	public static final int leftEncoderCyclesPerRevolution = Calibrations.encoderCUI103CyclesPerRevolution;
	public static final int rightEncoderCyclesPerRevolution = Calibrations.encoderCUI103CyclesPerRevolution;
	
	// Direction magic numbers
	public static final int drivingForward = -1;
	public static final int drivingBackward = 1;
	
	
	// Until we have more genuine motion profiling capabilities, we'll use this.
	// This is "feet necessary to decelerate per 1 magnitude of motor output."
	// Problems with this include that motor output is acceleration, but necessary
	// deceleration distance is correlated with distance.
	// public static final double decelerationInchesPerMotorOutputMagnitude = 18;
	
	public static final double FuelIntakePowerMagnitude = 1;
	public static final double ClimberClimbPowerMagnitude = 1;
	public static final double FuelPumpPowerMagnitude = .8;
	
	public static final double FuelIndexerPowerMagnitude = 1;
	public static final double GearCarriagePowerMagnitude = 1;
	public static final double GearCarriageRetractionPowerMagnitude = .5;
	
	// Fuel shooter
	public static final double ShooterRevPowerMagnitude = .83;
	public static final double ShooterEncoderTargetSpeed = 32000;
	public static final double ShooterMaintainSpeedPower = .7;
	public static final double ShooterOneHundredthPowerRpmRage = 400;
	public static final double ShooterMaximumShootRpm = 34000;
	public static final double ShooterMinimumShootRpm = 30500;
	public static final double ShooterTargetPowerMagnitudeOffset = -.025;
	public static final double ShooterRestorationPowerMagnitude = .85;
	
	// Fuel pump
	public static final double fuelPumpMinimumForwardPumpSeconds = .5;
	public static final double fuelPumpMinimumBackwardPumpSeconds = .3;
	public static final int fuelPumpStallRpm = 50;
	
	// Gear carriage
	public static final double GearCarriageStallTimerMinimumDurationForStall = .2;
	public static final double GearCarriageStallCurrentThresholdAmps = 45;
	public static final double GearCarriageStallTimerSeconds = .75;
	
    // Camera
	public static final int cameraQuality = 50;
	
	// Lighting
	public static double lightingFlashTotalDurationMs = 1000;
	public static double lightingFlashes = 10;
	
	// Autonomous Modes
	public static final String AutonomousGearToMiddleLift = "GEARMID";
	public static final String AutonomousGearToLeftLift = "GEARLEFT";
	public static final String AutonomousGearToRightLift = "GEARRIGHT";
	public static final String AutonomousShootHighGoal = "SHOOT";
	public static final String AutonomousCrossBaseLine = "DRIVE";
	public static final String AutonomousRankingPoint = "RP";
	public static final String AutonomousGeartoMiddleLiftScoreHigh = "MIDSHOOT";
	
	// Autonomous mode - place gear on middle lift
	public static final double AutonomousPlaceGearOnMiddleLiftDriveForwardInches = 91;
	public static final double AutonomousPlaceGearOnMiddleLiftDriveForwardPowerMagnitude = .4;
	public static final double AutonomousPlaceGearOnMiddleLiftDriveBackwardInches = 30;
	public static final double AutonomousPlaceGearOnMiddleLiftDriveBackwardPowerMagnitude = .25;
	public static final double GearCarriageAutoPlaceReorientationBaseDegrees = 5;
	
	// Autonomous mode - cross base line
	public static final double AutonomousCrossBaseLineDriveForwardInches = 91;
	public static final double AutonomousCrossBaselineDriveForwardPowerMagnitude = .7;
  
	// Autonomous mode - shoot high goals
	public static final double AutonomousShootHighGoalShootingSeconds = 10;
	public static final double AutonomousShootHighGoalRedAllianceDegreesToTurn = 20;
	
	// Autonomous mode - drop hopper and shoot high goals
	public static final double AutonomousDropHopperDriveFromWallInches = 77;
	public static final double AutonomousDropHopperDriveFromWallSpeed = 1;
	public static final double AutonomousDropHopperTurnToHopperDegreesBlueAlliance = -90;
	public static final double AutonomousDropHopperTurnToHopperDegreesRedAlliance = 90;
	public static final double AutonomousDropHopperDriveToHopperInches = 20;
	public static final double AutonomousDropHopperAimAtGoalDegreesBlueAlliance = 15;
	public static final double AutonomousDropHopperAimAtGoalDegreesRedAlliance = -15;
	public static final double AutonomousDropHopperShootSeconds = 13;

}
