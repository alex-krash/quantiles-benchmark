package com.badoo.bi.quantile.naive;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Created by krash on 05.07.17.
 */
public class NaiveSerializer extends Serializer<NaiveAdapter> {
    @Override
    public void write(Kryo kryo, Output output, NaiveAdapter object) {
        output.writeInt(object.orderedSequence.length);
        output.writeDoubles(object.orderedSequence);
    }

    @Override
    public NaiveAdapter read(Kryo kryo, Input input, Class<NaiveAdapter> type) {
        return new NaiveAdapter(input.readDoubles(input.readInt()));
    }
}
