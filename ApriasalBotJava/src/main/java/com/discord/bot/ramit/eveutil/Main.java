package com.discord.bot.ramit.eveutil;

import com.discord.bot.ramit.Commands.*;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

    public static void main(String[] args){

        DiscordApi api = new DiscordApiBuilder().setToken(Secrets.BotID).login().join();

        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(
            event ->
            {
                event.getChannel().sendMessage(
                        new CommandParser(
                                event.getMessage().getContent().split("\n")
                        ).runCommand()
                );
            }
        );

        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }
}
