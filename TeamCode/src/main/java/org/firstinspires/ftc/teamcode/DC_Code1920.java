package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Main DC", group="Iterative Opmode")
//@Disabled
public class DC_Code1920 extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    double dampener = 1;

    private int pulseLeftX = 0;
    private int pulseRightX = 0;
    private int pulseRightY = 0;
    private double inchLeftX = 0;
    private double inchRightX = 0;
    private double inchRightY = 0;
    private final double pulseToInch = .0032639031;
    private double dX = 0;
    private double dY = 0;
    private double t = 0;
    private double x = 0;
    private double y = 0;
    private double thatha = 0;

    public void init()
    {
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontLeft.setDirection(DcMotor.Direction.FORWARD);

        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backLeft.setDirection(DcMotor.Direction.FORWARD);

        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        frontRight.setDirection(DcMotor.Direction.REVERSE);

        backRight = hardwareMap.get(DcMotor.class, "back_right");
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    @Override
    public void start()
    {
        runtime.reset();
    }

    public void loop() {
        telemetry.addData("Running", " :)");
        telemetry.update();
        telemetry.clear();
        telemetry.addData("Running", ";)");
        telemetry.update();
        telemetry.clear();
        dampener = 1-(0.7*(gamepad1.left_trigger));
        if (gamepad1.dpad_up) {
            frontLeft.setPower(1*dampener);
            frontRight.setPower(1*dampener);
            backLeft.setPower(1*dampener);
            backRight.setPower(1*dampener);
        } else if (gamepad1.dpad_down) {
            frontLeft.setPower(-1*dampener);
            frontRight.setPower(-1*dampener);
            backLeft.setPower(-1*dampener);
            backRight.setPower(-1*dampener);
        } else if (gamepad1.dpad_left) {
            frontLeft.setPower(-1*dampener);
            frontRight.setPower(1*dampener);
            backLeft.setPower(1*dampener);
            backRight.setPower(-1*dampener);
        } else if (gamepad1.dpad_up) {
            frontLeft.setPower(1*dampener);
            frontRight.setPower(-1*dampener);
            backLeft.setPower(-1*dampener);
            backRight.setPower(1*dampener);
        } else {
            frontLeft.setPower(((-gamepad1.left_stick_y + gamepad1.left_stick_x) + (gamepad1.right_stick_x))*dampener);
            frontRight.setPower(((-gamepad1.left_stick_y - gamepad1.left_stick_x) - (gamepad1.right_stick_x))*dampener);
            backLeft.setPower(((-gamepad1.left_stick_y - gamepad1.left_stick_x) + (gamepad1.right_stick_x))*dampener);
            backRight.setPower(((-gamepad1.left_stick_y + gamepad1.left_stick_x) - (gamepad1.right_stick_x))*dampener);

            pulseLeftX = frontLeft.getCurrentPosition();
            pulseRightY = frontRight.getCurrentPosition();
            pulseRightX = backRight.getCurrentPosition();

            inchLeftX = pulseLeftX * pulseToInch * -1;
            inchRightX = pulseRightX * pulseToInch * -1;
            inchRightY = pulseRightY * pulseToInch;

            t = (inchLeftX - inchRightX)/14.5;
            dX = (inchLeftX + inchRightX)/2;
            dY = inchRightY  + 16*t/(2*Math.PI);

            x = dX * Math.cos(t) - dY * Math.sin(t);
            y = dX*Math.sin(t) + dY * Math.cos(t);
            //thatha = t + thatha

            telemetry.addData("Left x: ",  inchLeftX);
            telemetry.addData("Right y: " , inchRightY);
            telemetry.addData("Right x: " , inchRightX);
            telemetry.addData("dTheta: ", t);
            telemetry.addData("dX: ", dX);
            telemetry.addData("dY: ", dY);
            telemetry.addData("x coordinate: ", x);
            telemetry.addData("y coordinate: ", y);
            telemetry.update();
            telemetry.clear();
        }
    }

    @Override
    public void stop() { }
}
