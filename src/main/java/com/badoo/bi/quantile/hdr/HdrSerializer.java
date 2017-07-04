package com.badoo.bi.quantile.hdr;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.HdrHistogram.DoubleHistogram;

import java.nio.ByteBuffer;

/**
 * Created by krash on 04.07.17.
 */
public class HdrSerializer extends Serializer<HdrAdapter> {

    @Override
    public void write(Kryo kryo, Output output, HdrAdapter object) {
        ByteBuffer buffer = ByteBuffer.allocate(64 * 1024);
        int size = object.histogram.encodeIntoByteBuffer(buffer);
        output.writeInt(size);
        output.write(buffer.array(), 0, size);
    }

    @Override
    public HdrAdapter read(Kryo kryo, Input input, Class<HdrAdapter> type) {
        int size = input.readInt();
        byte[] bytes = input.readBytes(size);
        DoubleHistogram histogram = DoubleHistogram.decodeFromByteBuffer(ByteBuffer.wrap(bytes), 0);
        return new HdrAdapter(histogram);
    }
}
