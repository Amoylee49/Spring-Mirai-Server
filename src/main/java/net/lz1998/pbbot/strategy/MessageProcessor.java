package net.lz1998.pbbot.strategy;

import onebot.OnebotBase;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MessageProcessor {

    protected String command;

    public boolean isMatch(String message) {
        Matcher matcher = Pattern.compile(command, Pattern.CASE_INSENSITIVE)
                .matcher(message);
        return matcher.find();
    }

    public List<String> getCommandParameters(String message) {
        Matcher matcher = Pattern.compile(command, Pattern.CASE_INSENSITIVE)
                .matcher(message);
        String parameters = matcher.replaceFirst("").trim();
        return Arrays.asList(parameters.split(","));
    }

    public abstract List<OnebotBase.Message> process(String message);

}
