package org.usfirst.frc.team1188.robot;

public final class Calibrations {
	// Drive calibration
	public static double slewRate = .35;
	public static double cutPowerModeMovementRatio = .3;
	public static double cutPowerModeTurnRatio = .5;
	public static double gyroAdjustmentScaleFactor = .03;
	public static double gyroCooldownTimerTime = 1;
	public static double translationMaxTurnScaling = .5;
	
	// Drive and gyro modes
	public static final int bulldozerTank = 0;
	public static final int fpsTank = 1;
	
	public static final int gyroDisabled = 0;
	public static final int gyroEnabled = 1;
	
	// Deadband
	public static final double deadbandMagnitude = .1;
	
	// Default drive and gyro modes
	public static final int defaultDriveMode = Calibrations.fpsTank;
	public static final int defaultGyroMode = Calibrations.gyroEnabled;
	
	// Drive encoders (INCORRECT, NEEDS TO BE DETERMINED)
	public static final int encoderE4TCyclesPerRevolution = 360;
	public static final int encoderE4PCyclesPerRevolution = 250;
	public static final double driveWheelDiameterInches = 4;
	public static final double driveWheelCircumferenceInches = Calibrations.driveWheelDiameterInches * Math.PI;
	public static final double driveEncoderE4TCyclesPerInch = (double) Calibrations.encoderE4TCyclesPerRevolution / Calibrations.driveWheelCircumferenceInches;
	public static final double driveEncoderE4PCyclesPerInch = (double) Calibrations.encoderE4PCyclesPerRevolution / Calibrations.driveWheelCircumferenceInches;
	
	public static final int leftEncoderCyclesPerRevolution = Calibrations.encoderE4TCyclesPerRevolution;
	public static final int rightEncoderCyclesPerRevolution = Calibrations.encoderE4TCyclesPerRevolution;
	
	// Direction magic numbers
	public static final int drivingForward = -1;
	public static final int drivingBackward = 1;
	
	
	// Until we have more genuine motion profiling capabilities, we'll use this.
	// This is "feet necessary to decelerate per 1 magnitude of motor output."
	// Problems with this include that motor output is acceleration, but necessary
	// deceleration distance is correlated with distance.
	public static final double decelerationInchesPerMotorOutputMagnitude = 18;
}
