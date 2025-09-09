package com.os.client.api.adapters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.os.client.model.FloatingRate;
import com.os.client.model.OneOfLoanLoanStatusReason;
import com.os.client.model.OneOfRerateStatusReason;
import com.os.client.model.Rate;
import com.os.client.model.RebateRate;
import com.os.client.model.Rerate;
import com.os.client.model.RerateDeclineErrorReason;
import com.os.client.model.RerateDeclineErrorReasonFieldType;
import com.os.client.model.RerateDeclineErrorReasonFieldValue;
import com.os.client.model.RerateDeclineErrorResponse;

public class RerateStatusDeserializerTest {

	private Rerate rerate;

	@Before
	public void setUp() {
		ObjectMapper objectMapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.registerModule(new SimpleModule().addDeserializer(Rate.class, new RateDeserializer()))
				.registerModule(new SimpleModule().addDeserializer(OneOfLoanLoanStatusReason.class, new LoanStatusDeserializer()))
				.registerModule(new SimpleModule().addDeserializer(OneOfRerateStatusReason.class, new RerateStatusDeserializer()))
				.registerModule(new SimpleModule().addDeserializer(LocalDate.class, new LocalDateJacksonDeserializer()))
				.registerModule(new SimpleModule().addDeserializer(OffsetDateTime.class, new OffsetDateTimeJacksonDeserializer()));
		
		File json = new File("src/test/resources/rerate.json");
		
		try {
			rerate = objectMapper.readValue(json, Rerate.class);
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void rerateStatusReason() {
		OneOfRerateStatusReason reason = rerate.getStatusReason();
		assertNotNull(reason);
	}

	@Test
	public void rerateStatusReasonDecline() {
		OneOfRerateStatusReason reason = rerate.getStatusReason();
		RerateDeclineErrorResponse error = (RerateDeclineErrorResponse) reason;
		assertEquals(error.getReason(), RerateDeclineErrorReason.INCORRECT_RERATE_INFO);
	}

	@Test
	public void rerateStatusReasonDeclineError() {
		OneOfRerateStatusReason reason = rerate.getStatusReason();
		RerateDeclineErrorResponse error = (RerateDeclineErrorResponse) reason;
		assertNotNull(error);
	}

	@Test
	public void rerateStatusReasonDeclineErrorType() {
		OneOfRerateStatusReason reason = rerate.getStatusReason();
		RerateDeclineErrorResponse errorResponse = (RerateDeclineErrorResponse) reason;
		RerateDeclineErrorReasonFieldValue error = errorResponse.getError();
		assertEquals(error.getField(), RerateDeclineErrorReasonFieldType.RERATE_VALUE);
	}

	@Test
	public void rerateStatusReasonDeclineErrorValue() {
		OneOfRerateStatusReason reason = rerate.getStatusReason();
		RerateDeclineErrorResponse errorResponse = (RerateDeclineErrorResponse) reason;
		RerateDeclineErrorReasonFieldValue error = errorResponse.getError();
		RebateRate rebateRate = (RebateRate)error.getExpectedValue();
		FloatingRate floatingRate = (FloatingRate)rebateRate.getRebate();
		assertEquals(floatingRate.getFloating().getSpread().doubleValue(), 136, 0);
	}

}
