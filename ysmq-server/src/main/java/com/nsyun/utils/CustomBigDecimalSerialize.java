package com.nsyun.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomBigDecimalSerialize extends JsonSerializer<BigDecimal> {  
  
    private DecimalFormat df = new DecimalFormat("###0.00");  
  
    @Override  
    public void serialize(BigDecimal value, JsonGenerator jgen,  
            SerializerProvider provider) throws IOException,  
            JsonProcessingException {  
  
        jgen.writeString(df.format(value));  
    }  
}  

