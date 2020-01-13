package com.discord.bot.ramit.eveutil.Misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.discord.bot.ramit.eveutil.Secrets;
import org.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.message.BasicNameValuePair;

public class EvepraisalAPI {
    private static HttpPost post = new HttpPost("https://evepraisal.com/appraisal.json");

    public static JSONObject Mainapraisal(String[] requestData, String[] parameters) throws IOException
    {
        // Create the client, gotta love trainwrecks
        HttpClient client = HttpClients.custom()
            .setDefaultRequestConfig(RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD).build()
            ).build();

        // create the raw text
        String mainData = "";
        for (String item: requestData) {
            mainData += item + "\n";
        }

        // create the parameters then set the entity
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>(
            Arrays.asList(
                new BasicNameValuePair("User-Agent", Secrets.BotName),
                new BasicNameValuePair("market", "jita"),
                new BasicNameValuePair("raw_textarea", mainData)
            )
        );

        for(int x = 0; x < parameters.length; x++)
        {
            if(parameters[x] == "-p" && x < parameters.length)
            {
                urlParameters.add(new BasicNameValuePair("", parameters[x+1]));
            }
        }

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        // collect the response
        HttpResponse response = client.execute(post);

        // create a reader from the input stream
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent())
        );

        // read from it
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        // create and return
        return new JSONObject(result.toString());
    }

}
