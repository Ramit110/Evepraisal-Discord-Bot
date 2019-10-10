package com.discord.bot.ramit;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args){

        DiscordApi api = new DiscordApiBuilder().setToken(Secrets.BotID).login().join();

        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(
            event ->
            {
                String[] eventBrokenUp = event.getMessage().getContent().split("\n");
                if (eventBrokenUp[0].equalsIgnoreCase("!evepraisal"))
                {
                    if(eventBrokenUp.length == 1)
                    {
                        event.getChannel().sendMessage("You may want to add some.... things to that request.");
                    }
                    else
                    {
                        Main current = new Main();
                        try
                        {
                            JSONObject request = EvepraisalAPI.Mainapraisal(Arrays.copyOfRange(eventBrokenUp, 0, eventBrokenUp.length));
                            request = (JSONObject) request.get("appraisal");
                            request = (JSONObject) request.get("totals");
                            String response = "```Volume is: " + request.get("volume") + "\nBuy value:" + request.get("buy") + "\nSell value:" + request.get("sell") + "```";
                            event.getChannel().sendMessage(response);
                        }
                        catch (IOException e)
                        {
                            event.getChannel().sendMessage("Error 001 - Error while getting data.");
                        }
                        catch (JSONException e)
                        {
                            event.getChannel().sendMessage("Error 001 - Error parsing JSON.");
                        }
                    }
                }
            }
        );

        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }
}
