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
@TeleOp(name="Lecutus DC", group="Iterative Opmode")
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
    int inCrater = 0;


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

        scorer = hardwareMap.get(Servo.class, "scorer");
        scorer.setPosition(0.1);

        leftcollector = hardwareMap.get(Servo.class, "leftcollector");
        leftcollector.setPosition(1);

        rightcollector= hardwareMap.get(Servo.class, "rightcollector");
        rightcollector.setPosition(0);

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
        telemetry.addData("Running", " :)");
        telemetry.update();
        telemetry.clear();
        telemetry.addData("Running",";)" );
        telemetry.update();
        telemetry.clear();


        // Wheel motor power values. Right motor direction is reversed.
        double frontleftWheelPower = ((gamepad1.left_stick_y - gamepad1.left_stick_x) - (gamepad1.right_stick_x));
        double frontrightWheelPower = ((gamepad1.left_stick_y + gamepad1.left_stick_x) + (gamepad1.right_stick_x));
        double backleftWheelPower = ((gamepad1.left_stick_y + gamepad1.left_stick_x) - (gamepad1.right_stick_x));
        double backrightWheelPower = ((gamepad1.left_stick_y - gamepad1.left_stick_x) + (gamepad1.right_stick_x));
        if(gamepad1.dpad_up)
        {
            /*actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            actuator.setTargetPosition(1000);
            actuator.setPower(1);
            while(actuator.isBusy())
            {

            }*/
            actuator.setPower(1);

        }
         else if(gamepad1.dpad_down)
        {

            actuator.setPower(-1);

        }
        else actuator.setPower(0);

        if(gamepad1.left_trigger-gamepad1.right_trigger<0)
        slide.setPower((gamepad1.left_trigger-gamepad1.right_trigger)*0.6);
        else slide.setPower(gamepad1.left_trigger-gamepad1.right_trigger);
        if (gamepad2.right_stick_y>0)
            extender.setPower(0.6 *gamepad2.right_stick_y);
        else extender.setPower(gamepad2.right_stick_y);


        if(gamepad2.dpad_up) //transfer
        {
            leftcollector.setPosition(.1);
            rightcollector.setPosition(.9);
        }
        if(gamepad2.dpad_right||gamepad2.dpad_left) //up
        {
            leftcollector.setPosition(0.5);
            rightcollector.setPosition(0.5);
        }
        if (gamepad2.dpad_down) //collect
        {
            leftcollector.setPosition(1);
            rightcollector.setPosition(0);
        }


        if(gamepad2.right_bumper)
            spinner.setPower(1);
        if(gamepad2.left_bumper)
            spinner.setPower(-1);
        if(gamepad2.x)
            spinner.setPower(0);






        frontLeft.setPower(frontleftWheelPower);
        frontRight.setPower(frontrightWheelPower);
        backLeft.setPower(backleftWheelPower);
        backRight.setPower(backrightWheelPower);

        if(gamepad1.a) scorer.setPosition(0.5);
        else if (gamepad1.b)scorer.setPosition(0.35);
        else scorer.setPosition(0.1);
    }
    @Override
    public void stop() { }
}
