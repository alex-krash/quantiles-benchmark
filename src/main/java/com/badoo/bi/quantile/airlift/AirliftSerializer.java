package com.badoo.bi.quantile.airlift;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.KryoDataInput;
import com.esotericsoftware.kryo.io.KryoDataOutput;
import com.esotericsoftware.kryo.io.Output;
import com.facebook.presto.jdbc.internal.airlift.stats.QuantileDigest;

/**
 * Created by krash on 17.02.17.
 */
public class AirliftSerializer extends Serializer<AirliftAdapter> {
    @Override
    public void write(Kryo kryo, Output output, AirliftAdapter object) {
        object.digest.serialize(new KryoDataOutput(output));
    }

    @Override
    public AirliftAdapter read(Kryo kryo, Input input, Class<AirliftAdapter> type) {

        return new AirliftAdapter(QuantileDigest.deserialize(new KryoDataInput(input)));
    }

}
