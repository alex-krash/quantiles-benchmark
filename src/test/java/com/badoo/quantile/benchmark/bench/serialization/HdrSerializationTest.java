package com.badoo.quantile.benchmark.bench.serialization;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.hdr.HdrAdapter;
import com.badoo.quantile.benchmark.helper.HdrTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 04.07.17.
 */
public class HdrSerializationTest extends AbstractSerializationTest {
    @Override
    protected TestHelper createHelper() {
        return new HdrTestHelper();
    }

    @Override
    protected long estimateMemorySize(QuantileAdapter adapter) {
        return ((HdrAdapter)adapter).histogram.getEstimatedFootprintInBytes();
    }
}
