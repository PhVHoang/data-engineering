![arch-diag-basic](https://user-images.githubusercontent.com/12546802/127985238-965e81be-c339-4a18-89a6-37ba6d34e4de.png)

An Airflow installation generally consists of the following components:

* A scheduler, which handles both triggering scheduled workflows, and submitting Tasks to the executor to run.
* An executor, which handles running tasks. In the default Airflow installation, this runs everything inside the scheduler, but most production-suitable executors actually push task execution out to workers.
* A webserver, which presents a handy user interface to inspect, trigger and debug the behaviour of DAGs and tasks.
* A folder of DAG files, read by the scheduler and executor (and any workers the executor has)
* A metadata database, used by the scheduler, executor and webserver to store state.

## References
1. https://airflow.apache.org/docs/apache-airflow/stable/concepts/overview.html
2. https://blog.locale.ai/we-were-all-using-airflow-wrong-and-now-its-fixed/
