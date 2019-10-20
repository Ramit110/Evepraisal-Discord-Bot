package com.discord.bot.ramit.Commands;

import com.discord.bot.ramit.eveutil.EvepraisalAPI;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

public class EVEPraisal extends CommandBase implements ICommandExecution
{
    public EVEPraisal(String[] newMessage)
    {
        super(newMessage);
    }

    public String run()
    {
        String output = "";

        try
        {
            JSONObject request = EvepraisalAPI.Mainapraisal(Arrays.copyOfRange(message, 0, message.length));
            request = (JSONObject) request.get("appraisal");
            request = (JSONObject) request.get("totals");
            output = "```Volume is: " + request.get("volume") + "\nBuy value:" + request.get("buy") + "\nSell value:" + request.get("sell") + "```";
        }
        catch (IOException e)
        {
            output = "Error 001 - Error while getting data";
        }
        catch (JSONException e)
        {
            output = "Error 002 - Error parsing JSON.";
        }

        return output;
    }
}
