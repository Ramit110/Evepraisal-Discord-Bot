package com.discord.bot.ramit.Commands;

public class CommandBase {

    protected String[] command;
    protected int errorNumber;

    CommandBase(String[] newCommand)
    {
        command = newCommand;
    }
}
