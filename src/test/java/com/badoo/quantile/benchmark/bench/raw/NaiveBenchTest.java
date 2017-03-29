package com.badoo.quantile.benchmark.bench.raw;

import com.badoo.quantile.benchmark.bench.Helper;
import com.badoo.quantile.benchmark.helper.NaiveTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 17.02.17.
 */
public class NaiveBenchTest extends AbstractBenchTest {
    @Override
    protected TestHelper createHelper() {
        return new NaiveTestHelper();
    }

    public static void main(String... argv) throws Exception {
        Helper.run(NaiveBenchTest.class);
    }
}
