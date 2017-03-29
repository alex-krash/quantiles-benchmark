package com.badoo.quantile.benchmark.accuracy;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.algebird.AlgebirdProducerFactory;
import com.badoo.quantile.benchmark.helper.AlgebirdTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 15.02.17.
 */
public class AlgebirdAccuracyTest extends AbstractAccuracyTest {

    @Override
    protected TestHelper getHelper() {
        return new AlgebirdTestHelper();
    }
}
