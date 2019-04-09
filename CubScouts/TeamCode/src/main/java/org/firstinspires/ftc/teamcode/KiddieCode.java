package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.DWAIEventBasedTeleOp.EventHandler;

@TeleOp(name = "Kiddie Code")
public class KiddieCode extends LinearOpMode {
    //Setting up hardware
    private DcMotor RF;
    private DcMotor LF;
    private DcMotor RB;
    private DcMotor LB;
    private DcMotor Lift;
    private Servo Arm;

    public void mapHardware(){
        RF = hardwareMap.get(DcMotor.class, "RF");
        LF = hardwareMap.get(DcMotor.class, "LF");
        RB = hardwareMap.get(DcMotor.class, "RB");
        LB = hardwareMap.get(DcMotor.class, "LB");
        Lift = hardwareMap.get(DcMotor.class, "Lift");
        Arm = hardwareMap.get(Servo.class, "Arm");
        RF.setDirection(DcMotor.Direction.REVERSE);
        LF.setDirection(DcMotor.Direction.FORWARD);
        RB.setDirection(DcMotor.Direction.REVERSE);
        LB.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void runOpMode(){
        mapHardware();

        double p1;
        double p2;

        waitForStart();

        while(opModeIsActive()){
            p1 = gamepad1.left_stick_y;
            p2 = gamepad1.right_stick_y;

            LF.setPower(p1);
            LB.setPower(p1);
            RF.setPower(p2);
            RB.setPower(p2);

            if(gamepad1.a) {
                Lift.setPower(1);
            } else if(gamepad1.y){
                Lift.setPower(-1);
            } else{
                Lift.setPower(0);
            }

            if(gamepad1.left_trigger > 0.5){
                Arm.setPosition(0.7);
            } else if(gamepad1.right_trigger > 0.5){
                Arm.setPosition(0);
            }

        }

    }

}
