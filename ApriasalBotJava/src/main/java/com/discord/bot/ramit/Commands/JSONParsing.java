package com.discord.bot.ramit.Commands;

import org.json.JSONArray;
import org.json.JSONObject;


public abstract class JSONParsing extends CommandBase
{
    public JSONParsing(String[] newMessage)
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
