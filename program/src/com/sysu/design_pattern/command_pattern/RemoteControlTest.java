package com.sysu.design_pattern.command_pattern;

public class RemoteControlTest {

    public static void main(String[] args) {
        SimpleRemoteControl control = new SimpleRemoteControl();

        Light light = new Light();
//        LightCommand lightOn = new LightCommand(light);
        LightOnCommand lightOn = new LightOnCommand(light);
        LightOffCommand lightOff = new LightOffCommand(light);

        control.setCommand(lightOn, 0, lightOff);
        control.onButtonWasPressed(0);
        control.undoButtonWasPressed();

    }

}
