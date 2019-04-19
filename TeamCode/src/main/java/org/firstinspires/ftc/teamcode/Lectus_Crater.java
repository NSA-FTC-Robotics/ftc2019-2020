package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
@Autonomous(name = "Lecutus Crater")

public class Lectus_Crater extends Move_Engine
{
    public void runOpMode() {

        setConfig();
        initVuforia();
        initTfod();
        /** Wait for the game to begin */
        Standby();
       strafeRight(1,1);
       dontMove(1000);
       strafeLeft(1,1);



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
