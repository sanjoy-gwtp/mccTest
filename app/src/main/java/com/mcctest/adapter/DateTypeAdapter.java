package com.mcctest.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

/**
 * Created by hasan on 5/28/17.
 */

public class DateTypeAdapter extends TypeAdapter<Date> {

    @Override
    public Date read(final JsonReader in) throws IOException {
        JsonToken check = in.peek();
        if(check == JsonToken.NULL) {
            in.skipValue();
            return null;
        } else {
            return new Date(in.nextLong());
        }
    }

    @Override
    @SuppressWarnings("resource")
    public void write(final JsonWriter out, final Date value) throws IOException {
        if(value!=null) {
            out.value(value.getTime());
        }
    }
}