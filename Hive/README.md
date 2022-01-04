## Hive architecture
![hive](https://user-images.githubusercontent.com/12546802/143522110-f4c130ce-810a-4c35-8e5c-615d435f1cdb.png)


=> In order for Hive to process these files on File Storage, it must have a mapping from SQL tables in the runtimes to files and directories in the storage component => Hive uses Metastore (HMS) to manage the metadata about the files (table columns, file locations, file formats,...)

## Trino runtime replaces Hive runtime
![trino](https://user-images.githubusercontent.com/12546802/143522425-9219fa06-7335-4347-9edb-019ef379e733.png)

=> The HMS is the only Hive process used in the entire Trno ecosystem when using the Hive connector
=> The HMS is actually a simple service with a binary API using the Thrift protocol, this service makes updates to the metadata, stored in an RDBMS such as PostgreSQL, MySQL,...
=> There are other compatible replacements of the HMS such as AWS Glue

## Connect to Hive
Two ways to connect to Hive
1. Using Hive Metastore server, which then connects in the background to a relational db such as mysql for schema manifestation (this run on port 9038 generally)
2. Hive JDBC server, called HiveServer2 (run on port 10001 generally)

## References
https://trino.io/blog/2020/10/20/intro-to-hive-connector.html
https://cwiki.apache.org/confluence/display/HIVE
