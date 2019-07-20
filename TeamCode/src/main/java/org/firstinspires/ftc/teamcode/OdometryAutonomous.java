package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


//@Disabled
public abstract class OdometryAutonomous extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private int pulseLeftX;
    private int pulseRightX;
    private int pulseRightY;
    private double inchLeftX;
    private double inchRightX;
    private double inchRightY;
    private final double pulseToInch = .0032639031;
    double lastRY = 0;
    double lastRX = 0;
    double lastLX = 0;
    double diffRY = 0;
    double diffRX = 0;
    double diffLX = 0;
    double dX = 0;
    double dY = 0;
    double dT = 0;
    private double fieldX;
    private double fieldY;
    private double fieldT;


    public void setConfig()
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
    public void initCoords(double x, double y, double t)
    {
         fieldX = x;
         fieldY = y;
         fieldT = Math.toRadians(t);

    }
    public double getFieldX()
    {
        return fieldX;
    }
    public double getFieldY()
    {
        return fieldY;
    }
    public double getFieldT()
    {
        return fieldT;
    }
    public void updateposition()
    {
        pulseRightY = frontRight.getCurrentPosition();
        pulseRightX = backRight.getCurrentPosition();
        pulseLeftX = frontLeft.getCurrentPosition();

        inchRightY = pulseRightY * pulseToInch;
        inchRightX = pulseRightX * pulseToInch * -1;
        inchLeftX = pulseLeftX * pulseToInch * -1;

        diffRY = inchRightY-lastRY;
        diffRX= inchRightX-lastRX;
        diffLX = inchLeftX-lastLX;

        dX =(diffLX+ diffRX)/2;
        dY = diffRY  + 16*dT/(2*Math.PI);
        dT = (diffLX-diffRX)/14.5;


        fieldX += (dX * Math.cos(fieldT) - dY * Math.sin(fieldT));
        fieldY += (dX *Math.sin(fieldT) + dY * Math.cos(fieldT));
        fieldT += dT;

        lastRY = inchRightY;
        lastRX = inchRightX;
        lastLX = inchLeftX;

        if (fieldT >= 2*Math.PI) fieldT -= 2*Math.PI;
        else if (fieldT<0) fieldT += 2*Math.PI;






        telemetry.addData("x coordinate: ", fieldX);
        telemetry.addData("y coordinate: ", fieldY);
        telemetry.addData("t coordinate: ", Math.toDegrees(fieldT)  );
        telemetry.update();
        telemetry.clear();
    }

    public void strafe(double power, double direction)
    {
        direction = 90 - direction;
        direction = Math.toRadians(direction);

        double x = Math.cos(direction);
        double y =  Math.sin(direction);

        frontLeft.setPower(y + x);
        frontRight.setPower(y - x);
        backLeft.setPower(y - x);
        backRight.setPower(y + x);

        telemetry.addData("cos:" , x);
        telemetry.addData("sin:", y);
        telemetry.update();
        telemetry.clear();
    }

    public void setTheta(double targetTheta, double power)     //degrees input
    {
        double num = 15;
       double  t = Math.toDegrees(fieldT);
        if(Math.abs(t-targetTheta)>1)
        {
            while (Math.abs(t - targetTheta) > .02) {
                updateposition();
                t = Math.toDegrees(fieldT);

                if (Math.abs(t - targetTheta) < num) {
                    power = power / 1.2;
                    num = num / 2;
                }

                if ((targetTheta < t) && (targetTheta <= t - 180)) {
                    frontLeft.setPower(power);
                    frontRight.setPower(-power);
                    backLeft.setPower(power);
                    backRight.setPower(-power);
                } else if ((targetTheta < t) && (targetTheta > t - 180)) {
                    frontLeft.setPower(-power);
                    frontRight.setPower(power);
                    backLeft.setPower(-power);
                    backRight.setPower(power);
                } else if ((targetTheta > t) && (targetTheta <= t + 180)) {
                    frontLeft.setPower(power);
                    frontRight.setPower(-power);
                    backLeft.setPower(power);
                    backRight.setPower(-power);
                } else {
                    frontLeft.setPower(-power);
                    frontRight.setPower(power);
                    backLeft.setPower(-power);
                    backRight.setPower(power);
                }
            }
        }
    }
    public void forward(double power)
    {
        frontLeft.setPower(0.5*power);
        frontRight.setPower(0.5*power);
        backLeft.setPower(0.5*power);
        backRight.setPower(0.5*power);
        sleep(200);
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }
    public void backward(double power)
    {
        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(-power);
    }
    public void zeroPower()
    {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public double target(double x, double y) //returns degrees
    {
        double dx = x-fieldX;
        double dy = y-fieldY;
        double targetTheta = Math.atan2(dy,dx);
        if (targetTheta >= 2*Math.PI) targetTheta -= 2*Math.PI;
        else if (targetTheta<0) targetTheta += 2*Math.PI;
        targetTheta=Math.toDegrees(targetTheta);
        return targetTheta;
    }
    public void driveTo (double targetX, double targetY, double targetTheta, double power) // degrees input
    {
        double num = 4;

        while(Math.abs(fieldX - targetX) > 1.5 || Math.abs(fieldY - targetY) > 1.5 || Math.abs(fieldT - targetTheta) > 1)
        {
            while(Math.abs(fieldX - targetX) > 1.5 || Math.abs(fieldY - targetY) > 1.5)
            {
                updateposition();

                if(Math.abs(target(targetX, targetY)-Math.toDegrees(fieldT)) > 15)
                {
                    setTheta(target(targetX, targetY), .4);
                }

                if (Math.abs(fieldX - targetX) < 10 && Math.abs(fieldY - targetY) < 10)
                {
                    power = .4;
                }

                forward(power);
            }
            updateposition();
            setTheta(targetTheta,0.4);
        }
    }


}