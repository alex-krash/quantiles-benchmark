package com.badoo.quantile.benchmark.accuracy;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.tdunning.TDunningProducerFactory;
import com.badoo.quantile.benchmark.helper.TDunningTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 15.02.17.
 */
public class TDunningAccuracyTest extends AbstractAccuracyTest {
    @Override
    protected TestHelper getHelper() {
        return new TDunningTestHelper();
    }
}
