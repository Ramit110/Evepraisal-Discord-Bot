package com.discord.bot.ramit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

    private static String url = "https://evepraisal.com/appraisal.json";

    public static JSONObject Mainapraisal(String[] requestData) throws IOException
    {

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
