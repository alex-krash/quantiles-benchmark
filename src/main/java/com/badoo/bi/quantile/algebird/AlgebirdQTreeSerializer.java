package com.badoo.bi.quantile.algebird;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.algebird.QTree;

/**
 * Created by krash on 04.07.17.
 */
public class AlgebirdQTreeSerializer extends Serializer<QTree<Double>>
{
    @Override
    public void write(Kryo kryo, Output output, QTree<Double> object) {
        output.writeDouble(object.sum());
        output.writeLong(object.offset());
        output.writeInt(object.level());
        output.writeLong(object.count());
        kryo.writeObjectOrNull(output, object.lowerChildNullable(), this);
        kryo.writeObjectOrNull(output, object.upperChildNullable(), this);
    }

    @Override
    public QTree<Double> read(Kryo kryo, Input input, Class<QTree<Double>> type) {

        double sum = input.readDouble();
        long offset = input.readLong();
        int level = input.readInt();
        long count = input.readLong();
        QTree<Double> lower = kryo.readObjectOrNull(input, QTree.class, this);
        QTree<Double> upper = kryo.readObjectOrNull(input, QTree.class, this);

        return new QTree<>(sum, offset, level, count, lower, upper);
    }

    public static class AlgebirdAdapterSerializer extends Serializer<AlgebirdAdapter>
    {

        @Override
        public void write(Kryo kryo, Output output, AlgebirdAdapter object) {
            kryo.writeObject(output, object.source);
        }

        @Override
        public AlgebirdAdapter read(Kryo kryo, Input input, Class<AlgebirdAdapter> type) {
            return new AlgebirdAdapter(kryo.readObject(input, QTree.class));
        }
    }
}
