package org.firstinspires.ftc.teamcode.Autonomous2019;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware;

@Autonomous(name="timerBasedBlockAuto", group="Linear Opmode")
//@Disabled
public class timerBasedBlockAuto extends LinearOpMode {

    Hardware r = new Hardware();

    //distance that elevator lift lowers from lander
    int elevatorDistance = -50;
    @Override
    public void runOpMode() {
        AutoTransitioner.transitionOnStop(this, "teleop_1");
        r.initRobot(hardwareMap, telemetry);

        r.setDriveMotorMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        r.elevatorLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//setting an elevator lift mode as RUN_USING_ENCODER
        r.elevatorLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        r.teamMarkerPlacer.setPosition(1);
        //setting the power of the elevator lift
        r.elevatorLift.setPower(.1);
        //setting the target position of the elevator lift
        r.elevatorLift.setTargetPosition(0);

        waitForStart();

        r.driveElevatorLift(-.5, elevatorDistance);
        //moves the bot away from the hook on the lander
        r.driveLeftTime(.5,400);
        r.driveForwardTime(.4,1850);
        r.driveClockwiseTime(.25,1000);
        r.driveForwardTime(.2, 1000);
        r.teamMarkerPlacer.setPosition(1);
        //r.driveClockwiseTime(.25, 2000);
       // r.driveForwardTime(.8, 7000);

        //runs the servo that drops the team marker
        //r.teamMarkerPlacer.setPosition(1);
//        r.teamMarkerPlacer.setPosition(0);
//        r.driveForwardTime(.8, 8000);


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
