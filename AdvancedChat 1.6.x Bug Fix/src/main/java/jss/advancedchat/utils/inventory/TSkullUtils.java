package jss.advancedchat.utils.inventory;

public class TSkullUtils {
    private static String colors = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY5ZTNlNmU1YjJiOTJmMGJlYjM2OGI3MzhiOTkzZDdiYTIyNWJmOWJiMjc1OGJmYzlmYzJkYWJhNGE1YTdkIn19fQ==";

    private static String white = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzY2YTVjOTg5MjhmYTVkNGI1ZDViOGVmYjQ5MDE1NWI0ZGRhMzk1NmJjYWE5MzcxMTc3ODE0NTMyY2ZjIn19fQ==";

    private static String gray = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjliYTdmZWY2YTFhOGJkODk5YWJhZTRhNWI1NGNiMGVjZTUzYmFkYzY3N2MxNjY4YmVlMGE0NjIxYTgifX19";

    private static String black = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTY3YTJmMjE4YTZlNmUzOGYyYjU0NWY2YzE3NzMzZjRlZjliYmIyODhlNzU0MDI5NDljMDUyMTg5ZWUifX19";

    private static String red = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWZkZTNiZmNlMmQ4Y2I3MjRkZTg1NTZlNWVjMjFiN2YxNWY1ODQ2ODRhYjc4NTIxNGFkZDE2NGJlNzYyNGIifX19";

    private static String blue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjhhZmExNTU1ZTlmODc2NDgxZTNjNDI5OWVjNmU5MWQyMmI0MDc1ZTY3ZTU4ZWY4MGRjZDE5MGFjZTY1MTlmIn19fQ==";

    private static String green = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGU5YjI3ZmNjZDgwOTIxYmQyNjNjOTFkYzUxMWQwOWU5YTc0NjU1NWU2YzljYWQ1MmU4NTYyZWQwMTgyYTJmIn19fQ==";

    private static String yellow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY0MTY4MmY0MzYwNmM1YzlhZDI2YmM3ZWE4YTMwZWU0NzU0N2M5ZGZkM2M2Y2RhNDllMWMxYTI4MTZjZjBiYSJ9fX0=";

    private static String gold = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTQzYzc5Y2Q5YzJkMzE4N2VhMDMyNDVmZTIxMjhlMGQyYWJiZTc5NDUyMTRiYzU4MzRkZmE0MDNjMTM0ZTI3In19fQ==";

    private static String aqua = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMDdjNzhmM2VlNzgzZmVlY2QyNjkyZWJhNTQ4NTFkYTVjNDMyMzA1NWViZDJmNjgzY2QzZTgzMDJmZWE3YyJ9fX0=";

    private static String light_purple = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmExMWE1MWY2NTc2Mjg1ZjlmOWM4YWE3ZGVkMWVlMTJjMjAyZjE1ZDc5MWM3MzJiNTg2ZGRhZTcwNjRiMCJ9fX0=";

    private static String dark_purple = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTMyYWUyY2I4ZDJhZTYxNTE0MWQyYzY1ODkyZjM2NGZjYWRkZjczZmRlYzk5YmUxYWE2ODc0ODYzZWViNWMifX19";

    private static String dark_aqua = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTViOWE0ODQ2N2YwMjEyYWE2ODg2NGU2MzQyMTE2ZjhmNzlhMjc1NDU0YmYyMTVmNjdmNzAxYTZmMmM4MTgifX19";

    private static String dark_green = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmM5ZTYwMWVkOTE5OGRiYjM0YzUxZGRmMzIzOTI5ZjAxYTVmOTU4YWIxMTEzM2UzZTA0MDdiNjk4MzkzYjNmIn19fQ==";

    private static String dark_blue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmE0NjA1MzAxMmM2OGYyODlhYmNmYjE3YWI4MDQyZDVhZmJhOTVkY2FhOTljOTljMWUwMzYwODg2ZDM1In19fQ==";

    private static String dark_red = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGY0ZGMzYzM3NTNiZjViMGI3ZjA4MWNkYjQ5YjgzZDM3NDI4YTEyZTQxODdmNjM0NmRlYzA2ZmFjNTRjZSJ9fX0=";

