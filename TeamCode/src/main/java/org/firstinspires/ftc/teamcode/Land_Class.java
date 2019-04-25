package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous (name = "Land")
public class Land_Class extends Move_Engine
{
    public void runOpMode()
    {
        setConfig();
        initVuforia();
        initTfod();

        /** Wait for the game to begin*/
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();

        Standby();
        Land();
    }
}
