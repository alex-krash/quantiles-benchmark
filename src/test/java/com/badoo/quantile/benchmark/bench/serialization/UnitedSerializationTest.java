package com.badoo.quantile.benchmark.bench.serialization;

import com.badoo.quantile.benchmark.bench.Helper;

/**
 * Created by krash on 17.02.17.
 */
public class UnitedSerializationTest {

    public static void main(String... argv) throws Exception {
        Helper.run(AirliftSerializationTest.class, AlgebirdSerializationTest.class, NaiveSerializationTest.class, TDunningSerializationTest.class, HdrSerializationTest.class);
    }

}
