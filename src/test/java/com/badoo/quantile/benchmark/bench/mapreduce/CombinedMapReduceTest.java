package com.badoo.quantile.benchmark.bench.mapreduce;

import com.badoo.quantile.benchmark.bench.Helper;

/**
 * Created by krash on 17.02.17.
 */
public class CombinedMapReduceTest {

    public static void main(String... argv) throws Exception {
        Helper.run(AirliftMapReduceTest.class, AlgebirdMapReduceTest.class, NaiveMapReduceTest.class, TDunningMapReduceTest.class);
    }

}
