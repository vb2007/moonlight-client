# moonlight-client
## A casual 1.8 Minecraft client with optifine.

The client was developed with MCP 9.10 and OptiFine 1.8 HD U H6, using Eclipse 2023-09 (4.29.0).

## Prerequisites

- Have Java Development Kit *(JDK)* 8 installed (specifically: **jdk-8u431**)
  - [CEU Mirror](https://cdn.vb2007.hu/autoindex/rest-in-peace-intent/jdk-8u431-windows-x64.exe) (direct download, for Windows)
  - [Official download](https://www.oracle.com/java/technologies/javase/javase8u211-later-archive-downloads.html), from Oracle, requires login, specific version might not be there anymore
- Have **Eclipse IDE for Java developers** installed (any version will do)
  - [CEU Mirror](https://cdn.vb2007.hu/autoindex/rest-in-peace-intent/eclipse-inst-jre-win64.exe) (direct download, for Windows)
  - [Official download](https://www.eclipse.org/downloads/), from Eclipse.org

<!-- If you clone the repository directly from GitHub, then you can just skip to the [debugging](#debugging-the-client) part. -->

### Debugging the client

Do the following steps to make the client Eclipse-ready:

1. Clone the repository
2. Open Eclipse and choose the freshly cloned repo as a workspace with the `/src/eclipse` path after the project'sfolder

![1st img](https://cdn.vb2007.hu/autoindex/rest-in-peace-intent/img/1.png)

4. Import the `Client` project

![2nd img](https://cdn.vb2007.hu/autoindex/rest-in-peace-intent/img/2.png)

![3rd img](https://cdn.vb2007.hu/autoindex/rest-in-peace-intent/img/3.png)

6. Import the `Client` debug launch configuration

![4th img](https://cdn.vb2007.hu/autoindex/rest-in-peace-intent/img/4.png)

![5th img](https://cdn.vb2007.hu/autoindex/rest-in-peace-intent/img/5.png)

8. You should be able to start debugging

![6th img](https://cdn.vb2007.hu/autoindex/rest-in-peace-intent/img/6.png)

*If you want to, you can import the `Server` project and launch option as well, but I've never tested & used those, so they can contain build & other errors. If you've already imported the project by accident, then just delete it.*

### Installing & running the client

**TODO: export a `.jar` file and write this guide.**

### Compiling for yourself

**TODO: write this guide**
