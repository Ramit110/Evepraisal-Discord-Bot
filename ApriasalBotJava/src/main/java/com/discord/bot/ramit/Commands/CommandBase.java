package com.discord.bot.ramit.Commands;

import java.util.Arrays;

public abstract class CommandBase implements ICommandExecution {
    protected String[] parameters;
    protected String[] message;

    public CommandBase(String[] newMessage)
    {
        String[] newParameters = newMessage[0].split(" ");
        parameters = Arrays.copyOfRange(newParameters, 1, newParameters.length);
        message = Arrays.copyOfRange(newMessage, 1, newMessage.length);
    }

}