    private static String dark_gray = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmExN2U5NzAzN2NlMzUzZjg1ZjVjNjVkZjQzNWQyOTQ0OWE4OGRhNDQ0MmU0MzYxY2Y5OWFiYmUxZjg5MmZiIn19fQ==";

    private static String rainbow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY5ZTNlNmU1YjJiOTJmMGJlYjM2OGI3MzhiOTkzZDdiYTIyNWJmOWJiMjc1OGJmYzlmYzJkYWJhNGE1YTdkIn19fQ==";

    private static String next = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTg3YmFhNDc2NzIzNGMwMWMwNGI4YmJlYjUxOGEwNTNkY2U3MzlmNGEwNDM1OGE0MjQzMDJmYjRhMDE3MmY4In19fQ==";

    private static String last = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmVkOWQ1YzJiNDgwNzA1OGQ5ODdjNmUxZDYzMDBhMWNjNGI5ZWVlN2IxNmYxZjBhY2FjMTRmZmNkMWE5Njk5ZiJ9fX0=";

    private static String exit = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmViNTg4YjIxYTZmOThhZDFmZjRlMDg1YzU1MmRjYjA1MGVmYzljYWI0MjdmNDYwNDhmMThmYzgwMzQ3NWY3In19fQ==";

    private static String rainbowcube = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzgzZmQzZDExOTUzOWEyNDI1ZjdkYzczMzNkNDJmYWQ2OTRlNjJmNWY0Mzg4MjM1MjQ3MzE5ZDU5NjNkNTY3NyJ9fX0=";

    private static String choosecolor = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzIyNzY3MGQxNDg3OTQ5MTUzMDQ4MjdiMGViMDNlZmYyNzNjYTE1M2Y4NzRkYjVlOTA5NGQxY2RiYjYyNThhMiJ9fX0=";

    private static String missing = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2M2MDNjNzk1NjAzMTk5OTZkNjM5NDEyOGI0OWZlYzc2NTBjZjg2N2ExZTQ4ZmI4MGM2MDQzZTc3MGRkNzFiZCJ9fX0=";

    private static String mute_on = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzJmMDU4ZGRjMjk2OTEzMzI1OTFhYzU1YTBmZDczZjQzMjAxMTc5ODJjZmRiY2U3OTY5OTQxY2ZhOGVkOGM2YiJ9fX0=";

    private static String mute_off = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjAyYWYzY2EyZDVhMTYwY2ExMTE0MDQ4Yjc5NDc1OTQyNjlhZmUyYjFiNWVjMjU1ZWU3MmI2ODNiNjBiOTliOSJ9fX0=";

    public static String replace(String texture) {
        if (texture == null)
            return missing;
        String temp = texture;
        if (temp.equalsIgnoreCase("[colors]"))
            texture = colors;
        if (temp.equalsIgnoreCase("[white]"))
            texture = white;
        if (temp.equalsIgnoreCase("[gray]"))
            texture = gray;
        if (temp.equalsIgnoreCase("[black]"))
            texture = black;
        if (temp.equalsIgnoreCase("[red]"))
            texture = red;
        if (temp.equalsIgnoreCase("[blue]"))
            texture = blue;
        if (temp.equalsIgnoreCase("[green]"))
            texture = green;
        if (temp.equalsIgnoreCase("[yellow]"))
            texture = yellow;
        if (temp.equalsIgnoreCase("[gold]"))
            texture = gold;
        if (temp.equalsIgnoreCase("[aqua]"))
            texture = aqua;
        if (temp.equalsIgnoreCase("[light_purple]"))
            texture = light_purple;
        if (temp.equalsIgnoreCase("[dark_purple]"))
            texture = dark_purple;
        if (temp.equalsIgnoreCase("[dark_aqua]"))
            texture = dark_aqua;
        if (temp.equalsIgnoreCase("[dark_green]"))
            texture = dark_green;
        if (temp.equalsIgnoreCase("[dark_blue]"))
            texture = dark_blue;
        if (temp.equalsIgnoreCase("[dark_red]"))
            texture = dark_red;
        if (temp.equalsIgnoreCase("[dark_gray]"))
            texture = dark_gray;
        if (temp.equalsIgnoreCase("[rainbow]"))
            texture = rainbow;
        if (temp.equalsIgnoreCase("[last]"))
            texture = last;
        if (temp.equalsIgnoreCase("[next]"))
            texture = next;
        if (temp.equalsIgnoreCase("[exit]"))
            texture = exit;
        if (temp.equalsIgnoreCase("[choosecolor]"))
            texture = choosecolor;
        if (temp.equalsIgnoreCase("[rainbowcube]"))
            texture = rainbowcube;
        return texture;
    }

