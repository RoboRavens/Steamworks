package org.usfirst.frc.team1188.robot;

public final class Calibrations {
	// Drive calibration
	public static double slewRate = .35;
	public static double cutPowerModeMovementRatio = .3;
	public static double cutPowerModeTurnRatio = .5;
	public static double gyroAdjustmentScaleFactor = .03;
	public static double gyroCooldownTimerTime = .5;
	public static double translationMaxTurnScaling = .5;
	
	// Drive and gyro modes
	public static final int bulldozerTank = 0;
	public static final int fpsTank = 1;
	
	public static final int gyroDisabled = 1;
	public static final int gyroEnabled = 0;
	
	// Deadband
	public static final double deadbandMagnitude = .1;
	
	// Default drive and gyro modes
	public static final int defaultDriveMode = Calibrations.fpsTank;
	public static final int defaultGyroMode = Calibrations.gyroEnabled;
	
	// Drive encoders (INCORRECT, NEEDS TO BE DETERMINED)
	public static final int encoderCUI103CyclesPerRevolution = 4096;
	public static final int encoderE4TCyclesPerRevolution = 360;
	public static final int encoderE4PCyclesPerRevolution = 250;
	public static final double driveWheelDiameterInches = 4;
	public static final double driveWheelCircumferenceInches = Calibrations.driveWheelDiameterInches * Math.PI;
	//public static final double driveEncoderE4TCyclesPerInch = (double) Calibrations.encoderE4TCyclesPerRevolution / Calibrations.driveWheelCircumferenceInches;
	//public static final double driveEncoderE4PCyclesPerInch = (double) Calibrations.encoderE4PCyclesPerRevolution / Calibrations.driveWheelCircumferenceInches;
	
	public static final int leftEncoderCyclesPerRevolution = Calibrations.encoderCUI103CyclesPerRevolution;
	public static final int rightEncoderCyclesPerRevolution = Calibrations.encoderCUI103CyclesPerRevolution;
	
	// Direction magic numbers
	public static final int drivingForward = -1;
	public static final int drivingBackward = 1;
	
	
	// Until we have more genuine motion profiling capabilities, we'll use this.
	// This is "feet necessary to decelerate per 1 magnitude of motor output."
	// Problems with this include that motor output is acceleration, but necessary
	// deceleration distance is correlated with distance.
	public static final double decelerationInchesPerMotorOutputMagnitude = 18;
	
	public static final double FuelIntakePowerMagnitude = 1;
	public static final double ClimberClimbPowerMagnitude = 1;
	public static final double FuelPumpPowerMagnitude = 1;
	
	public static final double FuelIndexerPowerMagnitude = 1;
	public static final double GearCarriagePowerMagnitude = 1;
	public static final double ShooterRevPowerMagnitude = 1;
	
	// Fuel pump
	public static double fuelPumpMinimumForwardPumpSeconds = .5;
	public static double fuelPumpMinimumBackwardPumpSeconds = .3;
	public static int fuelPumpStallRpm = 30;
	
	// Autonomous Modes
	public static final String AutonomousGearToMiddleLift = "GEARMID";
	public static final String AutonomousGearToLeftLift = "GEARLEFT";
	public static final String AutonomousGearToRightLift = "GEARRIGHT";
	public static final String AutonomousShootHighGoal = "SHOOT";
	public static final String AutonomousCrossBaseLine = "DRIVE";
	
	// Autonomous mode - place gear on middle lift
	public static final double AutonomousPlaceGearOnMiddleLiftDriveForwardInches = 90;
	public static final double AutonomousPlaceGearOnMiddleLiftDriveForwardPowerMagnitude = .25;
	public static final double AutonomousPlaceGearOnMiddleLiftDriveBackwardInches = 20;
	public static final double AutonomousPlaceGearOnMiddleLiftDriveBackwardPowerMagnitude = .5;
	
	// Autonomous mode - cross base line
	public static final double AutonomousCrossBaseLineDriveForwardInches = 120;
	public static final double AutonomousCrossBaselineDriveForwardPowerMagnitude = .5;
}
