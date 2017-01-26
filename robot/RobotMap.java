package org.usfirst.frc.team1188.robot;

public class RobotMap {
	// Drive motors
	public static final int leftDriveArray = 0;
	public static final int rightDriveArray = 1;
	
	// Drive encoders
	public static final int leftDriveEncoder1 = 0;
	public static final int leftDriveEncoder2 = 1;
	public static final int rightDriveEncoder1 = 2;
	public static final int rightDriveEncoder2 = 3;
	
	// CAN Talons
	// Intake, agitator, indexer, shooter, climber
	public static final int intakeMotor1 = 0;
	public static final int intakeMotor2 = 1;
	public static final int agitatorMotor = 2;
	public static final int shooterMotor1 = 3;
	public static final int shooterMotor2 = 4;
	public static final int climberMotor1 = 5;
	public static final int climberMotor2 = 6;
	
	
	// Pneumatic solenoids
	public static final int shiftToHighSpeedSolenoid = 0;
	public static final int shiftToLowSpeedSolenoid = 1;
	public static final int extendGearIntakeSolenoid = 2;
	public static final int retractGearIntakeSolenoid = 3;
	public static final int extendGearCarriageSolenoid = 4;
	public static final int retractGearCarriageSolenoid = 5;
}
