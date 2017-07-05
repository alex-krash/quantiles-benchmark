package com.badoo.quantile.benchmark.bench.mapreduce;

import com.badoo.quantile.benchmark.bench.Helper;

/**
 * Created by krash on 17.02.17.
 */
public class UnitedMapReduceTest {

    public static void main(String... argv) throws Exception {
        Helper.run(NaiveMapReduceTest.class, HdrMapReduceTest.class, CombinedMapReduceTest.class);
    }

}
