package com.infy.obp.vo;

import org.joda.money.Money;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.infy.obp.serializer.MoneyJson;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    private String id;
    private String label;
    @JsonProperty("bank_id")
    private String bankId;
    private String error;

   @JsonDeserialize(using = MoneyJson.MoneyDeserializer.class)
    private Money balance;

    private String type;

    @JsonProperty("IBAN")
    private String iban;

    @JsonProperty("swist_bic")
    private String bic;

}
