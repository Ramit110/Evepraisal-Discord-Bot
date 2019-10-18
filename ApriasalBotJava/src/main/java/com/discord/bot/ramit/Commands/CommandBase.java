package com.discord.bot.ramit.Commands;

public class CommandBase {

    public static CommandBase getCommandFrom(String potentialCommand, String[] message)
    {
        switch (potentialCommand)
        {
            case "evepraisal":
                return new EVEPraisal(message);
            default:
                return null;
        }
    }

    protected String[] command;

    CommandBase(String[] newCommand)
    {
        command = newCommand;
    }
}
