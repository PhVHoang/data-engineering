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


## How underlying execution happens when running a job
![sparks_job_context](https://user-images.githubusercontent.com/12546802/155882785-f6d64465-fa57-4be7-9663-9a27314703a8.jpeg)

## How Spark works
![how_spark_works](https://user-images.githubusercontent.com/12546802/155882865-9bca0bef-d354-4db4-94c0-bd647fec2560.jpeg)

Once you do a spark-submit, a driver program is launched and this requests for resources to the cluster manager and at the same time the main program of the user function of the user processing program is initiated by the driver program.

Based on that, the execution logic is processed and parallely Spark context is also created. Using the Spark context, the different transformations and actions are processed. So, till the time the action is not encountered, all the transformations will go into the Spark context in the form of DAG that will create RDD lineage. 

Once the action is called, job is created. Job is the collection of different task stages. Once these tasks are created, they launched by the cluster manager on the worker nodes and this is done with the help of a class called task scheduler.

The conversion of RDD lineage into tasks is done by the DAG scheduler. Here DAG is created based on the different transformations in the program and once the action is called there are split into different stages of tasks and submitted to the task scheduler as tasks become ready. 

Then there are launched on the different executors in the worker node through the cluster manager. The entire resouce allocation and the tracking of the jobs and tasks are performed by the cluster manager. 

As soon as you do a spark-submit, your user program and other configuration mentioned are copied onto all the available nodes in the cluster. So that the program becomes the local read on all the worker nodes. Hence, the parallel executors running on the diffrent worker nodes do not have to do any kind of network routing.
