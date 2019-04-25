package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

/** Move_Engine is a super class we use to hold all of our movement-based methods
 *
 **/
public abstract class Move_Engine extends LinearOpMode {

    public static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    public static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    public static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public DcMotor slide;
    public DcMotor actuator;
    public DcMotor extender;
    public DcMotor spinner;
    public Servo leftcollector;
    public Servo rightcollector;
    public Servo scorer;
    public static final String VUFORIA_KEY = " AQ116pH/////AAABmYKZkP6ruU4ukxsm+1osPgFEdpQlf84kCDF5xhQQR5sUCugeVkoDJNSxZMmVG4iI/ZgvMc93IktiBsRMg2r0bdh1o/t5tOkiiSl+mdsccx1SES40H4FEoIf/D1n5qhQLpT+W656H1Ffe16Hbb86/FC2mJDAes22Ddq7fEeGSTzvFXAjrMnCwZSK90opojAxPitQxTFgjaXz+ZYPfcn7ciJ8DReEBuhcqdiVs54N0Szf0b0fdO5Wo201+rWhI9UVjOli7shY3vQyokxnhjPzVROsYmuKe05llHFPNpuVkWJiEPTBSwPqTQ40mwdX0RV5ortDPcG62bYx3v8hZY/dUVMWjFzdPr+sHJK1Cp2KAbbXj";
    public VuforiaLocalizer vuforia;
    public TFObjectDetector tfod;
    public Move_Engine(){/* Useless Constructor*/}
    public void setConfig(){
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
        scorer.setPosition(0.35);

        leftcollector = hardwareMap.get(Servo.class, "leftcollector");
        leftcollector.setPosition(0);

        rightcollector= hardwareMap.get(Servo.class, "rightcollector");
        rightcollector.setPosition(1);

        spinner = hardwareMap.get(DcMotor.class, "spinner");
        spinner.setDirection(DcMotor.Direction.FORWARD);
    }
    public void Standby(){
        while (!isStarted()) {
            telemetry.addData("Lecutus" + ":", " Waiting for Start.");
            telemetry.update();
            sleep(1000);
            telemetry.clear();
            telemetry.addData("Lecutus" + ":", " Waiting for Start..");
            telemetry.update();
            sleep(1000);
            telemetry.clear();
            telemetry.addData("Lecutus" + ":", " Waiting for Start...");
            telemetry.update();
            sleep(1000);
            telemetry.clear();
        }
    }
    /** dontMove is an overloaded method which stop all drive motors
     *
     * @param time a pause (in milliseconds) before the next movement
     */
    public void dontMove(int time){
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        sleep(time);
    }
    /** dontMove is an overloaded method which stop all drive motors
     *
     */
    public void dontMove(){
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
    /** One of two strafe methods, this one strafes left
     *
     * @param time The number of rotations the motors move.
     * @param pow The percent power (as a decimal) the drive motors run at.
     */
    public void strafeRight(int time, double pow){
        frontLeft.setPower(-1 * pow);
        frontRight.setPower(1 * pow);
        backLeft.setPower(1 * pow);
        backRight.setPower(-1 * pow);
        sleep(time);
    }
    /** One of two strafe methods, this one strafes right
     *
     * @param time The time (in milliseconds) the motors runs for.
     * @param pow The percent power (as a decimal) the drive motors run at.
     */
    public void strafeLeft(int time, double pow){

        frontLeft.setPower(1 * pow);
        frontRight.setPower(-1 * pow);
        backLeft.setPower(-1 * pow);
        backRight.setPower(1 * pow);
        sleep(time);
    }
    /** The Forward method drives Lecutus ...forward.
     * @param time The time in milliseconds to drive for.
     * @param pow The percent power (as a decimal) the drive motors run at.
     */
    public void Backward(int time,double pow){
        frontLeft.setPower(1 * pow);
        backLeft.setPower(1 * pow);
        frontRight.setPower(1 * pow);
        backRight.setPower(1 * pow);
        sleep(time);
    }
    /** The Backward method drives Lecutus
      backwards (surprise surprise..)
     * @param time The time in milliseconds to drive for.
     * @param pow The percent power (as a decimal) the drive motors run at.
     */
    public void Forward(int time,double pow){
        frontLeft.setPower(-1 * pow);
        backLeft.setPower(-1 * pow);
        frontRight.setPower(-1 * pow);
        backRight.setPower(-1 * pow);
        sleep(time);
    }
    /** rightAngleTurn is a method that turns right or left.
     * @param direction  Turns Lecutus Left or Right based on the String given ("L" or "R").
     */
    public void rightAngleTurn(String direction)
    {
        direction.toLowerCase();
        if(direction.startsWith("l")){
            frontLeft.setPower(1);
            backLeft.setPower(1);
            frontRight.setPower(-1);
            backRight.setPower(-1);
            sleep(520);
        } else if(direction.startsWith("r")){
            frontLeft.setPower(-1);
            backLeft.setPower(-1);
            frontRight.setPower(1);
            backRight.setPower(1);
            sleep(200);
        } else telemetry.addLine("lmao who wrote this it doesn't work");
    }

    /**Turn method
     *
     * @param time time to turn
     * @param pow Positive for Right, Negative for left
     */
    public void Turn(int time, double pow){
        frontLeft.setPower(-1 * pow);
        backLeft.setPower(-1 * pow);
        frontRight.setPower(1 * pow);
        backRight.setPower(1 * pow);
        sleep(time);
        dontMove();
    }
    /** The moveArm Method controls the movement of Lecutus's collector arm.
     * @param sec The time (in milliseconds) the motor runs for.
     */
    public void extendArm(int sec){
        extender.setPower(0.6);
        sleep(sec);
        extender.setPower(0);
    }
    public void retractArm(int sec)
    {
        extender.setPower(-0.6);
        sleep(sec);
        extender.setPower(0);
    }
    public void collectDown()
    {
        leftcollector.setPosition(1);
        rightcollector.setPosition(0);
    }
    public void collectUp()
    {
        leftcollector.setPosition(.1);
        rightcollector.setPosition(.9);
    }
    /**
     * Method to Lower Lecutus from the Lander.
     */
    public void Land()
    {

        actuator.setPower(1);
        sleep(8600);
        actuator.setPower(0);
        dontMove();
        Forward(400, 0.3);
        strafeRight(1000,0.5);
        Turn(520,1);

    }
    public void TurnAround()
    {

        frontLeft.setPower(-1);
        backLeft.setPower(-1);
        frontRight.setPower(1);
        backRight.setPower(1);
        sleep(1650);

    }
    public void spinSpinner(int time, double pow){
        spinner.setPower(pow);
        sleep(time);
        spinner.setPower(0);
    }
    public void Sample(){
        boolean found = false;
        int pos = 0;
        while (opModeIsActive()&& !found) {
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    telemetry.addData("# Object Detected", updatedRecognitions.size());
                    if (updatedRecognitions.size() == 2)
                    {
                        int goldMineralX = -1;
                        int silverMineral1X = -1;
                        int silverMineral2X = -1;
                        for (Recognition recognition : updatedRecognitions)
                        {
                            if (recognition.getLabel().equals(LABEL_GOLD_MINERAL))
                            {
                                goldMineralX = (int) recognition.getLeft();
                            }
                            else if (silverMineral1X == -1)
                            {
                                silverMineral1X = (int) recognition.getLeft();
                            }
                            else
                            {
                                silverMineral2X = (int) recognition.getLeft();
                            }
                        }

                        if (goldMineralX == -1)
                        {


                            telemetry.addData("Gold Mineral Position", "Left");
                            found = true;
                            pos = 1;
                        }
                        else if (goldMineralX > silverMineral1X )
                        {
                            telemetry.addData("Gold Mineral Position", "Center");
                            found = true;
                            pos = 2;
                        }
                        else
                        {
                            telemetry.addData("Gold Mineral Position", "Right");
                            found = true;
                            pos = 3;
                        }
                    }
                    telemetry.update();
                }
            }
        }
        if (pos ==3) //Right
        {
            Land();
            Turn(260,1);
            collectDown();
            Forward(1000,0.6);
            collectUp();
            Turn(300,-1);
            dontMove();
        }
        if (pos ==2) //Center
        {
            Land();
            collectDown();
            Forward(1000,0.6);
            collectUp();
        }
        if (pos ==1) //Left
        {
            Land();
            Turn(260,-1);
            collectDown();
            Forward(1000,0.6);
            collectUp();
            Turn(300,1);
            dontMove();
        }

    }
    /**
     * Initialize the Vuforia localization engine.
     */
    public void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }
    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    public void initTfod() {
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                    "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
            tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
            tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

    }
}