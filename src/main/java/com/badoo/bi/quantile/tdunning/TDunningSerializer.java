package com.badoo.bi.quantile.tdunning;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.tdunning.math.stats.AVLTreeDigest;

import java.nio.ByteBuffer;

/**
 * Created by krash on 04.07.17.
 */
public class TDunningSerializer extends Serializer<TDunningAdapter> {
    @Override
    public void write(Kryo kryo, Output output, TDunningAdapter object) {
        ByteBuffer buffer = ByteBuffer.allocate(object.digest.byteSize());
        object.digest.asBytes(buffer);
        int position = buffer.position();
        output.writeInt(position);
        output.writeBytes(buffer.array(), 0, position);
    }

    @Override
    public TDunningAdapter read(Kryo kryo, Input input, Class<TDunningAdapter> type) {
        int size = input.readInt();
        byte[] bytes = input.readBytes(size);

        return new TDunningAdapter(AVLTreeDigest.fromBytes(ByteBuffer.wrap(bytes)));
    }
}
