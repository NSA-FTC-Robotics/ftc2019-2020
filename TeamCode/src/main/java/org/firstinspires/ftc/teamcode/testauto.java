package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous (name = "Test Depot scoring auto(No changes made to other code)")
@Disabled
public class testauto extends Move_Engine {
    public void runOpMode() {
        setConfig();
        initVuforia();
        initTfod();

        /** Wait for the game to begin*/
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();

        Standby();
        Land();
        Turn(220,1);
        spinSpinner(5000,1);
        collectDown();
        Forward(300,0.5);
        collectUp();
        spinSpinner(3500, -1);
        Backward(500,0.6);
        Turn(300,-1);
        dontMove();
    }
}
