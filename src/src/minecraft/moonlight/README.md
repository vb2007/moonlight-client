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
2. Open Eclipse, choose a workspace and navigate to `/src/eclipse` in the project's folder
3. Import the `Client` project
4. Import the `Client` debug launch option
5. You should be able to start debugging

*If you want to, you can import the `Server` project and launch option as well, but I've never tested & used those, so they can contain build & other errors.*

### Installing & running the client

**TODO: export a `.jar` file and write this guide.**

### Compiling for yourself

**TODO: write this guide**