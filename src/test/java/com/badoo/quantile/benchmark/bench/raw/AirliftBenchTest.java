package com.badoo.quantile.benchmark.bench.raw;

import com.badoo.quantile.benchmark.bench.Helper;
import com.badoo.quantile.benchmark.helper.AirliftTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 15.02.17.
 */
public class AirliftBenchTest extends AbstractBenchTest {

    public static void main(String... argv) throws Exception {
        Helper.run(AirliftBenchTest.class);
    }

    @Override
    protected TestHelper createHelper() {
        return new AirliftTestHelper();
    }
}
