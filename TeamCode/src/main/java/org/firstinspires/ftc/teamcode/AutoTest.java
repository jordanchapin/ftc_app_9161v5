package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="AutoTest", group="Linear OpMode")
//@Disabled
public class AutoTest extends LinearOpMode {

    Hardware r = new Hardware();

    @Override
    public void runOpMode() {
        r.initRobot(hardwareMap, telemetry);

        waitForStart();

        r.setPower(1);

        r.setDriveMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        r.frontLeft.setTargetPosition(5500);
        r.frontRight.setTargetPosition(5500);
        r.backLeft.setTargetPosition(5500);
        r.backRight.setTargetPosition(5500);

        while(r.frontLeft.isBusy())
        {

        }
        r.setPower(0);

    }
}
