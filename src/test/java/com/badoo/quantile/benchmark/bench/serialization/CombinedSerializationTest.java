package com.badoo.quantile.benchmark.bench.serialization;

import com.badoo.quantile.benchmark.helper.CombinedTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 05.07.17.
 */
public class CombinedSerializationTest extends AbstractSerializationTest {
    @Override
    protected TestHelper createHelper() {
        return new CombinedTestHelper();
    }
}
