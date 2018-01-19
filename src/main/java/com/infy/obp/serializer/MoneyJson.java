package com.infy.obp.serializer;

import java.io.IOException;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

public class MoneyJson {
    public static class MoneyDeserializer extends JsonObjectDeserializer<Money> {
        @Override
        protected Money deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
            return Money.of(CurrencyUnit.of(tree.get("currency").asText()), Double.parseDouble(tree.get("amount").textValue()));
        }
    }
}