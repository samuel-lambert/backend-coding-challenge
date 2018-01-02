# Coveo Backend Coding Challenge
(inspired by https://github.com/busbud/coding-challenge-backend-c)

## Requirements

Design an API endpoint that provides auto-complete suggestions for large cities.

- The endpoint is exposed at `/suggestions`
- The partial (or complete) search term is passed as a querystring parameter `q`
- The caller's location can optionally be supplied via querystring parameters `latitude` and `longitude` to help improve relative scores
- The endpoint returns a JSON response with an array of scored suggested matches
    - The suggestions are sorted by descending score
    - Each suggestion has a score between 0 and 1 (inclusive) indicating confidence in the suggestion (1 is most confident)
    - Each suggestion has a name which can be used to disambiguate between similarly named locations
    - Each suggestion has a latitude and longitude

## "The rules"

- *You can use the language and technology of your choosing.* It's OK to try something new (tell us if you do), but feel free to use something you're comfortable with. We don't care if you use something we don't; the goal here is not to validate your knowledge of a particular technology.
- End result should be deployed on a public Cloud (Heroku, AWS etc. all have free tiers you can use).

## Configuration

Various configuration options can be set in a configuration file, which can be found at the root folder as
'application.properties'. These options are:

#### retrieve_user_location
Should the service try to use the IP address of the client to improve accuracy of the score if the latitude and/or longitude
are not included in the URL?
Valid choices: true or false

#### fallback_latitude
What would be the default latitude used to calculate scores if it was not included in the URL and 'retrieve_user_location' is
not enabled?
Valid choices: Anything between -90.0 and 90.0 inclusively

#### fallback_longitude
What would be the default longitude used to calculate scores if it was not included in the URL and 'retrieve_user_location' is
not enabled?
Valid choices: Anything between -180.0 and 180.0 inclusively

Note: this configuration file is managed by the Spring Framework, This means you can also override any Spring-related
options in this file, such as server address or port number.

## Usage

To clone the repository:

```sh
$ git clone https://github.com/samuel-lambert/backend-coding-challenge.git
```

To build the project:
```sh
$ cd backend-coding-challenge
$ mvn clean install
```

To run:
```sh
$ mvn spring-boot:run
```

Note: Maven needs to be install on your machine!

## Design notes

Initially, I wanted to calculate scores according to 3 aspects:
* Distance between client and resulting cities of search query
* Size (population) of the cities returned. For example, London, UK would always score better than London, ON.
* Similarities between the search query string and a city name.

However, I feel it is better to only use the distance between a client and a city to calculate a score on a result.
It is much more probable that people living close to _London, ON_ do not want to have _London, UK_ as a first
match when they are looking for _London_.
