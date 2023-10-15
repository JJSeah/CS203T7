package com.example.payment.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class DateJsonSerializer<LocalDateTime> extends StdSerializer<LocalDateTime>{

	public DateJsonSerializer() {
		this(null, false);
	}
	protected DateJsonSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7035242849275956037L;

	@Override
	public void serialize(LocalDateTime value, 
			JsonGenerator gen, 
			SerializerProvider provider) throws IOException {
		gen.writeString(value.toString());		
	}

}
