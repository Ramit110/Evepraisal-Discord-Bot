package com.discord.bot.ramit.eveutil.Misc;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class YAMLParser {
    private static Map<String, String> blueprintInfo;

    public static Map<String, String> getBlueprintInfo()
    {
        return blueprintInfo;
    }

    public static void loadYAML() {
        try
        {
            Files.readAllBytes(Paths.get(""));
        }
        catch (IOException e)
        {

        }
    }
}
