package jss.advancedchat.old.json.serializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import jss.advancedchat.old.json.handlers.HoverAction;
import jss.advancedchat.old.json.handlers.JsonHoverEvent;

public class SerializerHoverEvent implements Serializer<JsonHoverEvent> {

	public JsonElement serialize(JsonHoverEvent arg0, Type arg1, JsonSerializationContext arg2) {
		JsonObject json = new JsonObject();
		json.add("action", arg2.serialize(arg0.getAction()));
		json.addProperty("value", arg0.getValue());
		return json;
	}

	public JsonHoverEvent deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		JsonObject json = (JsonObject) arg0;
		HoverAction action = (HoverAction) arg2.deserialize(json.get("action"), HoverAction.class);
		String value = json.get("value").getAsString();
		return new JsonHoverEvent(action, value.split("\n"));
	}
}
