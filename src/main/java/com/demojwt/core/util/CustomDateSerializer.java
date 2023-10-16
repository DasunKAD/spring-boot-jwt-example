package com.demojwt.core.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class CustomDateSerializer extends StdSerializer<Date> {
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public CustomDateSerializer() {
        this(null);
    }

    protected CustomDateSerializer(Class<Date> t) {
        super(t);
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (value == null) {
            gen.writeString("");
        }
        formatter.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        gen.writeString(formatter.format(value));
    }
}
