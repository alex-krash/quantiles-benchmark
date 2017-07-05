# Quantiles benchmark
This repository contains benchmarks for different implementations of quantile computations.

The original goal was to find an implementation with following characteristics:

* Applicable to Map/Reduce implementation (scalable, parallelizable)
* Has ~1% error

Each participant was organized in separate package, and adopted with implementation of interfaces from com/badoo/bi/quantile/ProducerFactory.java.
If you want to add new candidate here, you have to implement these interfaces, and add new package.