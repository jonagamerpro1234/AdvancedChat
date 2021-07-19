package jss.advancedchat.json.serializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import jss.advancedchat.json.handlers.ClickAction;
import jss.advancedchat.json.handlers.JsonClickEvent;

public class SerializerClickEvent implements Serializer<JsonClickEvent> {

	public JsonElement serialize(JsonClickEvent arg0, Type arg1, JsonSerializationContext arg2) {
		JsonObject json = new JsonObject();
		json.add("action", arg2.serialize(arg0.getAction()));
		json.addProperty("value", arg0.getValue());
		return json;
	}

	public JsonClickEvent deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		JsonObject json = (JsonObject) arg0;
		ClickAction action = (ClickAction) arg2.deserialize(json.get("action"), ClickAction.class);
		String value = json.get("value").getAsString();
		return new JsonClickEvent(action, value);
	}
}
