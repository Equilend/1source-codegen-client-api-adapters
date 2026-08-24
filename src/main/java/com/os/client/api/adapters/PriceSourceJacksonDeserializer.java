package com.os.client.api.adapters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.os.client.model.PriceSource;

public class PriceSourceJacksonDeserializer extends JsonDeserializer<PriceSource> {

	@Override
	public PriceSource deserialize(JsonParser parser, DeserializationContext context) throws IOException {
		String value = parser.getText();
		PriceSource source = PriceSource.fromValue(value);
		if (source == null) {
			throw context.weirdStringException(value, PriceSource.class, "Unsupported price source");
		}
		return source;
	}
}
