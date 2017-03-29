# Accuracy tests

This set of tests validates our implementations for quantiles to meet contract 
of accuracy computations. If candidate passed this test, it can be used in benchmarks

This set of tests validates correctness of quantiles for the following sequences:

* [1, 100]
* [1, 10 000]
* [1, 100 000]
* [1, 1 000 000]

With following accuracies:

* 0.5 %
* 1 %
* 1.5 %
* 2 %
