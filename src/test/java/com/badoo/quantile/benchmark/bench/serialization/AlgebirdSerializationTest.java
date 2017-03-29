package com.badoo.quantile.benchmark.bench.serialization;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.algebird.AlgebirdAdapter;
import com.badoo.quantile.benchmark.helper.AlgebirdTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 17.02.17.
 */
public class AlgebirdSerializationTest extends AbstractSerializationTest {
    @Override
    protected TestHelper createHelper() {
        return new AlgebirdTestHelper();
    }
}
