package com.badoo.quantile.benchmark.bench.mapreduce;

import com.badoo.quantile.benchmark.helper.AirliftTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 17.02.17.
 */
public class AirliftMapReduceTest extends AbstractMapReduceTest {
    @Override
    protected TestHelper createHelper() {
        return new AirliftTestHelper();
    }
}
