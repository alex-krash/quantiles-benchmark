package com.badoo.quantile.benchmark.bench.raw;

import com.badoo.quantile.benchmark.bench.Helper;
import com.badoo.quantile.benchmark.helper.AlgebirdTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 15.02.17.
 */
public class AlgebirdBenchTest extends AbstractBenchTest {

    public static void main(String... argv) throws Exception {
        Helper.run(AlgebirdBenchTest.class);
    }

    @Override
    protected TestHelper createHelper() {
        return new AlgebirdTestHelper();
    }
}
