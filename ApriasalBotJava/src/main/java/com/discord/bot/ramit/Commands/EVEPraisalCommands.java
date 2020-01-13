package com.discord.bot.ramit.Commands;

import org.json.JSONArray;
import org.json.JSONObject;


public abstract class EVEPraisalCommands extends CommandBase
{
    public EVEPraisalCommands(String[] newMessage)
    {
        super(newMessage);
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
