# Spark Architecture

- Master-slave: one process (the master) coordiates and distributes work among slave processes.
- Master process: Driver
- Slave process: Executor

## Cluster manager
A MapReduce or Spark job runs on a cluster of machines. *MapReduce's Application Master or Spark's Driver process do not have the authority or the ability
to allocate cluster resources for job execution.* Another piece of software manges the physical resources of the cluster and arbitrates them among jobs, usually
based on user-defined policy. The Spark driver negotiates resources with the cluster manager to launch executor processes. 
YARN is one such example of a cluster manager software. Spark is compatible with the following cluster managers
- Hadoop YARN
- Apache Mesos
- Built-in standalone cluster manager
- Kubernetes
- Local mode

## Execution modes
- **Cluster mode:** In cluster mode, the user submits a spark application (Java .jar file, Python, or R script) to the cluster manager. The manager in turn spawns the driver and the executor processes on worker nodes to execute the job. 
In this setting, both the driver and the executors live inside the cluster.
- **Client mode:**  The client mode is similar to the cluster mode, except that the driver process lives on the client machine that is used to submit the Spark job outside the cluster. The machine hosting the driver process is not co-located on the cluster running the executor processes. 
- The client machine is responsible for maintaining the driver process, while the cluster is responsible for maintaining the executor processes.
