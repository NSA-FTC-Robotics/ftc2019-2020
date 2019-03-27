package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
// import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@TeleOp(name="Jack's Super Secret Test Code", group="Iterative Opmode")
//@Disabled
public class Lecutus_DC extends OpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor slide;
    private DcMotor actuator;
    private DcMotor extender;
    private DcMotor spinner;
    private Servo leftcollector;
    private Servo rightcollector;
    private Servo scorer;

    public void init()
    {
        frontLeft  = hardwareMap.get(DcMotor.class, "front_left");
        frontLeft.setDirection(DcMotor.Direction.FORWARD);

        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        frontRight.setDirection(DcMotor.Direction.REVERSE);

        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backLeft.setDirection(DcMotor.Direction.FORWARD);

        backRight = hardwareMap.get(DcMotor.class, "back_right");
        backRight.setDirection(DcMotor.Direction.REVERSE);

        slide = hardwareMap.get(DcMotor.class, "slide");
        slide.setDirection(DcMotor.Direction.FORWARD);

        actuator = hardwareMap.get(DcMotor.class, "actuator");
        actuator.setDirection(DcMotor.Direction.FORWARD);

        extender = hardwareMap.get(DcMotor.class, "extender");
        extender.setDirection(DcMotor.Direction.REVERSE);

        spinner = hardwareMap.get(DcMotor.class, "spinner");
        spinner.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void start()
    {
        runtime.reset();
    }
    public void loop()
    {
        // Wheel motor power values. Right motor direction is reversed.

        double frontleftWheelPower = ((gamepad1.left_stick_y - gamepad1.left_stick_x) + (gamepad1.right_stick_x));
        double frontrightWheelPower = ((gamepad1.left_stick_y - gamepad1.left_stick_x) - (gamepad1.right_stick_x));
        double backleftWheelPower = ((gamepad1.left_stick_y + gamepad1.left_stick_x) + (gamepad1.right_stick_x));
        double backrightWheelPower = ((gamepad1.left_stick_y + gamepad1.left_stick_x) - (gamepad1.right_stick_x));
        if(gamepad1.dpad_up)
        {
            actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            actuator.setTargetPosition(1000);
            actuator.setPower(1);
            while(actuator.isBusy())
            {

            }
            actuator.setPower(0);

        }
        if(gamepad1.dpad_down)
        {
            actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            actuator.setTargetPosition(-1000);
            actuator.setPower(-1);
            while(actuator.isBusy())
            {

            }
            actuator.setPower(0);

        }
        slide.setPower(gamepad1.left_trigger-gamepad1.right_trigger);
        extender.setPower(-gamepad2.right_stick_y);
        if(gamepad2.right_bumper)
            spinner.setPower(1);
        if(gamepad2.left_bumper)
            spinner.setPower(-0.25);
        if(gamepad2.x)
            spinner.setPower(0);

        if(gamepad1.a)
            scorer.setPosition(0.75);
        else scorer.setPosition(0);


        frontLeft.setPower(frontleftWheelPower);
        frontRight.setPower(frontrightWheelPower);
        backLeft.setPower(backleftWheelPower);
        backRight.setPower(backrightWheelPower);
    }
    @Override
    public void stop() { }
}
