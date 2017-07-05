package com.badoo.quantile.benchmark.accuracy;

import com.badoo.quantile.benchmark.helper.CombinedTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 05.07.17.
 */
public class CombinedAccuracyTest extends AbstractAccuracyTest {
    @Override
    protected TestHelper getHelper() {
        return new CombinedTestHelper();
    }
}
