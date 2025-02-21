package com.os.client.api.adapters;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class OffsetDateTimeTypeGsonAdapter implements JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

	@Override
	public JsonElement serialize(final OffsetDateTime date, final Type typeOfSrc, final JsonSerializationContext context) {
		return new JsonPrimitive(date.format(DATE_TIME_FORMATTER));
	}

	@Override
	public OffsetDateTime deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
			throws JsonParseException {
		
		String dateAsString = json.getAsString();
		
		if (dateAsString == null) {
			throw new JsonParseException("OffsetDateTime argument is null.");
		}
		
		int milliSize = dateAsString.substring(dateAsString.indexOf(".")+1).length();
		
		for (int i = milliSize; i<3; i++) {
			dateAsString = dateAsString + "0";
		}
		
		LocalDateTime dateTime = LocalDateTime.parse(dateAsString, DATE_TIME_FORMATTER);
		
		return OffsetDateTime.of(dateTime, ZoneOffset.UTC);

	}
}