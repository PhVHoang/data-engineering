Spark is a general-purpose distributed data processing engine that is suitable for use in a wide range of circumstances. 
On top of the Spark core data processing engine, there are libraries for SQL, machine learning, graph computation, and stream processing, 
which can be used together in an application. Programming languages supported by Spark include: Java, Python, Scala, and R. 
Application developers and data scientists incorporate Spark into their applications to rapidly query, analyze, and transform data at scale. 
Tasks most frequently associated with Spark include ETL and SQL batch jobs across large data sets, processing of streaming data from sensors, 
IoT, or financial systems, and machine learning tasks.

Before Spark, there was MapReduce, a resilient distributed processing framework, 
which enabled Google to index the exploding volume of content on the web, across large clusters of commodity servers.

![spark](https://user-images.githubusercontent.com/12546802/127589415-709a081f-6ff8-47d9-9a92-b53b1ce1725e.png)

There were 3 core concepts to the Google strategy:

**Distribute data**: when a data file is uploaded into the cluster, it is split into chunks, called data blocks, and distributed amongst the data nodes and replicated across the cluster.

**Distribute computation**: users specify a map function that processes a key/value pair to generate a set of intermediate key/value pairs and a reduce function that merges all intermediate values associated with the same intermediate key. Programs written in this functional style are automatically parallelized and executed on a large cluster of commodity machines in the following way:
* The mapping process runs on each assigned data node, working only on its block of data from a distributed file.
* The results from the mapping processes are sent to the reducers in a process called "shuffle and sort": key/value pairs from the mappers are sorted by key, partitioned by the number of reducers, and then sent across the network and written to key sorted "sequence files" on the reducer nodes.
* The reducer process executes on its assigned node and works only on its subset of the data (its sequence file). The output from the reducer process is written to an output file.

**Tolerate faults**: both data and computation can tolerate failures by failing over to another node for data or processing.

## Useful repositories
1. https://github.com/MrPowers/spark-daria

## References
1. https://developer.hpe.com/blog/spark-101-what-is-it-what-it-does-and-why-it-matters/
2. https://educative.io
3. https://jaceklaskowski.gitbooks.io/mastering-spark-sql/content/
4. https://jaceklaskowski.gitbooks.io/spark-structured-streaming/content/
5. [Spark Internals](https://github.com/JerryLead/SparkInternals)
6. https://github.com/spoddutur/spark-notes
