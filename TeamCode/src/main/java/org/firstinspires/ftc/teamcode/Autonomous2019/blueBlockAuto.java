package org.firstinspires.ftc.teamcode.Autonomous2019;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Hardware;

@Autonomous(name="blueBlockAuto", group="Linear Opmode")
//@Disabled
public class blueBlockAuto extends LinearOpMode {

    Hardware r = new Hardware();

    //distance that elevator lift lowers from lander
    int elevatorDistance = -50;
    @Override
    public void runOpMode() {
        AutoTransitioner.transitionOnStop(this, "teleop_1");
        r.initRobot(hardwareMap, telemetry);

        //setting an elevator lift mode as STOP_AND_RESET_ENCODER
        r.setDriveMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r.elevatorLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//setting an elevator lift mode as RUN_USING_ENCODER
        r.setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        r.elevatorLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //r.teamMarkerPlacer.setPosition(1);
        //setting the power of the elevator lift
        r.elevatorLift.setPower(.1);
        //setting the target position of the elevator lift
        r.elevatorLift.setTargetPosition(0);

        waitForStart();

        r.driveElevatorLift(-.5, elevatorDistance);
        //moves the bot away from the hook on the lander
        r.driveLeftEncoder(.5, 9);
        //moves us forward for 66 inches
        //r.driveForwardEncoder(.5, 66);
        //runs the servo that drops the team marker
        //r.teamMarkerPlacer.setPosition(1);

        /*
        //reverses the bot
        r.driveForwardBackwardEncoder(-.5, 3);
        //turns the bot counterclockwise
        r.setMotorEncoderCounterwise(10);
        //drives bot into crater
        r.driveForwardBackwardEncoder(.5, 50);
        */


        //end of autonomous period
    }
}
