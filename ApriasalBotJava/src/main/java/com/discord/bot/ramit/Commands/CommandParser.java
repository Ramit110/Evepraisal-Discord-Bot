package com.discord.bot.ramit.Commands;

public class CommandParser {
    private CommandBase command;

    public CommandParser(String[] message)
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

    public String runCommand()
    {
        if(command != null)
        {
            return command.run();
        }
        return "";
    }
}
