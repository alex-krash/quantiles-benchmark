package com.badoo.quantile.benchmark.bench.serialization;

import com.badoo.quantile.benchmark.helper.NaiveTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 17.02.17.
 */
public class NaiveSerializationTest extends AbstractSerializationTest {
    @Override
    protected TestHelper createHelper() {
        return new NaiveTestHelper();
    }
}
