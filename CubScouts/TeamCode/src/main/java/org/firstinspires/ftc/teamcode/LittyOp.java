package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.DWAIEventBasedTeleOp.EventHandler;

import android.speech.tts.*;
import java.util.Locale;

@TeleOp(name = "Litty Op")
public class LittyOp extends EventHandler {
    private DcMotor L;
    private DcMotor R;
    private DcMotor S;

    private TextToSpeech tts;

    public void mapHardware(){
        L = hardwareMap.get(DcMotor.class, "L");
        R = hardwareMap.get(DcMotor.class, "R");
        S = hardwareMap.get(DcMotor.class, "S");

        L.setDirection(DcMotor.Direction.REVERSE);
        R.setDirection(DcMotor.Direction.FORWARD);
        S.setDirection(DcMotor.Direction.REVERSE);
        S.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        S.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        tts = new TextToSpeech(hardwareMap.appContext, null);
        tts.setLanguage(Locale.US);
    }

    protected void onA1(){

        if(gamepad1.a) {
            telemetry.addLine("In A");

            tts.speak("Bruh", TextToSpeech.QUEUE_FLUSH, null);
        }

    }

    protected void onRightJoystick1(){      //Gas
        telemetry.addLine("In R joystick");

        if(!gamepad1.right_bumper && !gamepad1.left_bumper) {
            L.setPower(gamepad1.right_stick_y);
            R.setPower(gamepad1.right_stick_y);
        }

    }

    protected void onLeftJoystick1(){       //Steering
        telemetry.addLine("In L josystick");

        double p = gamepad1.left_stick_x;
        int eVal = S.getCurrentPosition();

        /*if(   //Deprecated code
            (eVal > 140 && gamepad1.left_stick_x > 0)
                ||
            (eVal < -140 && gamepad1.left_stick_x < 0)){
            S.setPower(0);
        } else{
            S.setPower(gamepad1.left_stick_x * .4);
        }*/

        if (p <= -0.2) {        //Establishes a deadzone

            if (eVal < -165) {          //Motor encoder stops beyond 165
                S.setPower(0);
            } else {
                S.setPower(p * .4);     //Lowered power makes encoder movement more accurate
            }

        } else if (p >= 0.2) {  //Same as above

            if (eVal > 165) {
                S.setPower(0);
            } else {
                S.setPower(p * .4);
            }

        } else {                //Established deadzone code

            if (eVal > 20){     //Automatic return to center
                S.setPower(-0.2);
            } else if (eVal < -20) {
                S.setPower(0.2);
            } else if (eVal < -5){
                S.setPower(0.08);
            } else if (eVal > 5){
                S.setPower(-0.08);
            } else{
                S.setPower(0);
            }

        }

    }

    protected void onLeftBumper1(){     //Handbrake

        if(gamepad1.left_bumper) {
            telemetry.addLine("In L bumper");

            L.setPower(0);
            R.setPower(0);
            L.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
            R.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
        }

    }

    protected void onRightBumper1(){    //Clutchkick

        if (gamepad1.right_bumper) {
            telemetry.addLine("In R bumper");

            L.setPower(0);
            R.setPower(0);
            L.setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);
            R.setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);
        }

    }

    protected void gamepad1Default(){   //If gamepad at rest (no buttons at all)
        telemetry.addLine("In default");

        L.setPower(0);
        R.setPower(0);
        //S.setPower(0);

        int eVal = S.getCurrentPosition();

        if (eVal > 20){     //Automatic return to center
            S.setPower(-0.2);
        } else if (eVal < -20) {
            S.setPower(0.2);
        } else if (eVal < -5){
            S.setPower(0.08);
        } else if (eVal > 5){
            S.setPower(-0.08);
        } else{
            S.setPower(0);
        }

        L.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
        R.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
    }
}
