# PACT-Broker-Example
PACT is a contract testing tool. 
It helps to test communications between services/microservices.
But the main idea of this repository is different. 
This is manual how to use PACT Broker with Maven in Spring Boot.


If you want more information about PACT - follow [next](https://docs.pact.io/ "PACT") docs.

## Consumer
[*Consumer*](https://github.com/SlandShow/PACT-Broker-Example/tree/master/Consumer "Consumer") - A client that wants to receive some data.

Here is an example of how consumer share pact files on broker:
```
          <plugin>
                <groupId>au.com.dius</groupId>
                <artifactId>pact-jvm-provider-maven_2.12</artifactId>
                <version>3.5.11</version>
                <executions>
                    <execution>
                        <phase>install</phase>
                        <goals>
                            <goal>publish</goal>
                        </goals>
                        <!-- Run broker on local machine with docker using docker-compose file -->
                        <configuration>
                            <pactDirectory>${basedir}/target/pacts</pactDirectory>
                            <pactBrokerUrl>http://localhost:80</pactBrokerUrl>
                            <projectVersion>1.1</projectVersion>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
```

[*Consumer test*](https://github.com/SlandShow/PACT-Broker-Example/blob/master/Consumer/src/test/java/comslandshow/demo/DemoApplicationTests.java "consumer test") generate json in target file, when it exetutes via Spring Boot context. And next step on maven plugin, which share this json on broker. It's pretty easy and can be executed in CI Job.

## Provider
[*Provider*](https://github.com/SlandShow/PACT-Broker-Example/tree/master/Provider "Provider") - a service or server that provides the data.

Provider must verify interaction from broker. If interaction succesfully verified - broker mark it via `green` color, if not - via `red` color.
For more info, read [next](https://github.com/SlandShow/PACT-Broker-Example/new/master?readme=1#broker) part of documentation.

## Broker
The Pact Broker is an application for sharing for consumer driven contracts and verification results. It is optimised for use with "pacts" (contracts created by the Pact framework), but can be used for any type of contract that can be serialized to JSON.

More info available [here](https://github.com/pact-foundation/pact_broker "Broker").

For example.

1. Empty Broker:

![broker not found](https://i.ibb.co/cJGSCSk/2019-06-01-00-55-45.png)

2. Broker with consumer interaction:

![broker not found](https://i.ibb.co/n8C8Pmg/2019-06-01-00-55-56.png)

3. Broker with verified consumer interaction:

![broker not found](https://i.ibb.co/rsRdRMp/2019-06-01-01-05-55.png)

## How to run PACT Broker on my local machine?
Just use [docker compose](https://docs.docker.com/compose/overview/ "docker compose"):
`docker-compose --file docker-compose-pact.yaml  up --build` and then open [http://localhost:80](http://localhost:80).

## How can i use this example?

1. Run Broker.

2. `cd Consumer` and build the project via `mvn clean install`. Also, you can run this app, [swagger-ui](https://swagger.io/tools/swagger-ui/ "Swagger UI") is available on `http://localhost:8080/swagger-ui.html`.

3. ` cd ..` and `cd Provider`. Build the project via `mvn clean install`. And run tit next, [swagger-ui](https://swagger.io/tools/swagger-ui/ "Swagger UI") is available on `http://localhost:7073/swagger-ui.html`.
Then use `mvn pact:verify` in additional terminal, it will verify PACT Broker test.
