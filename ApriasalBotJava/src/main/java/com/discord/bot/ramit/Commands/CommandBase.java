package com.discord.bot.ramit.Commands;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class CommandBase {
    protected String[] parameters;
    protected String[] message;

    public CommandBase(String[] newMessage)
    {
        String[] newParameters = newMessage[0].split(" ");
        parameters = Arrays.copyOfRange(newParameters, 1, newParameters.length-1);
        message = Arrays.copyOfRange(message, 1, message.length-1);
    }

}
