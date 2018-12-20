package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hardware {
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;

    public DcMotor elevatorLift;

    public DcMotor intakeSpinner;
    public DcMotor intakeArmLift;
    public DcMotor intakeArmExtender;

    public Servo teamMarkerPlacer;


    Telemetry telemetry;
    HardwareMap hwMap;

    ElapsedTime timer = new ElapsedTime();

    /*a motor must be within this many ticks of its
	target to be considered "on target"*/
    private static final int encoderSafeZone = 50;


    //init methods
    public void initRobot(HardwareMap spareMap, Telemetry tempTelemetry) {
        hwMap = spareMap;
        telemetry = tempTelemetry;
        initHardware();
    }

    public void initHardware() {
        //drive
        frontLeft = hwMap.dcMotor.get("front left wheel");
        frontRight = hwMap.dcMotor.get("front right wheel");
        backLeft = hwMap.dcMotor.get("back left wheel");
        backRight = hwMap.dcMotor.get("back right wheel");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //lift
        elevatorLift = hwMap.dcMotor.get("elevator lift motor");

        //intake
        intakeSpinner = hwMap.dcMotor.get("intake spinner motor");
        intakeArmLift = hwMap.dcMotor.get("intake arm lift");
        intakeArmExtender = hwMap.dcMotor.get("intake arm extender");

        //team marker
        teamMarkerPlacer = hwMap.servo.get("team marker placer");
    }

    //converts inches into motor ticks for Rev HD hex motors
    public int ticksToInch(int distance) {
        //ticks per inch = counts per rotation of Rev motors / circumference of wheels
        //ticks = 2240/(4*Math.PI);
        return distance * 178;
    }


//drive encoder methods
    /*public void driveForwardBackwardEncoder(double power, int distance)
    {
        //dist is the distance left to the goal encoder position
        int frontLDist, frontRDist, backLDist, backRDist;

        setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        setMotorEncoderForward(ticksToInch(distance) + frontLeft.getCurrentPosition());

        setPower(power);

        do{
            frontLDist=Math.abs(frontLeft.getTargetPosition()-frontLeft.getCurrentPosition());
            frontRDist=Math.abs(frontRight.getTargetPosition()-frontRight.getCurrentPosition());
            backLDist=Math.abs(backLeft.getTargetPosition()-backLeft.getCurrentPosition());
            backRDist=Math.abs(backRight.getTargetPosition()-backRight.getCurrentPosition());

            telemetry.addData("frontLeft distanceFrom: ",frontLDist);
            telemetry.addData("frontRight distanceFrom: ",frontRDist);
            telemetry.addData("backLeft distanceFrom: ",backLDist);
            telemetry.addData("backRight distanceFrom: ",backRDist);
            telemetry.update();
        }while(
                frontLDist>encoderSafeZone &&
                        frontRDist>encoderSafeZone &&
                        backLDist>encoderSafeZone &&
                        backRDist>encoderSafeZone
                );
        setToStill();
    }*/

    public void driveForwardEncoder(double power, int distance) {
        setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        int frontLDist, frontRDist, backLDist, backRDist;
        setMotorEncoderForward(ticksToInch(distance) + frontLeft.getCurrentPosition());

        setPower(power);
        do {
            frontLDist = Math.abs(frontLeft.getTargetPosition() - frontLeft.getCurrentPosition());
            frontRDist = Math.abs(frontRight.getTargetPosition() - frontRight.getCurrentPosition());
            backLDist = Math.abs(backLeft.getTargetPosition() - backLeft.getCurrentPosition());
            backRDist = Math.abs(backRight.getTargetPosition() - backRight.getCurrentPosition());

            telemetry.addData("frontLeft distanceFrom: ", frontLDist);
            telemetry.addData("frontRight distanceFrom: ", frontRDist);
            telemetry.addData("backLeft distanceFrom: ", backLDist);
            telemetry.addData("backRight distanceFrom: ", backRDist);
            telemetry.update();
        } while (
                frontLDist > encoderSafeZone &&
                        frontRDist > encoderSafeZone &&
                        backLDist > encoderSafeZone &&
                        backRDist > encoderSafeZone
                );
        setToStill();
    }

    public void driveLeftEncoder(double power, int distance) {
        setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        int frontLDist, frontRDist, backLDist, backRDist;
        setMotorEncoderLeft(ticksToInch(distance) + frontLeft.getCurrentPosition());

        setPower(power);
        do {
            frontLDist = Math.abs(frontLeft.getTargetPosition() - frontLeft.getCurrentPosition());
            frontRDist = Math.abs(frontRight.getTargetPosition() - frontRight.getCurrentPosition());
            backLDist = Math.abs(backLeft.getTargetPosition() - backLeft.getCurrentPosition());
            backRDist = Math.abs(backRight.getTargetPosition() - backRight.getCurrentPosition());

            telemetry.addData("frontLeft distanceFrom: ", frontLDist);
            telemetry.addData("frontRight distanceFrom: ", frontRDist);
            telemetry.addData("backLeft distanceFrom: ", backLDist);
            telemetry.addData("backRight distanceFrom: ", backRDist);
            telemetry.update();
        } while (
                frontLDist > encoderSafeZone &&
                        frontRDist > encoderSafeZone &&
                        backLDist > encoderSafeZone &&
                        backRDist > encoderSafeZone
                );
        setToStill();
    }

    public void driveRightEncoder(double power, int distance) {
        setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        int frontLDist, frontRDist, backLDist, backRDist;
        setMotorEncoderRight(ticksToInch(distance) + frontLeft.getCurrentPosition());
        setPower(power);
        do {
            frontLDist = Math.abs(frontLeft.getTargetPosition() - frontLeft.getCurrentPosition());
            frontRDist = Math.abs(frontRight.getTargetPosition() - frontRight.getCurrentPosition());
            backLDist = Math.abs(backLeft.getTargetPosition() - backLeft.getCurrentPosition());
            backRDist = Math.abs(backRight.getTargetPosition() - backRight.getCurrentPosition());

            telemetry.addData("frontLeft distanceFrom: ", frontLDist);
            telemetry.addData("frontRight distanceFrom: ", frontRDist);
            telemetry.addData("backLeft distanceFrom: ", backLDist);
            telemetry.addData("backRight distanceFrom: ", backRDist);
            telemetry.update();
        } while (
                frontLDist > encoderSafeZone &&
                        frontRDist > encoderSafeZone &&
                        backLDist > encoderSafeZone &&
                        backRDist > encoderSafeZone
                );
        setToStill();
    }

    public void turnClockwiseEncoder(double power, int distance) {
        setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        int frontLDist, frontRDist, backLDist, backRDist;
        setMotorEncoderClockwise(ticksToInch(distance) + frontLeft.getCurrentPosition());

        setPower(power);
        do {
            frontLDist = Math.abs(frontLeft.getTargetPosition() - frontLeft.getCurrentPosition());
            frontRDist = Math.abs(frontRight.getTargetPosition() - frontRight.getCurrentPosition());
            backLDist = Math.abs(backLeft.getTargetPosition() - backLeft.getCurrentPosition());
            backRDist = Math.abs(backRight.getTargetPosition() - backRight.getCurrentPosition());

            telemetry.addData("frontLeft distanceFrom: ", frontLDist);
            telemetry.addData("frontRight distanceFrom: ", frontRDist);
            telemetry.addData("backLeft distanceFrom: ", backLDist);
            telemetry.addData("backRight distanceFrom: ", backRDist);
            telemetry.update();
        } while (
                frontLDist > encoderSafeZone &&
                        frontRDist > encoderSafeZone &&
                        backLDist > encoderSafeZone &&
                        backRDist > encoderSafeZone
                );
        setToStill();
    }

    public void turnCounterwiseEncoder(double power, int distance) {
        setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        int frontLDist, frontRDist, backLDist, backRDist;
        setMotorEncoderCounterwise(ticksToInch(distance) + frontLeft.getCurrentPosition());

        setPower(power);
        do {
            frontLDist = Math.abs(frontLeft.getTargetPosition() - frontLeft.getCurrentPosition());
            frontRDist = Math.abs(frontRight.getTargetPosition() - frontRight.getCurrentPosition());
            backLDist = Math.abs(backLeft.getTargetPosition() - backLeft.getCurrentPosition());
            backRDist = Math.abs(backRight.getTargetPosition() - backRight.getCurrentPosition());

            telemetry.addData("frontLeft distanceFrom: ", frontLDist);
            telemetry.addData("frontRight distanceFrom: ", frontRDist);
            telemetry.addData("backLeft distanceFrom: ", backLDist);
            telemetry.addData("backRight distanceFrom: ", backRDist);
            telemetry.update();
        } while (
                frontLDist > encoderSafeZone &&
                        frontRDist > encoderSafeZone &&
                        backLDist > encoderSafeZone &&
                        backRDist > encoderSafeZone
                );
        setToStill();
    }

    public void driveElevatorLift(double power, int distance) {
        int elevatorDist;

        elevatorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevatorLift.setTargetPosition(ticksToInch(distance) + elevatorLift.getCurrentPosition());

        elevatorLift.setPower(power);

        do {
            elevatorDist = Math.abs(elevatorLift.getTargetPosition() - elevatorLift.getCurrentPosition());

            telemetry.addData("elevatorLift distanceFrom: ", elevatorDist);
            telemetry.update();
        } while (elevatorDist > encoderSafeZone);

        elevatorLift.setPower(0);
    }

    //set methods
    public void setToStill() {
        setPower(0);
    }

    public void setPower(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(power);
        backLeft.setPower(power);
    }

    public void setDriveMotorMode(DcMotor.RunMode mode) {
        switch (mode) {
            case RUN_USING_ENCODER:
                if (frontLeft.getMode() == DcMotor.RunMode.RUN_USING_ENCODER)
                    break;
                frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                break;
            case RUN_WITHOUT_ENCODER:
                if (frontLeft.getMode() == DcMotor.RunMode.RUN_WITHOUT_ENCODER)
                    break;
                frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                break;
            case STOP_AND_RESET_ENCODER:
                if (frontLeft.getMode() == DcMotor.RunMode.STOP_AND_RESET_ENCODER)
                    break;
                frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                break;
            case RUN_TO_POSITION:
                if (frontLeft.getMode() == DcMotor.RunMode.RUN_TO_POSITION)
                    break;
                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                break;
        }
    }

    //set methods for use with encoders
    public void setMotorEncoderForward(int distance) {
        frontLeft.setTargetPosition(distance);
        frontRight.setTargetPosition(distance);
        backLeft.setTargetPosition(distance);
        backRight.setTargetPosition(distance);
    }

    /*public void setMotorEncoderBackward(int distance) {
        frontLeft.setTargetPosition(-distance);
        frontRight.setTargetPosition(-distance);
        backLeft.setTargetPosition(-distance);
        backRight.setTargetPosition(-distance);
    }*/
    public void setMotorEncoderLeft(int distance) {
        frontLeft.setTargetPosition(-distance);
        frontRight.setTargetPosition(distance);
        backLeft.setTargetPosition(distance);
        backRight.setTargetPosition(-distance);
    }

    public void setMotorEncoderRight(int distance) {
        frontLeft.setTargetPosition(distance);
        frontRight.setTargetPosition(-distance);
        backLeft.setTargetPosition(-distance);
        backRight.setTargetPosition(distance);
    }

    public void setMotorEncoderClockwise(int distance) {
        frontLeft.setTargetPosition(distance);
        frontRight.setTargetPosition(-distance);
        backLeft.setTargetPosition(distance);
        backRight.setTargetPosition(-distance);
    }

    public void setMotorEncoderCounterwise(int distance) {
        frontLeft.setTargetPosition(-distance);
        frontRight.setTargetPosition(distance);
        backLeft.setTargetPosition(-distance);
        backRight.setTargetPosition(distance);
    }

    public void setToCounterwise(double power) {
        frontLeft.setPower(-1 * power);
        frontRight.setPower(1 * power);
        backLeft.setPower(-1 * power);
        backRight.setPower(1 * power);
    }

    public void setToClockwise(double power) {
        frontLeft.setPower(1 * power);
        frontRight.setPower(-1 * power);
        backLeft.setPower(1 * power);
        backRight.setPower(-1 * power);
    }

    public void setToForward(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }

    public void setToBackward(double power) {
        frontLeft.setPower(-1 * power);
        frontRight.setPower(-1 * power);
        backLeft.setPower(-1 * power);
        backRight.setPower(-1 * power);
    }

    public void setToRight(double power) {
        frontLeft.setPower(1 * power);
        frontRight.setPower(-1 * power);
        backLeft.setPower(-1 * power);
        backRight.setPower(1 * power);
    }

    public void setToLeft(double power) {
        frontLeft.setPower(-1 * power);
        frontRight.setPower(1 * power);
        backLeft.setPower(1 * power);
        backRight.setPower(-1 * power);
    }

    //drive methods by time
    public void driveForwardTime(double power, int time) {
        setToForward(power);
        waiter(time);
        setToStill();
    }

    public void driveBackwardTime(double power, int time) {
        setToBackward(power);
        waiter(time);
        setToStill();
    }

    public void driveClockwiseTime(double power, int time) {
        setToClockwise(power);
        waiter(time);
        setToStill();
    }

    public void driveCounterwiseTime(double power, int time) {
        setToCounterwise(power);
        waiter(time);
        setToStill();
    }

    public void driveLeftTime(double power, int time) {
        setToLeft(power);
        waiter(time);
        setToStill();
    }

    public void driveRightTime(double power, int time) {
        setToRight(power);
        waiter(time);
        setToStill();
    }

    //keeps track of the time
    public void waiter(int time) {
        timer.reset();
        while (timer.milliseconds() < time) {
        }
    }
}


