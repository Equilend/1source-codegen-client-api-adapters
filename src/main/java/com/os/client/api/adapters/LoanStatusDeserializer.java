package com.os.client.api.adapters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.os.client.model.AnyOfLoanDeclineErrorResponseErrorsItems;
import com.os.client.model.CcpIndicator;
import com.os.client.model.CurrencyCd;
import com.os.client.model.LoanCancelErrorReason;
import com.os.client.model.LoanCancelErrorResponse;
import com.os.client.model.LoanDeclineErrorReason;
import com.os.client.model.LoanDeclineErrorReasonFieldBillingCurrency;
import com.os.client.model.LoanDeclineErrorReasonFieldCcpIndicator;
import com.os.client.model.LoanDeclineErrorReasonFieldCollateralCurrency;
import com.os.client.model.LoanDeclineErrorReasonFieldCollateralMargin;
import com.os.client.model.LoanDeclineErrorReasonFieldDividendRate;
import com.os.client.model.LoanDeclineErrorReasonFieldResetDate;
import com.os.client.model.LoanDeclineErrorReasonFieldSettlement;
import com.os.client.model.LoanDeclineErrorReasonFieldSettlementType;
import com.os.client.model.LoanDeclineErrorReasonFieldTermDate;
import com.os.client.model.LoanDeclineErrorReasonFieldTermType;
import com.os.client.model.LoanDeclineErrorResponse;
import com.os.client.model.OneOfLoanLoanStatusReason;
import com.os.client.model.PartySettlementInstruction;
import com.os.client.model.SettlementType;
import com.os.client.model.TermType;

