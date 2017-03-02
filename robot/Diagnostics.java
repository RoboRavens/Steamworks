package org.usfirst.frc.team1188.robot;

public class Diagnostics {
	Robot robot;
	
	public Diagnostics(Robot robot) {
		this.robot = robot;
	}
	
	public void outputAutonomousDiagnostics() {
		//System.out.print("LEncoder: " + round(robot.driveTrain.ravenTank.leftEncoder.getNetInchesTraveled(), 2) + " REncoder: " + round(robot.driveTrain.ravenTank.rightEncoder.getNetInchesTraveled(), 2));
		System.out.print("DT total IT: " + Math.round(robot.driveTrain.ravenTank.getNetInchesTraveled()));
	}
	
	public void outputTeleopDiagnostics() {
		// System.out.print("DT total IT: " + Math.round(robot.driveTrain.ravenTank.getNetInchesTraveled()));
		// System.out.println("Navx.getAngle: " + navX.getAngle());
		System.out.println("REncoder: " + round(robot.driveTrain.ravenTank.rightEncoder.getNetInchesTraveled(), 2) + " LEncoder: " + round(robot.driveTrain.ravenTank.leftEncoder.getNetInchesTraveled(), 2));
		
		// System.out.println("Navx get angle: " + driveTrain.ravenTank.getGyroAngle());
		
		//System.out.println("Extension limit: " + gearCarriage.getIsAtExensionLimit() + " retraction limit: " + gearCarriage.getIsAtRetractionLimit());
		// System.out.println("RPM: " + robot.fuelShooter.shooterMotorLead.getEncVelocity());
		// fuelPump.getRpm();
		// System.out.println(Calibrations.DriveTrainCollisionJerkThreshold);
		
		// robot.driveTrain.ravenTank.outputJerk();
		// robot.driveTrain.ravenTank.outputHighestJerk();
	}
	
	public double round(double value, int digits) {
		double tempValue = value;
		
		for (int i = 0; i < digits; i++) {
			tempValue *= 10;
		}
		
		tempValue = Math.round(tempValue);
		
		for (int i = 0; i < digits; i++) {
			tempValue /= 10;
		}
		
		return tempValue;
	}
	
	
}
