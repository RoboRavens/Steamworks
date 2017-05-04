package org.usfirst.frc.team1188.ravenhardware;

import org.usfirst.frc.team1188.robot.*;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class RavenTank {
	Robot robot;

    public RavenEncoder leftEncoder;
    public RavenEncoder rightEncoder;
    
    // Gyro orientationGyro;
    Timer gyroCooldownTimer;
    
    AHRS orientationGyro = new AHRS(SPI.Port.kMXP);
    
    AHRS.BoardYawAxis boardYawAxis;
    double lastAccelerationX;
    double lastAccelerationY;
    double highestJerkX;
    double highestJerkY;
    
    protected int driveMode;
	protected int gyroMode;
	protected boolean cutPower;
	protected double gyroTargetHeading;
	// protected double orientation = 0;
	protected double slewRate = Calibrations.slewRate;
	
	// Joystick calibrationStick = new Joystick(RobotMap.calibrationJoystick);
	
	protected double netInchesTraveled = 0;
	protected double targetNetInchesTraveled = 0;
	protected boolean automatedDrivingEnabled = false;
	protected int automatedDrivingDirection = Calibrations.drivingForward;
	protected double automatedDrivingSpeed = 0;
	
	protected double leftEncoderReferencePoint = 0;
	protected double rightEncoderReferencePoint = 0;
	
	protected boolean hasHitObstacle = false;
	protected boolean drivingThroughObstacle = false;
	protected boolean turning = false;
	protected boolean waiting = false;
	
	public boolean userControlOfCutPower = true;

	RavenTalon driveLeft1 = new RavenTalon(RobotMap.leftDriveChannel1, slewRate);
	RavenTalon driveRight1 = new RavenTalon(RobotMap.rightDriveChannel1, slewRate);
	
	protected Solenoid shiftToLowGearSolenoid;
	protected Solenoid shiftToHighGearSolenoid;
	
	protected boolean isInHighGear = Calibrations.DriveTrainStartingIsInHighGear;
	
	Lighting shiftedToLowGearLighting;
	
	public RavenTank(Robot robot) {
		initializeRavenTank(robot);
	}
	
	public RavenTank(Robot robot, Solenoid lowGearSolenoid, Solenoid highGearSolenoid, Lighting shiftedToLowGearLighting) {
		this.shiftToLowGearSolenoid = lowGearSolenoid;
		this.shiftToHighGearSolenoid = highGearSolenoid;
		this.shiftedToLowGearLighting = shiftedToLowGearLighting;
		
		initializeRavenTank(robot);
	}

	private void initializeRavenTank(Robot robot) {
		this.robot = robot;
		
		slewRate = Calibrations.slewRate;
		
		Encoder leftWpiEncoder = new Encoder(RobotMap.leftDriveEncoder1, RobotMap.leftDriveEncoder2);
		Encoder rightWpiEncoder = new Encoder(RobotMap.rightDriveEncoder1, RobotMap.rightDriveEncoder2);
		
    
		leftEncoder = new RavenEncoder(leftWpiEncoder, Calibrations.leftEncoderCyclesPerRevolution, Calibrations.driveWheelDiameterInches);
		rightEncoder = new RavenEncoder(rightWpiEncoder, Calibrations.rightEncoderCyclesPerRevolution, Calibrations.driveWheelDiameterInches);
		
		// rightEncoder.setInverted(true);
    
		// orientationGyro = new AnalogGyro(1);
		
		gyroCooldownTimer = new Timer();		
		
		setDriveMode(Calibrations.defaultDriveMode);
		setCutPower(false);
				
		setGyroMode(Calibrations.defaultGyroMode);
		gyroTargetHeading = setGyroTargetHeadingToCurrentHeading();
	}
	
	public void setDriveMode(int driveMode) {
    	this.driveMode = driveMode;
    }
	
	public void setCutPower(boolean cutPower) {
    	this.cutPower = cutPower;
    }
	
	public void setGyroMode(int gyroMode) {
    	this.gyroMode = gyroMode;
    }
	
	public double deadband(double input) {
		double output = input;
		
		if (Math.abs(output) < Calibrations.deadbandMagnitude) {
			output = 0;
		}
		
		return output;
	}
	
	public void setSlewRate(double slewRate) {
		slewRate = Calibrations.slewRate;
		// TODO: Implement setSlewRate
		// driveRightSide.setSlewRate(slewRate);
		// driveLeftSide.setSlewRate(slewRate);
	}
	
    public void drive(double left, double rightY, double rightX) {
    	left = deadband(left);
    	rightY = deadband(rightY);
    	rightX = deadband(rightX);

//		System.out.println("Navx: " + orientationGyro.getAngle());
		
    	
    	// this.setSlewRate(Math.abs(this.calibrationStick.getZ() * 2));
    	
    	switch (driveMode) {
    		case Calibrations.bulldozerTank:
    			bulldozerTank(left, rightY);
    			break; 
    		case Calibrations.fpsTank:
    			fpsTank(left, rightX);
    			break;
    	}
    }
	
	public void bulldozerTank(double left, double right) {
		// Invert the left side.
    	right *= -1;
    	if (cutPower){
    		left *= Calibrations.cutPowerModeMovementRatio;
    		right *= Calibrations.cutPowerModeTurnRatio;
    	}
    	

		this.driveLeftSide(left);
    	this.driveRightSide(right);
    }
    
    public void fpsTank(double translation, double turn) {
    	// System.out.println("Gyro: " + orientationGyro.getAngle() + " Lencoder: " + this.leftEncoder.getNetInchesTraveled() + " Rencoder: " + this.rightEncoder.getNetInchesTraveled());
		
		// System.out.println("LINV: " + this.leftEncoder.getNetInchesTraveled() + " RINV: " + this.rightEncoder.getNetInchesTraveled());
		
    	
    	if (cutPower){
    		translation *= Calibrations.cutPowerModeMovementRatio;
    		turn *= Calibrations.cutPowerModeTurnRatio;
    	}
    	
		// Apply a small reduction to turning magnitude based on the magnitude of translation.
		turn = getScaledTurnFromTranslation(translation, turn);
		
        double gyroAdjust = getTurnableGyroAdjustment(turn); 
    	
    	// System.out.println("Gyro adjust: " + gyroAdjust + " gyro: " + this.orientationGyro.getAngle());
      
		double leftFinal = (translation - turn) * -1 - gyroAdjust;
		double rightFinal = (translation + turn) - gyroAdjust;
      
		this.driveLeftSide(leftFinal);
    	this.driveRightSide(rightFinal);
    	
    	if (detectCollisions() == true) {
    		shiftToLowGear();
    		shiftedToLowGearLighting.turnOnForSeconds(3);
    	}
    }
    
    public boolean detectCollisions() {
    	shiftedToLowGearLighting.maintainSecondsState();
    	boolean collisionDetected = false;
    	
    	double currentAccelerationX = orientationGyro.getWorldLinearAccelX();
    	double currentAccelerationY = orientationGyro.getWorldLinearAccelY();
    	
    	double currentJerkX = currentAccelerationX - lastAccelerationX;
    	double currentJerkY = currentAccelerationY - lastAccelerationY;
    	
    	lastAccelerationX = currentAccelerationX;
    	lastAccelerationY = currentAccelerationY;
    	
    	if (currentJerkX > highestJerkX) {
    		highestJerkX = currentJerkX;
    	}
    	
    	if (currentJerkY > highestJerkY) {
    		highestJerkY = currentJerkY;
    	}
    	
    	if (Math.abs(currentJerkX) > Calibrations.DriveTrainCollisionJerkThreshold) {
    		collisionDetected = true;
    	}
    	
    	if (Math.abs(currentJerkY) > Calibrations.DriveTrainCollisionJerkThreshold) {
    		collisionDetected = true;
    	}
    	
    	return collisionDetected;
    }
    
    public void outputJerk() {
    	double currentAccelerationX = orientationGyro.getWorldLinearAccelX();
    	double currentAccelerationY = orientationGyro.getWorldLinearAccelY();
    	
    	double currentJerkX = currentAccelerationX - lastAccelerationX;
    	double currentJerkY = currentAccelerationY - lastAccelerationY;
    	
    	System.out.println("X Jerk: " + currentJerkX + " Y Jerk: " + currentJerkY);
    }
    
    public void outputHighestJerk() {
    	System.out.println("Highest X Jerk: " + highestJerkX + " Highest Y Jerk: " + highestJerkY);
    }
    
    public void driveLeftSide(double magnitude) {
		// System.out.println("Driving left side. Magnitude: " + magnitude);
    	driveLeft1.set(magnitude);
		//driveLeft2.set(magnitude);
		// driveLeft3.set(magnitude);
    }
    
    public void driveRightSide(double magnitude) {
    	driveRight1.set(magnitude);
		//driveRight2.set(magnitude);
		// driveRight3.set(magnitude);
    }
    
    public void alignToMiddleLift() {
    	setGyroTargetHeading(Calibrations.gyroMiddleLiftHeading);
    }
    
    public void alignToLeftLift() {
    	setGyroTargetHeading(Calibrations.gyroLeftLiftHeading);
    }

	public void alignToRightLift() {
		setGyroTargetHeading(Calibrations.gyroRightLiftHeading);
	}
    
    
    public void shiftToLowGear() {
    	
    	
    	this.isInHighGear = false;
    	shiftToLowGearSolenoid.set(true);
    	shiftToHighGearSolenoid.set(false);
    	
    
    }
    
    public void shiftToHighGear() {
    	
    	
    	this.isInHighGear = true;
    	shiftToHighGearSolenoid.set(true);
    	shiftToLowGearSolenoid.set(false);
    	
    }
	
	public double getScaledTurnFromTranslation(double translation, double turn) {
		double turnScaleReduction = Calibrations.translationMaxTurnScaling * Math.abs(translation);
		double turnCoefficient = 1 - turnScaleReduction;
		double netTurn = turn * turnCoefficient;
		
		return netTurn;
	}
    
    public void driveOutput() {
    	
    }
    
    /*
	public int getRightDriveEncoder(){
		System.out.println(rightEncoder.getNetInchesTraveled());
		return rightEncoder.getCycles();
	}
	
	public int getLeftDriveEncoder(){
		System.out.println(leftEncoder.getNetInchesTraveled());
		return leftEncoder.getCycles();
	}    
*/
    public double getDriveGyro() {
    	//System.out.println("Gyro angle: " + Math.round(orientationGyro.getAngle()) + " Gyro mode: " + gyroMode);
    	return orientationGyro.getAngle();
    }
    
    /*
    public void resetDriveGyro() {
    	orientationGyro.reset();
    }
    */
    
    public double setGyroTargetHeadingToCurrentHeading(){    	
    	this.gyroTargetHeading = getCurrentHeading();
    	
    	return gyroTargetHeading;
    }
    
    public double setGyroTargetHeading(double angle){
    	gyroTargetHeading = angle;
    	return gyroTargetHeading;
    }

    private boolean adjustGyroDueToTimer() {
    	double time = this.gyroCooldownTimer.get();
    	
    	boolean adjust = false;
    	
    	if (time > 0 && time < Calibrations.gyroCooldownTimerTime) {
    		adjust = true;
    	}
    	else if (time > Calibrations.gyroCooldownTimerTime) {
    		gyroCooldownTimer.stop();
    	}
    	
    	return adjust;
    }

    public double getTurnableGyroAdjustment(double turn){
    	// If the gyro is in disabled mode, just return immediately.
    	if (gyroMode == Calibrations.gyroDisabled) {
    		return 0;
    	} 
    	
    	if (Math.abs(turn) > 0 || this.adjustGyroDueToTimer()) {
        	this.setGyroTargetHeadingToCurrentHeading();
        	
        	if (Math.abs(turn) > 0) {
	        	this.gyroCooldownTimer.reset();
	        	this.gyroCooldownTimer.start();
        	}
        }
    	
    	return getStaticGyroAdjustment();
    	// return 0;
    }
    
    
    public double getStaticGyroAdjustment() {
    	// If the gyro is in disabled mode, just return immediately.
    	if (gyroMode == Calibrations.gyroDisabled) {
    		return 0;
    	}
    	
    	double heading = getCurrentHeading();
    	
    	// System.out.print("GTH: " + gyroTargetHeading);
    	
    	
    	// Mod to eliminate extra rotations.
    	double gyroAdjust = (heading - gyroTargetHeading) % 360;
    	
    	
    	if (gyroAdjust < 0) {
    		gyroAdjust = 360 + gyroAdjust;
    	}
    	
    	// System.out.print(" gyro adjust1: " + gyroAdjust);
    	
    	// This snippet ensures that the robot will spin in the fastest direction to zero
    	// if it ends up more than 180 degrees off of intention.
    	if (gyroAdjust > 180){
    		gyroAdjust = gyroAdjust - 360;
    	}
    	
    	if (gyroAdjust > 180 || gyroAdjust < -180){
    		gyroAdjust *= -1;
    	}
    	
    	// System.out.println("GYRO ADJUST POST_adj: " + gyroAdjust);
    	
    	// Mod again in case the directional snippet was applied.
    	gyroAdjust = Math.round(gyroAdjust) % 360;
    	
    	gyroAdjust *= Calibrations.gyroAdjustmentScaleFactor;
    	
        // System.out.println("Gyro adjust: " + gyroAdjust + " gyro: " + this.orientationGyro.getAngle() +  "Zero" + gyroZero);
    	
    	//System.out.println("Gyro adjust: " + gyroAdjust + " heading: " + getCurrentHeading());
    	//System.out.println("-1 mod 360: " + (-1 % 360));
        return gyroAdjust;
    }
    
    
    /*
    public double getStaticGyroAdjustment() {
    	// If the gyro is in disabled mode, just return immediately.
    	if (gyroMode == Calibrations.gyroDisabled) {
    		return 0;
    	}
    	    	
    	// Mod to eliminate extra rotations.
    	double gyroAdjust = (Math.round(orientationGyro.getAngle()) - gyroTargetHeading) % 360;
    	
    	// This snippet ensures that the robot will spin in the fastest direction to zero
    	// if it ends up more than 180 degrees off of intention.
    	if (gyroAdjust < -180){
    		gyroAdjust = gyroAdjust - 360;
    	}
    	if (gyroAdjust > 180 || gyroAdjust < -180){
    		gyroAdjust *= -1;
    	}
    	
    	// Mod again in case the directional snippet was applied.
    	gyroAdjust = Math.round(gyroAdjust) % 360;
    	
    	gyroAdjust *= Calibrations.gyroAdjustmentScaleFactor;
    	
        // System.out.println("Gyro adjust: " + gyroAdjust + " gyro: " + this.orientationGyro.getAngle() +  "Zero" + gyroZero);
    	
        return gyroAdjust;
    }
    */
    
    public void stopAndWait() {
    	enableAutomatedDriving(0);
    }
    /*
    public void driveForwardInches(double inches, int direction, double speed) {
    	leftEncoderReferencePoint = this.leftEncoder.getNetInchesTraveled();
    	rightEncoderReferencePoint = this.rightEncoder.getNetInchesTraveled();
      
    	this.targetNetInchesTraveled = inches;
    	enableAutomatedDriving(direction, speed);
    }*/
    /*
    public void driveUntilOverObstacle(int direction, double speed) {
    	drivingThroughObstacle = true;
    	enableAutomatedDriving(direction, speed);
    }
    */
    
    public void turnRelativeDegrees(double degrees) {
    	this.setGyroTargetHeading(this.gyroTargetHeading + degrees);
    }
    
    public void enableAutomatedDriving(int direction, double speed) {
    	automatedDrivingDirection = direction;
    	enableAutomatedDriving(speed);
    }
    
    public void enableAutomatedDriving(double speed) {
    	automatedDrivingEnabled = true;
    	automatedDrivingSpeed = speed;
    }
    
    public void overrideAutomatedDriving() {
    	// Just disable all the automated driving variables, and
    	// the normal drive function will immediately resume.
    	automatedDrivingEnabled = false;
    	drivingThroughObstacle = false;
    	hasHitObstacle = false;
    	turning = false;
    	waiting = false;
    }
    
    public void stop() {
    	this.fpsTank(0, 0);
    }
    
    public boolean automatedActionHasCompleted() {
    	// Just return the opposite of automatedDrivingEnabled.
    	return automatedDrivingEnabled == false;
    }
    
    /*
    public void maintainState() {
		// System.out.println("Gyro: " + orientationGyro.getAngle() + " Lencoder: " + this.leftEncoder.getNetInchesTraveled() + " Rencoder: " + this.rightEncoder.getNetInchesTraveled());
		
    	// Maintain state only does things while automated driving is enabled.
    	if (automatedDrivingEnabled == false) {
    		return;
    	}
    	
    	// if (drivingThroughObstacle) {
    	// 	maintainStateDrivingThroughObstacle();
    	//	return;
    	// }
    	
    	
    	if (turning) {
    		maintainStateTurning();
    		return;
    	}
    	
    	if (waiting) {
    		maintainStateWaiting();
    		return;
    	}
    	
    	maintainStateDrivingStraight();
    }
    */
    
    public void maintainStateWaiting() {
    	this.stop();
    }
    
    public void wake() {
    	this.waiting = false;
    	this.automatedDrivingEnabled = false;
    }
    
    public void maintainStateTurning() {
    	if (Math.abs(gyroTargetHeading - getCurrentHeading()) < 3) {
    		automatedDrivingEnabled = false;
    		turning = false;
    	}
    	
    }
    
    public double getCurrentHeading() {
    	double heading = orientationGyro.getAngle();

    	heading = heading % 360;
    	
    	if (heading < 0) {
    		heading += 360;
    	}
    	
    	return heading;
    }
    
    /*
    public double getGyroAngle() {    
    	return orientationGyro.getAngle();    
    }
    */
    
    public double getNetInchesTraveled() {
    	double leftInches = this.leftEncoder.getNetInchesTraveled();
    	double rightInches = this.rightEncoder.getNetInchesTraveled();
    	
    	double netInchesTraveled = (leftInches + rightInches) / 2;
    	
    	
    	netInchesTraveled = rightInches;
    	
    	return netInchesTraveled;
    }
    
    /*
    public void maintainStateDrivingStraight() {
    	//this.maintainEncoders();
    	
    	// Check if we've made it to the destination.
    	if (netInchesTraveled <= targetNetInchesTraveled) {
    		automatedDrivingEnabled = false;
    		return;
    	}
    	
    	// Automated driving: confirm direction, and set power based on speed.
    	// But, if within deceleration zone, start decelerating using "poor man's PID".
    	double powerCoefficient = getPowerCoefficient();
    	
    	double power = this.automatedDrivingSpeed * powerCoefficient;
    	
    	power *= this.automatedDrivingDirection;

    	this.fpsTank(power, 0);
    }
    */
    
    
    public void maintainEncoders() {
    	double leftInches = this.leftEncoder.getNetInchesTraveled() - this.leftEncoderReferencePoint;
    	double rightInches = this.rightEncoder.getNetInchesTraveled() - this.rightEncoderReferencePoint;
    	
      // Take the mean of the left and rich inches. Turning "shouldn't" make a difference.
    	this.netInchesTraveled = (leftInches + rightInches) / 2;
		
		System.out.println("ME NIT: " + this.netInchesTraveled);
    }

	public void resetOrientationGyro() {
		orientationGyro.reset();
		
	}
    
    /*
    public double getPowerCoefficient() {
    	double decelerationRangeInches = Calibrations.decelerationInchesPerMotorOutputMagnitude * this.automatedDrivingSpeed;
    	
    	double inchesToGo = targetNetInchesTraveled - netInchesTraveled;
    	
    	// Any power cuts will be applied through this coefficient. 1 means no cuts.
    	double powerCoefficient = 1;
    	
    	// The power coefficient will be what percent of the deceleration range has been
    	// is yet to be traversed, but never higher than one.
    	powerCoefficient = Math.min(1, inchesToGo / decelerationRangeInches);
    	
    	return powerCoefficient;
    }
    */
    
    /*
    public void maintainStateDrivingThroughObstacle() {
    	double power = automatedDrivingSpeed;
    	
    	// Check if we've made hit the obstacle and are now on carpet.
    	if (hasHitObstacle && robotIsOnCarpet()) {
    		// We're through; kill automated mode.
    		power = 0;
    		automatedDrivingEnabled = false;
    		hasHitObstacle = false;
    		drivingThroughObstacle = false;
    	}
    	
    	power *= automatedDrivingDirection;
    	
    	fpsTank(power, 0);
    }
    */
    
}