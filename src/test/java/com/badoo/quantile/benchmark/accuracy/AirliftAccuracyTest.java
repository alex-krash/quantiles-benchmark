package com.badoo.quantile.benchmark.accuracy;

import com.badoo.quantile.benchmark.helper.AirliftTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;

/**
 * Created by krash on 15.02.17.
 */
public class AirliftAccuracyTest extends AbstractAccuracyTest {

    @Override
    protected TestHelper getHelper() {
        return new AirliftTestHelper();
    }
}
