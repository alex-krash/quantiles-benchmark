package com.badoo.quantile.benchmark.bench.raw;

import com.badoo.quantile.benchmark.helper.HdrTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 04.07.17.
 */
public class HdrBenchTest extends AbstractBenchTest {
    @Override
    protected TestHelper createHelper() {
        return new HdrTestHelper();
    }
}
