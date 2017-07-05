package com.badoo.bi.quantile.combined;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.HdrHistogram.DoubleHistogram;

import java.nio.ByteBuffer;

/**
 * Created by krash on 05.07.17.
 */
public class CombinedSerializer extends Serializer<CombinedAdapter> {
    @Override
    public void write(Kryo kryo, Output output, CombinedAdapter object) {
        boolean isRaw = null != object.rawData;
        output.writeBoolean(isRaw);
        if (isRaw) {
            output.writeInt(object.rawData.length);
            output.writeDoubles(object.rawData);
        } else {
            ByteBuffer buffer = ByteBuffer.allocate(64 * 1024);
            int size = object.histogram.encodeIntoByteBuffer(buffer);
            output.writeInt(size);
            output.write(buffer.array(), 0, size);
        }
    }

    @Override
    public CombinedAdapter read(Kryo kryo, Input input, Class<CombinedAdapter> type) {

        boolean isRaw = input.readBoolean();
        if (isRaw) {
            return new CombinedAdapter(input.readDoubles(input.readInt()));
        }

        int size = input.readInt();
        byte[] bytes = input.readBytes(size);
        DoubleHistogram histogram = DoubleHistogram.decodeFromByteBuffer(ByteBuffer.wrap(bytes), 0);

        return new CombinedAdapter(histogram);
    }
}
