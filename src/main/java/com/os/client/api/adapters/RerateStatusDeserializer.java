package com.os.client.api.adapters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.os.client.model.OneOfRerateRerateStatusReason;
import com.os.client.model.RerateCancelErrorReason;
import com.os.client.model.RerateCancelErrorResponse;
import com.os.client.model.RerateDeclineErrorReason;
import com.os.client.model.RerateDeclineErrorReasonFieldType;
import com.os.client.model.RerateDeclineErrorReasonFieldValue;
import com.os.client.model.RerateDeclineErrorResponse;

public class RerateStatusDeserializer extends StdDeserializer<OneOfRerateRerateStatusReason> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3709103313504551006L;

	public RerateStatusDeserializer() {
		this(null);
	}

	protected RerateStatusDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public OneOfRerateRerateStatusReason deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		OneOfRerateRerateStatusReason impl = null;

		JsonNode nodeLoanStatusReason = p.readValueAsTree();

		JsonNode nodeReason = nodeLoanStatusReason.get("reason");
		if (nodeReason != null) {

			if (RerateDeclineErrorReason.fromValue(nodeReason.textValue()) != null) {

				RerateDeclineErrorResponse rerateDeclineErrorResponse = new RerateDeclineErrorResponse();
				rerateDeclineErrorResponse.setReason(RerateDeclineErrorReason.fromValue(nodeReason.textValue()));

				JsonNode errors = nodeLoanStatusReason.get("errors");
				if (errors != null && errors.isArray()) {

					List<RerateDeclineErrorReasonFieldValue> responseErrors = new ArrayList<>();

					for (JsonNode error : errors) {
						JsonNode nodeField = error.get("field");
						if (nodeField != null) {

							String field = nodeField.textValue();
							
							JsonNode nodeValue = error.get("expectedValue");

							if (RerateDeclineErrorReasonFieldType.fromValue(field) != null) {

								RerateDeclineErrorReasonFieldValue rerateDeclineErrorReasonFieldValue = new RerateDeclineErrorReasonFieldValue();
								rerateDeclineErrorReasonFieldValue.setField(RerateDeclineErrorReasonFieldType.fromValue(field));
								if (nodeValue != null) {
									RateDeserializer deserializer = new RateDeserializer();
									rerateDeclineErrorReasonFieldValue.setExpectedValue(deserializer.deserializeRerateRate(nodeValue));
								}
								responseErrors.add(rerateDeclineErrorReasonFieldValue);
								
							}
						}
					}
					
					rerateDeclineErrorResponse.setErrors(responseErrors);
				}
				
				impl = rerateDeclineErrorResponse;

			} else if (RerateCancelErrorReason.fromValue(nodeReason.textValue()) != null) {

				RerateCancelErrorResponse rerateCancelErrorResponse = new RerateCancelErrorResponse();
				rerateCancelErrorResponse.setReason(RerateCancelErrorReason.fromValue(nodeReason.textValue()));

				impl = rerateCancelErrorResponse;

			}
		}

		return impl;
	}
}
