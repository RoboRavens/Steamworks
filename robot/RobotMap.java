package org.usfirst.frc.team1188.robot;

public class RobotMap {
	// Drive motors
	public static int leftDriveChannel1 = 0;
//	public static int leftDriveChannel2 = 1;
	public static int rightDriveChannel1 = 1;
	//public static int rightDriveChannel2 = 3;
	//public static int rightDriveChannel2 = 1;
	//public static int rightDriveChannel3 = 2;
	
	// Drive encoders
	public static final int leftDriveEncoder1 = 0;
	public static final int leftDriveEncoder2 = 1;
	public static final int rightDriveEncoder1 = 2;
	public static final int rightDriveEncoder2 = 3;
	
	/*
	public static final int fuelIntakeMotor1 = 1;
	public static final int fuelIntakeMotor2 = 2;
	*/
	
	// Talon SRX Id's 
	public static final int fuelIntakeMotor = 1;
	public static final int fuelPumpMotor = 2;
	public static final int fuelIndexerMotor = 3;
	public static final int fuelShooterMotorLead = 4;
	public static final int fuelShooterMotorFollower = 5;
	public static final int gearCarriageMotor = 6;
	public static final int climberMotor = 7;
	
	// Sensors
	public static final int gearCarriageExtensionLimit = 4;
	public static final int gearCarriageRetractionLimit = 5;
	
	// Camera
	public static final String cameraName = "cam0";
	
	// Relays
	public static final int intakeReadyLightRelay = 0;
	public static final int carriageStalledLightRelay = 1;
	public static final int carriageExtendedLightRelay = 2;
	public static final int underglowLightRelay = 3;
	public static final int flashlightRelay = 3;
}
