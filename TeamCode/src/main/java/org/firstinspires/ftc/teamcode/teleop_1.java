package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="teleop_1", group="Iterative Opmode")
//@Disabled
public class teleop_1 extends OpMode
{
    Hardware r = new Hardware();

    @Override
    public void init() {
        r.initRobot(hardwareMap, telemetry);

    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {


    }

    boolean inReverse=true;//reverse button is a
    boolean aWasPressed=false;

    @Override
    public void loop() {
        //drive code
        if (gamepad1.a && !aWasPressed)
            inReverse = !inReverse;
        aWasPressed = gamepad1.a;
        //first we must translate the rectangular values of the joystick into polar coordinates
        double y = -1 * gamepad1.left_stick_y;
        double x = 1 * gamepad1.left_stick_x;
        double angle = 0;
        if (y > 0 && x > 0)//quadrant 1
            angle = Math.atan(y / x);
        else if (y > 0 && x < 0)//quadrant 2
            angle = Math.toRadians(180) + Math.atan(y / x);
        else if (y < 0 && x < 0)//quadrant 3
            angle = Math.toRadians(180) + Math.atan(y / x);
        else if (y < 0 && x > 0)//quadrant 4
            angle = Math.toRadians(360) + Math.atan(y / x);
        if (y == 0 && x > 1)
            angle = 0;
        if (y > 0 && x == 0)
            angle = Math.PI / 2;
        if (y == 0 && x < 0)
            angle = Math.PI;
        if (y < 0 && x == 0)
            angle = 3 * Math.PI / 2;
        angle += Math.toRadians(90);
        //now we get the velocity of the robot based on the distance the disanctance the sticks are from the origen
        double velocity = Math.sqrt(Math.pow(gamepad1.left_stick_y, 2) + Math.pow(gamepad1.left_stick_x, 2));
        //now we get the rotation simply based on the right stick's horizontal x value
        double rotation = gamepad1.right_stick_x * -1;

        if (inReverse)//reverse button
            angle += Math.toRadians(180);

        //equations taking the polar coordinates and turing them into motor powers
        double power1 = velocity * Math.cos(angle + (Math.PI / 4)) - rotation;
        double power2 = velocity * Math.sin(angle + (Math.PI / 4)) + rotation;
        double power3 = velocity * Math.sin(angle + (Math.PI / 4)) - rotation;
        double power4 = velocity * Math.cos(angle + (Math.PI / 4)) + rotation;
        r.frontLeft.setPower(power1);
        r.frontRight.setPower(power2);
        r.backLeft.setPower(power3);
        r.backRight.setPower(power4);

        //elevator lift code
        if (gamepad2.dpad_up) {
            r.elevatorLift.setPower(1);
        } else if (gamepad2.dpad_down) {
            r.elevatorLift.setPower(-1);
        } else
            r.elevatorLift.setPower(0);

        //code to run intake
        if (gamepad2.right_trigger > .1) {
            r.intakeSpinner.setPower(-.6);
        } else if (gamepad2.left_trigger > .1) {
            r.intakeSpinner.setPower(.6);
        } else
            r.intakeSpinner.setPower(0);

        //code to lift the intake up and down
        if (gamepad2.right_bumper) {
            if (gamepad2.left_stick_y * -1 > 0) {
                r.intakeArmLift.setPower(gamepad2.left_stick_y * 1);
            } else if (gamepad2.left_stick_y * -1 < 0) {
                r.intakeArmLift.setPower(gamepad2.left_stick_y * -1);
            } else
                r.intakeArmLift.setPower(.1);
        }else{
                if (gamepad2.left_stick_y * -1 > 0) {
                    r.intakeArmLift.setPower(gamepad2.left_stick_y * -.6);
                } else if (gamepad2.left_stick_y * -1 < 0) {
                    r.intakeArmLift.setPower(gamepad2.left_stick_y * -.6);
                } else
                    r.intakeArmLift.setPower(.1);

            }

            //code to extend the intake arm in and out
            double rightY2 = gamepad2.right_stick_y;
            if (rightY2 > .1) {
                r.intakeArmExtender.setPower(rightY2);
            } else if (rightY2 < -.1) {
                r.intakeArmExtender.setPower(rightY2);
            } else
                r.intakeArmExtender.setPower(0);

    }

    @Override
    public void stop() {
    }

}
