package com.discord.bot.ramit.Commands;

public class CommandBuilder {
    private CommandBase command;

    public CommandBuilder(String[] message)
    {
        switch (message[0].split(" ")[0])
        {
            case "!evepraisal":
                command = new EVEPraisal(message);
                break;
            default:
                command = null;
                break;
        }
    }

    public CommandBase getCommand()
    {
        return command;
    }
}
