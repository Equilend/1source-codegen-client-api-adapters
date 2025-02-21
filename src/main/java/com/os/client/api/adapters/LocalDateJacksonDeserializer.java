package com.os.client.api.adapters;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateJacksonDeserializer extends JsonDeserializer<LocalDate> {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE;

	@Override
	public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException {
		return deserialize(jsonParser.getText());
	}

	public LocalDate deserialize(String dateAsString) throws IOException {

		if (dateAsString == null) {
			throw new IOException("dateAsString argument is null.");
		}

		LocalDate date = LocalDate.parse(dateAsString, DATE_TIME_FORMATTER);

		return date;

	}
}