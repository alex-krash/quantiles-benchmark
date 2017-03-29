package com.badoo.quantile.benchmark.bench.serialization;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.airlift.AirliftAdapter;
import com.badoo.bi.quantile.airlift.AirliftSerializer;
import com.badoo.bi.quantile.algebird.AlgebirdAdapter;
import com.badoo.quantile.benchmark.helper.AirliftTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;
import org.junit.Before;

/**
 * Created by krash on 17.02.17.
 */
public class AirliftSerializationTest extends AbstractSerializationTest {

    @Override
    protected TestHelper createHelper() {
        return new AirliftTestHelper();
    }
    @Override
    protected long estimateMemorySize(QuantileAdapter adapter) {
        return ((AirliftAdapter)adapter).digest.estimatedInMemorySizeInBytes();
    }
}
