package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;
@Autonomous(name = "Move_Engine Tester")
@Disabled
public class Engine_Tester extends Move_Engine {


    public void runOpMode() {

        setConfig();
        leftcollector.setPosition(1);
        rightcollector.setPosition(0);
        initVuforia();
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        /** Wait for the game to begin */
        Standby();
        //Sample();
        //Land();
        Forward(3000,1);
        dontMove(500);
        rightAngleTurn("LEFT");
        strafeRight(3000,1);
        dontMove(1000);
        spinSpinner(1500, 0.5);
        spinSpinner(1500, 0.5);



        if (opModeIsActive()) {
            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }

            if (tfod != null) {
                tfod.shutdown();
            }
        }
    }
}

