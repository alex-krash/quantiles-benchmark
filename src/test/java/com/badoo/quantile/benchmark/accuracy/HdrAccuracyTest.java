package com.badoo.quantile.benchmark.accuracy;

import com.badoo.quantile.benchmark.helper.HdrTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 04.07.17.
 */
public class HdrAccuracyTest extends AbstractAccuracyTest {
    @Override
    protected TestHelper getHelper() {
        return new HdrTestHelper();
    }
}
