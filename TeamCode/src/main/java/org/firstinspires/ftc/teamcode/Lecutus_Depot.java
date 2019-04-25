
package org.firstinspires.ftc.teamcode;

import android.nfc.FormatException;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


/**
 * This 2018-2019 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the gold and silver minerals.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@Autonomous(name = "Lecutus Depot")
//@Disabled
public class Lecutus_Depot extends Move_Engine {

    @Override
    public void runOpMode() {
        setConfig();
        initVuforia();
        initTfod();

        /** Wait for the game to begin*/
         telemetry.addData(">", "Press Play to start tracking");
         telemetry.update();

         Standby();

        if (opModeIsActive()) {
            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }

            //Sample();
            Land();
            Forward(1000,1);
            dontMove();
            collectDown();
            spinSpinner(2000, 1);


            if (tfod != null) {
                tfod.shutdown();
            }
        }


    }
}

