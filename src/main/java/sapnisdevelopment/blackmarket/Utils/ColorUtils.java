package sapnisdevelopment.blackmarket.Utils;

import net.md_5.bungee.api.ChatColor;

import java.util.List;

public class ColorUtils
{
    public static List<String> color(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            input.set(i, color(input.get(i)));
        }

        return input;
    }

    public static String[] color(String[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = color(input[i]);
        }

        return input;
    }

    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}