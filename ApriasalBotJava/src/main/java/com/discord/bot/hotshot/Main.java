package com.discord.bot.hotshot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.message.BasicNameValuePair;

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
                            JSONObject request = Mainapraisal(Arrays.copyOfRange(eventBrokenUp, 0, eventBrokenUp.length));
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

    private static JSONObject Mainapraisal(String[] requestData) throws IOException
    {
        String url = "https://evepraisal.com/appraisal.json";

        HttpClient client = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                            .build();

        HttpPost post = new HttpPost(url);
        String mainData = "";
        for (String item: requestData) {
            mainData += item + "\n";
        }

        // add header
        post.setHeader("User-Agent", "Mozilla/5.0");

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        // urlParameters.add(new BasicNameValuePair("User-Agent", "TestDiscordBot"));
        urlParameters.add(new BasicNameValuePair("User-Agent", Secrets.BotName));
        urlParameters.add(new BasicNameValuePair("market", "jita"));
        urlParameters.add(new BasicNameValuePair("raw_textarea", mainData));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return new JSONObject(result.toString());
    }
}
