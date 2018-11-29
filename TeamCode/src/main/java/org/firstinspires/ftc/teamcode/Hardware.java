package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Hardware
{
    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;

    public DcMotor elevatorLift;
    public DcMotor intakeSpinner;


    Telemetry telemetry;
    HardwareMap hwMap;

    /*a motor must be within this many ticks of its
	target to be considered "on target"*/
    private static final int encoderSafeZone=50;


//init methods
    public void initRobot(HardwareMap spareMap, Telemetry tempTelemetry)
    {
        hwMap = spareMap;
        telemetry = tempTelemetry;
        initHardware();
    }

    public void initHardware()
    {
        frontLeft = hwMap.dcMotor.get("front left wheel");
        frontRight = hwMap.dcMotor.get("front right wheel");
        backLeft = hwMap.dcMotor.get("back left wheel");
        backRight = hwMap.dcMotor.get("back right wheel");

        elevatorLift = hwMap.dcMotor.get("elevator lift motor");
        intakeSpinner = hwMap.dcMotor.get("intake spinner motor");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    //converts inches into motor ticks for Rev HD hex motors
    public int ticksToInch(int distance)
    {
        //ticks per inch = counts per rotation of Rev motors / circumference of wheels
        //ticks = 2240/(4*Math.PI);
        return distance*178;
    }


//drive encoder methods
    public void driveForwardBackwardEncoder(double power, int distance)
    {
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
    }

//set methods
    public void setToStill()
    {
        setPower(0);
    }

    public void setPower(double power)
    {
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
    public void setMotorEncoderBackward(int distance) {
        frontLeft.setTargetPosition(-distance);
        frontRight.setTargetPosition(-distance);
        backLeft.setTargetPosition(-distance);
        backRight.setTargetPosition(-distance);
    }
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
}


