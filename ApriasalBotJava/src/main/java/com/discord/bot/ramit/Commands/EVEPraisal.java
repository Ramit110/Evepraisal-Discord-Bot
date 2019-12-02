package com.discord.bot.ramit.Commands;

import com.discord.bot.ramit.eveutil.EvepraisalAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

public class EVEPraisal extends CommandBase
{
    public EVEPraisal(String[] newMessage)
    {
        super(newMessage);
    }

    public String run()
    {
        String output = "";

        if(message.length == 0)
        {
            return "You may want to add some.... things to that request";
        }
        try
        {
            JSONObject requestMain = EvepraisalAPI.Mainapraisal(Arrays.copyOfRange(message, 0, message.length));

            requestMain = requestMain.getJSONObject("appraisal");

            JSONArray requestItems = requestMain.getJSONArray("items");
            requestMain = requestMain.getJSONObject("totals");

            for (Object objects : requestItems) {
                JSONObject items = (JSONObject)objects;
                output +=
                    "```" + items.get("name") +
                    "\nVolume:" + (items.getFloat("typeVolume") * items.getInt("quantity"))+
                    "\nSell Value:" + items.getJSONObject("prices").getJSONObject("sell").get("avg") +
                    "\nBuy Value:" + items.getJSONObject("prices").getJSONObject("buy").get("avg") + "```";
            }
            output +=
                    "```Total Volume is: " + requestMain.get("volume") +
                    "\nBuy value:" + requestMain.get("buy") +
                    "\nSell value:" + requestMain.get("sell") + "```";
        }
        catch (IOException e)
        {
            output += "Error 001 - Error while getting data";
        }
        catch (JSONException e)
        {
            output += "Error 002 - Error parsing JSON";
        }

        return output;
    }
}
