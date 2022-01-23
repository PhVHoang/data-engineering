## Architecture
![architecture-worker-docs](https://user-images.githubusercontent.com/12546802/150664503-aadba40a-9bc5-41e5-ade0-fd3fe1a74c49.png)

- The data read from the under store can be stored in the worker and be immediately availabe to other clients
- The client can be lightweight and does not depend on the under storage connectors

### Benefits of using Alluxio
- Memory-speed I/O
- Simplified Cloud and Object Storage Adoption
- Simplified Data Management
- Easy Application Deployment

### Examples of Alluxio as a data orchestration layer

![alluxio-presto](https://user-images.githubusercontent.com/12546802/150664473-917cfbbd-de14-47e5-9f0f-4e6f4dd5ff6c.png)

## References
1. https://docs.alluxio.io/os/user/stable/en/overview/Architecture.html
2. https://www.alluxio.io/blog/building-a-high-performance-platform-on-aws-to-support-real-time-gaming-services-using-presto-and-alluxio/
3. 
