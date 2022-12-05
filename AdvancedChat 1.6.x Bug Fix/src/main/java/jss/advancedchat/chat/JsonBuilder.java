package jss.advancedchat.chat;

import java.util.List;

public class JsonBuilder {
    private final StringBuilder json = new StringBuilder();

    public JsonBuilder start() {
        add("[\"\",");
        return this;
    }

    public JsonBuilder addText(String str) {
        add("{\"text\":\"" + str + "\"");
        return this;
    }

    public JsonBuilder addExtraText(String str) {
        add(",{\"text\":\"" + str + "\"");
        return this;
    }

    public JsonBuilder addHover(List<String> str) {
        for (int i = 0; i < str.size(); i++) {
            if (i == str.size() - 1) {
                add("{\"text\":\"" + str + "\"");
            } else {
                add("{\"text\":\"" + str + "\",\n");
            }
        }
        return this;
    }

    public JsonBuilder addCommand(String str) {
        add(",\"clickEvent\":{\"RUN_COMMAND\",\"value\":\"" + str + "\"}");
        return this;
    }

    public JsonBuilder addSuggestCommand(String str) {
        add(",\"clickEvent\":{\"SUGGEST_COMMAND\",\"value\":\"" + str + "\"}");
        return this;
    }

    public JsonBuilder addUrl(String str) {
        add(",\"clickEvent\":{\"OPEN_URL\",\"value\":\"" + str + "\"}");
        return this;
    }

    public String getRawText() {
        return this.json.toString();
    }

    public JsonBuilder and() {
        add("}");
        return this;
    }

    public JsonBuilder create() {
        add("}]");
        return this;
    }

    private void add(Object obj) {
        this.json.append(obj);
    }
}
