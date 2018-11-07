package com.sysu.design_pattern.command_pattern;

public class GarageDoorOpenCommand implements Command {

    GarageDoor door; //命令接受者

    public GarageDoorOpenCommand(GarageDoor door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.up();
    }

    @Override
    public void undo() {
        door.down();
    }
}
