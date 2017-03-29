package com.badoo.quantile.benchmark.bench;

import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.HotspotMemoryProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.stream.DoubleStream;

/**
 * Created by krash on 14.02.17.
 */
public abstract class Helper {

    public static void run(Class... who) throws Exception {
        OptionsBuilder builder = new OptionsBuilder();
        Options opts = builder
//                .addProfiler(GCProfiler.class)
                .build();

        for (Class aWho : who) {
            builder.include(aWho.getName());
        }

        new Runner(opts).run();
    }
}
