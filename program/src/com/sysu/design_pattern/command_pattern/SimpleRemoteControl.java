package com.sysu.design_pattern.command_pattern;

public class SimpleRemoteControl {

    Command[] onCommands;
    Command[] offCommands;

    Command undoCommand;

    public SimpleRemoteControl() {
        Command command = new NoCommand(); //用一个空对象，可以避免很多应为null造成的麻烦

        onCommands = new Command[7];
        offCommands = new Command[7];
        for (int i=0; i < 7; i++) {
            onCommands[i] = command;
        }

        undoCommand = command;
    }

    public void setCommand(Command onCommand, int slot, Command offCommand) {
        this.onCommands[slot] = onCommand;
        this.offCommands[slot] = offCommand;
    }

    public void onButtonWasPressed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }
    public void offButtonWasPressed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void undoButtonWasPressed() {
        undoCommand.undo();
    }
}
