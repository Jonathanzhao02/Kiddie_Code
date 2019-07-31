package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;

import java.util.*;
import android.speech.tts.*;

@TeleOp(name = "Kiddie Code")
public class KiddieCode extends LinearOpMode {
    //Setting up hardware
    private DcMotor RF;
    private DcMotor LF;
    private DcMotor RB;
    private DcMotor LB;
    private DcMotor strafuh;
    private DcMotor Lift;
    private Servo RArm;
    private Servo LArm;

    private double p1;
    private double p2;
    private double left;
    private double right;
    private double max;
    private double strafe;

    private TextToSpeech tts;

    public void mapHardware(){
        RF = hardwareMap.get(DcMotor.class, "RF");
        LF = hardwareMap.get(DcMotor.class, "LF");
        RB = hardwareMap.get(DcMotor.class, "RB");
        LB = hardwareMap.get(DcMotor.class, "LB");
        strafuh = hardwareMap.get(DcMotor.class, "strafuh");
        Lift = hardwareMap.get(DcMotor.class, "Lift");
        RArm = hardwareMap.get(Servo.class, "RArm");
        LArm = hardwareMap.get(Servo.class, "LArm");
        RF.setDirection(DcMotor.Direction.REVERSE);
        LF.setDirection(DcMotor.Direction.FORWARD);
        RB.setDirection(DcMotor.Direction.REVERSE);
        LB.setDirection(DcMotor.Direction.FORWARD);

        tts = new TextToSpeech(hardwareMap.appContext, null);
        tts.setLanguage(Locale.US);
    }

    @Override
    public void runOpMode(){
        mapHardware();

        waitForStart();

        while(opModeIsActive()){
            p1 = gamepad1.left_stick_y;
            p2 = gamepad1.right_stick_x;

            // Combine drive and turn for blended motion.
            left = p1 - p2;
            right = p1 + p2;

            // Normalize the values so neither exceed +/- 1.0
            max = Math.max(Math.abs(left), Math.abs(right));

            if (max > 1.0) {
                left /= max;
                right /= max;
            }

            strafe = gamepad1.left_stick_x;

            LF.setPower(left);
            LB.setPower(left);
            RF.setPower(right);
            RB.setPower(right);
            strafuh.setPower(strafe);

            if(gamepad1.a) {
                Lift.setPower(1);
            } else if(gamepad1.y){
                Lift.setPower(-1);
            } else{
                Lift.setPower(0);
            }

            if(gamepad1.left_trigger > 0.5){
                RArm.setPosition(0);  //Closed
            } else if(gamepad1.right_trigger > 0.5){
                RArm.setPosition(1);    //Open
            }

            if(gamepad1.left_trigger > 0.5){
                LArm.setPosition(1);  //Closed
            } else if(gamepad1.right_trigger > 0.5){
                LArm.setPosition(0);    //Open
            }

            if(gamepad1.b){
                tts.speak("Hello Cub Scouts. My name is Billy.", TextToSpeech.QUEUE_FLUSH, null);
            }

        }

    }

}
