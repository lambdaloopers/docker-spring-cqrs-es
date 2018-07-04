# Spring CQRS Event Sourcing

RESTful backend for a CQRS Kafka-based event sourcing API.

* https://www.slideshare.net/lambdaloopers/kafka-infrastructure-development
* https://www.slideshare.net/lambdaloopers/kafka-infrastructure-production
* https://www.slideshare.net/lambdaloopers/kafka-infrastructure-services
* https://www.slideshare.net/lambdaloopers/kafka-infrastructure-monitoring
* https://www.slideshare.net/lambdaloopers/kafka-infrastructure-cloud

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order to run this application, you need to install
java8 SDK, docker, docker-compose and docker-machine on your
development environment.

How to setup the development environment:

* [Java 8 SDK](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) - Guide to install Java 8 SDK on multiple environments.
* [Docker Engine](https://docs.docker.com/engine/installation/) - Guide to install docker on multiple environments.
* [Docker Compose](https://docs.docker.com/compose/install/) - Guide to install docker-compose on multiple environments.
* [Docker Machine](https://docs.docker.com/machine/install-machine/) - Guide to install docker-compose on multiple environments (Non-linux based systems).

Once you have installed all of the above, you must create a docker-machine environment with

```
docker-machine create --driver virtualbox default
```

You can check more information [here](https://docs.docker.com/machine/get-started/).

### Installing

First of all, we have to setup our environment variables by copying .env.example to .env and editing the contents to our environment.

In order to run the application on your environment, first you have to start a docker-machine environent.

```
docker-machine start default
```

We have to wait so docker-machine gives us an IP for the environment, and then we can run it with

```
./bin/start.sh
```

## Built With

* [Spring](https://spring.io/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [MySQL](https://www.mysql.com/) - Relational database
* [Apache Kafka](https://kafka.apache.org/) - A distributed streaming platform

## Development

This project is based on [CQRS](https://martinfowler.com/bliki/CQRS.html)
architecture, so it isolates the writes and reads through an Event Queue.

When a Request comes into the application, it is received by a Controller
which will validate it and decide if the request requires the execution of
a Command or a Query depending on if it is a write or read request.

The Command is an Application Service that gathers a Request and
by interacting with the Entities issues an Event.
They achieve it with the help of Repositories and Domain Services.

The Query is an Application Service that gathers a Request and
asking information to the Repositories returns the proper
Entity Aggregate.

It must be highlighted that the Command writes the information to a
Kafka queue and the Query reads it from a MySQL database, so there is
the need to consume the information from Kafka and write it to MySQL, this 
is the role of the Consumers.

The Consumer listen to Events by Consuming the appropriate Queue Topic and 


## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **Adrià Batlle** - *Initial work* - [sudobat](https://github.com/sudobat)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

