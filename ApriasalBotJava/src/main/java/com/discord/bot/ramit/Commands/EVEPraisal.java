package com.discord.bot.ramit.Commands;

import com.discord.bot.ramit.eveutil.Misc.EvepraisalAPI;
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
            JSONObject requestMain = EvepraisalAPI.Mainapraisal(Arrays.copyOfRange(message, 0, message.length), parameters);

            requestMain = requestMain.getJSONObject("appraisal");

            output += "Link: https://evepraisal.com/a/" + requestMain.get("id") + "\n";

            output += objectParse(requestMain.getJSONArray("items"));

            JSONObject totalValues = requestMain.getJSONObject("totals");
            output +=
                    "```Total Volume is: " + totalValues.get("volume") +
                    "\nBuy value:" + totalValues.get("buy") +
                    "\nSell value:" + totalValues.get("sell") + "```";
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

    protected String objectParse(JSONArray inputArr)
    {
        String tbr = "";
        for (Object objects : inputArr) {
            JSONObject items = (JSONObject)objects;
            tbr +=
                    "```" + items.get("name") +
                            "\nVolume: " + items.get("typeVolume") +
                            "\nQuantity: "+ items.get("quantity") +
                            "\nTotal Volume: "+ (items.getInt("quantity") * items.getInt("quantity")) + "```";
        }
        return tbr;
    }
}
