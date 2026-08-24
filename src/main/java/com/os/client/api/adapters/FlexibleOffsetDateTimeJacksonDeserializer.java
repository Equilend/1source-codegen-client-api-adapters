package com.os.client.api.adapters;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Accepts ISO-8601 timestamps with variable fractional-second precision.
 * Offset-less values retain the client's historical behavior of assuming UTC.
 */
public class FlexibleOffsetDateTimeJacksonDeserializer extends JsonDeserializer<OffsetDateTime> {

	@Override
	public OffsetDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
		String value = parser.getText();
		if (value == null || value.isBlank()) {
			return null;
		}
		try {
			return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		} catch (DateTimeParseException noOffset) {
			try {
				return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME).atOffset(ZoneOffset.UTC);
			} catch (DateTimeParseException invalid) {
				throw context.weirdStringException(value, OffsetDateTime.class,
						"Expected an ISO-8601 date-time with optional offset");
			}
		}
	}
}
