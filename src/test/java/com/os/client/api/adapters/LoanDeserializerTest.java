package com.os.client.api.adapters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.os.client.model.Loan;
import com.os.client.model.LoanDeclineErrorReason;
import com.os.client.model.LoanDeclineErrorResponse;
import com.os.client.model.OneOfLoanLoanStatusReason;
import com.os.client.model.Rate;
import com.os.client.model.AnyOfLoanDeclineErrorResponseErrorsItems;

public class LoanDeserializerTest {

	private Loan loan;

	@Before
	public void setUp() {
		ObjectMapper objectMapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.registerModule(new SimpleModule().addDeserializer(Rate.class, new RateDeserializer()))
				.registerModule(new SimpleModule().addDeserializer(OneOfLoanLoanStatusReason.class, new LoanStatusDeserializer()))
				.registerModule(new SimpleModule().addDeserializer(LocalDate.class, new LocalDateJacksonDeserializer()))
				.registerModule(new SimpleModule().addDeserializer(OffsetDateTime.class, new OffsetDateTimeJacksonDeserializer()));
		
		File json = new File("src/test/resources/loan_with_decline_reason.json");
		
		try {
			loan = objectMapper.readValue(json, Loan.class);
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void loanStatusReasonDeclineError() {
		OneOfLoanLoanStatusReason reason = loan.getLoanStatusReason();
		LoanDeclineErrorResponse error = (LoanDeclineErrorResponse) reason;
		assertNotNull(error);
	}

	@Test
	public void loanStatusReason() {
		OneOfLoanLoanStatusReason reason = loan.getLoanStatusReason();
		assertNotNull(reason);
	}

	@Test
	public void loanStatusReasonDecline() {
		OneOfLoanLoanStatusReason reason = loan.getLoanStatusReason();
		LoanDeclineErrorResponse error = (LoanDeclineErrorResponse) reason;
		assertEquals(error.getReason(), LoanDeclineErrorReason.INCORRECT_LOAN_INFO);
	}

	@Test
	public void loanStatusReasonDeclineErrors() {
		OneOfLoanLoanStatusReason reason = loan.getLoanStatusReason();
		LoanDeclineErrorResponse error = (LoanDeclineErrorResponse) reason;
		List<AnyOfLoanDeclineErrorResponseErrorsItems> errors = error.getErrors();
		assertTrue(errors.size() > 0);
	}

}
