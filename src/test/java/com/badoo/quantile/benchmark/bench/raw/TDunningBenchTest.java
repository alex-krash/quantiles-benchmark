package com.badoo.quantile.benchmark.bench.raw;

import com.badoo.quantile.benchmark.bench.Helper;
import com.badoo.quantile.benchmark.helper.TDunningTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 17.02.17.
 */
public class TDunningBenchTest extends AbstractBenchTest {
    public static void main(String... argv) throws Exception {
        Helper.run(TDunningBenchTest.class);
    }

    @Override
    protected TestHelper createHelper() {
        return new TDunningTestHelper();
    }
}
