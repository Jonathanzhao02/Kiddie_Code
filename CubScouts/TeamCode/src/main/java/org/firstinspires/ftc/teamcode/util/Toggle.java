package org.firstinspires.ftc.teamcode.util;

public class Toggle{
    boolean toggle;
    boolean currentLoop;
    boolean previousLoop;

    public Toggle(){toggle = false;}   //Constructor (default false)
    public Toggle(boolean start){toggle = start;}  //Constructor

    public void update(boolean loop){
        previousLoop = currentLoop;
        currentLoop = loop;

        if(changed() && currentLoop)toggle = !toggle;
    }

    public boolean get(){
        return toggle;
    }

    public boolean startClick(){
        return changed() && currentLoop;
    }

    public boolean endClick(){
        return changed() && !currentLoop;
    }


    private boolean changed(){
        return currentLoop != previousLoop;
    }
}