package com.os.client.api.adapters;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.os.client.model.PriceBasis;

/**
 * Maps the API's numeric price-basis value through the generated enum value,
 * rather than allowing Jackson to interpret it as an enum ordinal.
 */
public class PriceBasisJacksonDeserializer extends JsonDeserializer<PriceBasis> {

	@Override
	public PriceBasis deserialize(JsonParser parser, DeserializationContext context) throws IOException {
		BigDecimal value = parser.getDecimalValue();
		PriceBasis basis = PriceBasis.fromValue(value);
		if (basis == null) {
			throw context.weirdNumberException(value, PriceBasis.class, "Unsupported price basis");
		}
		return basis;
	}
}
