# Spring Data multi-store tests

Sample project to allow testing that the various Spring Data modules can be used together. It consists of a set of Maven profiles that can be used to run the test case against a variety of dependency setups. The test case consists of a domain class and repository for Spring Data JPA, MongoDB and Neo4J.

## Dependency sets

The profiles can roughly be grouped into some defining the dependency versions for the Spring Data modules as well as ones to define the Spring version to be used. By default the latest snapshot versions of the next Spring Data milestones will be used as well as the snapshot version of the next major Spring version (4.0 as of the time of writing).

## Profiles

- *snapshots* - (default) Includes snapshots of upcoming major versions of Spring Data modules
- *bugfix-snapshots* - Includes snapshots of the next bugfix releases of Spring Data modules
- *milestones* - Includes latest milestones of upcoming versions of Spring Data modules
- *releases* - Includes latest release versions of Spring Data modules

- *fix-spring* - Fixes Spring dependencies to a version (requires one of the following profiles to be activated as well)
- *3.2* - Includes latest Spring version of the 3.2 branch
- *4.0* - Includes latest Spring version of the 4.0 branch
- *spring-snapshots* - Includes the latest Spring snapshots (4.0 branch)

## Usage
The project can be built with profiles activated to produce the wanted set of dependencies. E.g. a Maven run with:

`mvn clean install -Pmilestones,fix-spring,4.0`

Would run the project against the latest milestones of the Spring Data modules as well as Spring 4.0.0.RC2.
