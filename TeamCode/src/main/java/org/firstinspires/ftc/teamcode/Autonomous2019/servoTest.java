package org.firstinspires.ftc.teamcode.Autonomous2019;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware;

@Autonomous(name="servoTest", group="Linear Opmode")
//@Disabled
public class servoTest extends LinearOpMode {

    Hardware r = new Hardware();

    //distance that elevator lift lowers from lander
    int elevatorDistance = -50;
    //@Override
    public void runOpMode() {
        AutoTransitioner.transitionOnStop(this, "teleop_1");
        r.initRobot(hardwareMap, telemetry);

         waitForStart();
        r.teamMarkerPlacer.setPosition(0);
        r.waiter(2);
        

        r.teamMarkerPlacer.setPosition(1);
        r.waiter(2);
    }
}
