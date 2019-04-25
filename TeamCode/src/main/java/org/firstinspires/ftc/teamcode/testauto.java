package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous (name = "Test Auto")
public class testauto extends Move_Engine {
    public void runOpMode() {
        setConfig();
        initVuforia();
        initTfod();

        /** Wait for the game to begin*/
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();

        Standby();
        Turn(260,1);
        collectDown();
        extendArm(4000);
        collectUp();
        Turn(260,-1);
        dontMove();
    }
}
