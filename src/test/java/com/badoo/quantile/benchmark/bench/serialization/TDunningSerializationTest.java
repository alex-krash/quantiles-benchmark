package com.badoo.quantile.benchmark.bench.serialization;

import com.badoo.quantile.benchmark.helper.TDunningTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 17.02.17.
 */
public class TDunningSerializationTest extends AbstractSerializationTest {
    @Override
    protected TestHelper createHelper() {
        return new TDunningTestHelper();
    }
}