public class LoanStatusDeserializer extends StdDeserializer<OneOfLoanLoanStatusReason> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3709103313504551007L;

	public LoanStatusDeserializer() {
		this(null);
	}

	protected LoanStatusDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public OneOfLoanLoanStatusReason deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		OneOfLoanLoanStatusReason impl = null;

		JsonNode nodeLoanStatusReason = p.readValueAsTree();

		JsonNode nodeReason = nodeLoanStatusReason.get("reason");
		if (nodeReason != null) {

			if (LoanDeclineErrorReason.fromValue(nodeReason.textValue()) != null) {

				LoanDeclineErrorResponse loanDeclineErrorResponse = new LoanDeclineErrorResponse();
				loanDeclineErrorResponse.setReason(LoanDeclineErrorReason.fromValue(nodeReason.textValue()));

				JsonNode errors = nodeLoanStatusReason.get("errors");
				if (errors != null && errors.isArray()) {
					
					List<AnyOfLoanDeclineErrorResponseErrorsItems> responseErrors = new ArrayList<>();
					
					for (JsonNode error : errors) {
						JsonNode nodeField = error.get("field");
						if (nodeField != null) {

							String field = nodeField.textValue();
							
							JsonNode nodeValue = error.get("expectedValue");

							if (LoanDeclineErrorReasonFieldBillingCurrency.FieldEnum.fromValue(field) != null) {
								LoanDeclineErrorReasonFieldBillingCurrency loanDeclineErrorReasonFieldBillingCurrency = new LoanDeclineErrorReasonFieldBillingCurrency();
								loanDeclineErrorReasonFieldBillingCurrency.setField(LoanDeclineErrorReasonFieldBillingCurrency.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldBillingCurrency.setExpectedValue(CurrencyCd.fromValue(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldBillingCurrency);
								
							} else if (LoanDeclineErrorReasonFieldCcpIndicator.FieldEnum
									.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldCcpIndicator loanDeclineErrorReasonFieldCcpIndicator = new LoanDeclineErrorReasonFieldCcpIndicator();
								loanDeclineErrorReasonFieldCcpIndicator.setField(LoanDeclineErrorReasonFieldCcpIndicator.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldCcpIndicator.setExpectedValue(CcpIndicator.fromValue(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldCcpIndicator);

							} else if (LoanDeclineErrorReasonFieldCollateralCurrency.FieldEnum
									.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldCollateralCurrency loanDeclineErrorReasonFieldCollateralCurrency = new LoanDeclineErrorReasonFieldCollateralCurrency();
								loanDeclineErrorReasonFieldCollateralCurrency.setField(LoanDeclineErrorReasonFieldCollateralCurrency.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldCollateralCurrency.setExpectedValue(CurrencyCd.fromValue(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldCollateralCurrency);

							} else if (LoanDeclineErrorReasonFieldCollateralMargin.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldCollateralMargin loanDeclineErrorReasonFieldCollateralMargin = new LoanDeclineErrorReasonFieldCollateralMargin();
								loanDeclineErrorReasonFieldCollateralMargin.setField(LoanDeclineErrorReasonFieldCollateralMargin.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldCollateralMargin.setExpectedValue(nodeValue.doubleValue());
								}
								responseErrors.add(loanDeclineErrorReasonFieldCollateralMargin);

							} else if (LoanDeclineErrorReasonFieldDividendRate.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldDividendRate loanDeclineErrorReasonFieldDividendRate = new LoanDeclineErrorReasonFieldDividendRate();
								loanDeclineErrorReasonFieldDividendRate.setField(LoanDeclineErrorReasonFieldDividendRate.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldDividendRate.setExpectedValue(nodeValue.doubleValue());
								}
								responseErrors.add(loanDeclineErrorReasonFieldDividendRate);

							} else if (LoanDeclineErrorReasonFieldResetDate.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldResetDate loanDeclineErrorReasonFieldResetDate = new LoanDeclineErrorReasonFieldResetDate();
								loanDeclineErrorReasonFieldResetDate.setField(LoanDeclineErrorReasonFieldResetDate.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									LocalDateJacksonDeserializer dateDeserializer = new LocalDateJacksonDeserializer();
									loanDeclineErrorReasonFieldResetDate.setExpectedValue(dateDeserializer.deserialize(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldResetDate);

							} else if (LoanDeclineErrorReasonFieldSettlement.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldSettlement loanDeclineErrorReasonFieldSettlement = new LoanDeclineErrorReasonFieldSettlement();
								loanDeclineErrorReasonFieldSettlement.setField(LoanDeclineErrorReasonFieldSettlement.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldSettlement.setExpectedValue((new ObjectMapper()).convertValue(nodeValue, PartySettlementInstruction.class));
								}
								responseErrors.add(loanDeclineErrorReasonFieldSettlement);

							} else if (LoanDeclineErrorReasonFieldSettlementType.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldSettlementType loanDeclineErrorReasonFieldSettlementType = new LoanDeclineErrorReasonFieldSettlementType();
								loanDeclineErrorReasonFieldSettlementType.setField(LoanDeclineErrorReasonFieldSettlementType.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldSettlementType.setExpectedValue(SettlementType.fromValue(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldSettlementType);

							} else if (LoanDeclineErrorReasonFieldTermDate.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldTermDate loanDeclineErrorReasonFieldTermDate = new LoanDeclineErrorReasonFieldTermDate();
								loanDeclineErrorReasonFieldTermDate.setField(LoanDeclineErrorReasonFieldTermDate.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									LocalDateJacksonDeserializer dateDeserializer = new LocalDateJacksonDeserializer();
									loanDeclineErrorReasonFieldTermDate.setExpectedValue(dateDeserializer.deserialize(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldTermDate);

							} else if (LoanDeclineErrorReasonFieldTermType.FieldEnum.fromValue(field) != null) {

								LoanDeclineErrorReasonFieldTermType loanDeclineErrorReasonFieldTermType = new LoanDeclineErrorReasonFieldTermType();
								loanDeclineErrorReasonFieldTermType.setField(LoanDeclineErrorReasonFieldTermType.FieldEnum.fromValue(field));
								if (nodeValue != null) {
									loanDeclineErrorReasonFieldTermType.setExpectedValue(TermType.fromValue(nodeValue.textValue()));
								}
								responseErrors.add(loanDeclineErrorReasonFieldTermType);

							}
						}
					}
					
					loanDeclineErrorResponse.setErrors(responseErrors);
				}
				
				impl = loanDeclineErrorResponse;

			} else if (LoanCancelErrorReason.fromValue(nodeReason.textValue()) != null) {

				LoanCancelErrorResponse loanCancelErrorResponse = new LoanCancelErrorResponse();
				loanCancelErrorResponse.setReason(LoanCancelErrorReason.fromValue(nodeReason.textValue()));

				impl = loanCancelErrorResponse;

			}
		}

		return impl;
	}
}
