package org.firstinspires.ftc.teamcode.DWAIEventBasedTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.*;

public class EventHandler extends LinearOpMode {
    /*Override methods accordingly to assign buttons
    * to different functions. Number indicates which gamepad.
    * Be sure to override mapHardware and add private components.
    * The defaultState is overridden by any gamepad events.
    * Priority of buttons can be assigned within the method.
    * (if not blah, then blah, this is to avoid conflicts) */

    public void mapHardware(){

    }

    @Override
    public void runOpMode(){
        mapHardware();

        waitForStart();

        telemetry.addLine("Creating thread");
        telemetry.update();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                telemetry.clearAll();
                telemetry.addLine("Entered thread");
                telemetry.update();

                while(opModeIsActive()) {
                    telemetry.clear();
                    defaultState();

                    if (!gamepad1.atRest()) {
                        gamepadEventDetector1();
                    } else {
                        gamepad1Default();
                    }

                    if (!gamepad2.atRest()) {
                        gamepadEventDetector2();
                    } else {
                        gamepad2Default();
                    }

                    telemetry.update();
                }

            }
        });

        t.setPriority(Thread.NORM_PRIORITY);
        t.start();

        while(opModeIsActive()) {
        }

        t.interrupt();

        telemetry.clearAll();
        telemetry.addLine("Finished thread");
        telemetry.update();
    }

    protected void gamepad1Default(){

    }

    protected void gamepad2Default(){

    }

    protected void onA1(){

    }

    protected void onA2(){

    }

    protected void onB1(){

    }

    protected void onB2(){

    }

    protected void onBack1(){

    }

    protected void onBack2(){

    }

    protected void onDpadDown1(){

    }

    protected void onDpadDown2(){

    }

    protected void onDpadLeft1(){

    }

    protected void onDpadLeft2(){

    }

    protected void onDpadRight1(){

    }

    protected void onDpadRight2(){

    }

    protected void onDpadUp1(){

    }

    protected void onDpadUp2(){

    }

    protected void onGuide1(){

    }

    protected void onGuide2(){

    }

    protected void onLeftBumper1(){

    }

    protected void onLeftBumper2(){

    }

    protected void onLeftStickButton1(){

    }

    protected void onLeftStickButton2(){

    }

    protected void onRightBumper1(){

    }

    protected void onRightBumper2(){

    }

    protected void onRightStickButton1(){

    }

    protected void onRightStickButton2(){

    }

    protected void onStart1(){

    }

    protected void onStart2(){

    }

    protected void onX1(){

    }

    protected void onX2(){

    }

    protected void onY1(){

    }

    protected void onY2(){

    }

    protected void onRightJoystick1(){

    }

    protected void onRightJoystick2(){

    }

    protected void onRightTrigger1(){

    }

    protected void onRightTrigger2(){

    }

    protected void onLeftJoystick1(){

    }

    protected void onLeftJoystick2(){

    }

    protected void onLeftTrigger1(){

    }

    protected void onLeftTrigger2(){

    }

    protected void defaultState(){

    }

    private void gamepadEventDetector1(){

            onA1();


            onB1();


            onBack1();


            onDpadDown1();


            onDpadLeft1();


            onDpadRight1();


            onDpadUp1();


            onGuide1();


            onLeftBumper1();


            onLeftStickButton1();


            onRightBumper1();


            onRightStickButton1();


            onStart1();


            onX1();


            onY1();


        onRightJoystick1();
        onLeftJoystick1();
        onRightTrigger1();
        onLeftTrigger1();
    }

    private void gamepadEventDetector2(){

        if(gamepad2.a){
            onA2();
        }

        if(gamepad2.b){
            onB2();
        }

        if(gamepad2.back){
            onBack2();
        }

        if(gamepad2.dpad_down){
            onDpadDown2();
        }

        if(gamepad2.dpad_left){
            onDpadLeft2();
        }

        if(gamepad2.dpad_right){
            onDpadRight2();
        }

        if(gamepad2.dpad_up){
            onDpadUp2();
        }

        if(gamepad2.guide){
            onGuide2();
        }

        if(gamepad2.left_bumper){
            onLeftBumper2();
        }

        if(gamepad2.left_stick_button){
            onLeftStickButton2();
        }

        if(gamepad2.right_bumper){
            onRightBumper2();
        }

        if(gamepad2.right_stick_button){
            onRightStickButton2();
        }

        if(gamepad2.start){
            onStart2();
        }

        if(gamepad2.x){
            onX2();
        }

        if(gamepad2.y) {
            onY2();
        }

        onRightJoystick2();
        onLeftJoystick2();
        onRightTrigger2();
        onLeftTrigger2();
    }
}