    public static String getColors() {
        return colors;
    }

    public static void setColors(String colors) {
        TSkullUtils.colors = colors;
    }

    public static String getWhite() {
        return white;
    }

    public static void setWhite(String white) {
        TSkullUtils.white = white;
    }

    public static String getGray() {
        return gray;
    }

    public static void setGray(String gray) {
        TSkullUtils.gray = gray;
    }

    public static String getBlack() {
        return black;
    }

    public static void setBlack(String black) {
        TSkullUtils.black = black;
    }

    public static String getRed() {
        return red;
    }

    public static void setRed(String red) {
        TSkullUtils.red = red;
    }

    public static String getBlue() {
        return blue;
    }

    public static void setBlue(String blue) {
        TSkullUtils.blue = blue;
    }

    public static String getGreen() {
        return green;
    }

    public static void setGreen(String green) {
        TSkullUtils.green = green;
    }

    public static String getYellow() {
        return yellow;
    }

    public static void setYellow(String yellow) {
        TSkullUtils.yellow = yellow;
    }

    public static String getGold() {
        return gold;
    }

    public static void setGold(String gold) {
        TSkullUtils.gold = gold;
    }

    public static String getAqua() {
        return aqua;
    }

    public static void setAqua(String aqua) {
        TSkullUtils.aqua = aqua;
    }

    public static String getLight_purple() {
        return light_purple;
    }

    public static void setLight_purple(String light_purple) {
        TSkullUtils.light_purple = light_purple;
    }

    public static String getDark_purple() {
        return dark_purple;
    }

    public static void setDark_purple(String dark_purple) {
        TSkullUtils.dark_purple = dark_purple;
    }

    public static String getDark_aqua() {
        return dark_aqua;
    }

    public static void setDark_aqua(String dark_aqua) {
        TSkullUtils.dark_aqua = dark_aqua;
    }

    public static String getDark_green() {
        return dark_green;
    }

    public static void setDark_green(String dark_green) {
        TSkullUtils.dark_green = dark_green;
    }

    public static String getDark_blue() {
        return dark_blue;
    }

    public static void setDark_blue(String dark_blue) {
        TSkullUtils.dark_blue = dark_blue;
    }

    public static String getDark_red() {
        return dark_red;
    }

    public static void setDark_red(String dark_red) {
        TSkullUtils.dark_red = dark_red;
    }

    public static String getDark_gray() {
        return dark_gray;
    }

    public static void setDark_gray(String dark_gray) {
        TSkullUtils.dark_gray = dark_gray;
    }

    public static String getRainbow() {
        return rainbow;
    }

    public static void setRainbow(String rainbow) {
        TSkullUtils.rainbow = rainbow;
    }

    public static String getNext() {
        return next;
    }

    public static void setNext(String next) {
        TSkullUtils.next = next;
    }

    public static String getLast() {
        return last;
    }

    public static void setLast(String last) {
        TSkullUtils.last = last;
    }

    public static String getExit() {
        return exit;
    }

    public static void setExit(String exit) {
        TSkullUtils.exit = exit;
    }

    public static String getMissing() {
        return missing;
    }

    public static void setMissing(String missing) {
        TSkullUtils.missing = missing;
    }

    public static String getMute_on() {
        return mute_on;
    }

    public static void setMute_on(String mute_on) {
        TSkullUtils.mute_on = mute_on;
    }

    public static String getMute_off() {
        return mute_off;
    }

    public static void setMute_off(String mute_off) {
        TSkullUtils.mute_off = mute_off;
    }

    public static String getRainbowcube() {
        return rainbowcube;
    }

    public static void setRainbowcube(String rainbowcube) {
        TSkullUtils.rainbowcube = rainbowcube;
    }

    public static String getChoosecolor() {
        return choosecolor;
    }

    public static void setChoosecolor(String choosecolor) {
        TSkullUtils.choosecolor = choosecolor;
    }
}
