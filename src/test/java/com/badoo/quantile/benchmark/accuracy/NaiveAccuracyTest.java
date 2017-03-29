package com.badoo.quantile.benchmark.accuracy;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.naive.NaiveProducerFactory;
import com.badoo.quantile.benchmark.helper.NaiveTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 15.02.17.
 */
public class NaiveAccuracyTest extends AbstractAccuracyTest {
    @Override
    protected TestHelper getHelper() {
        return new NaiveTestHelper();
    }
}
