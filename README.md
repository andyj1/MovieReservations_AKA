### MovieReservations_AKA
##### Project: Movie Theater Reservation System
> Group: **AKA** -- by [Andy Jeong](https://github.com/andyj1), [Kevin Lin](https://github.com/kevinlin6543), [Ali Rahman](https://github.com/alirahman17)

##### Overview
> Based on web-scraped data from IMDB, AMC, and RottenTomatoes for movies and theater information using *scrapy* Python library,  this project is to develop a database system that allows users to reserve seats as well as food menu for a movie at a specific theater. This web application demonstrates database management system for a movie theater. 

###### Tech Stack
> MongoDB (non-relational), Spark Framework, ReactJS, Maven

##### Routes
- /signup : params(username, password, name, email, genre, zip_code, admin_boolean) --> HttpStatus code
- /login : header(token), params(username, password) --> token
- /theaters : params(None) --> Theater list
- /movies : params(None) --> Movies list
- /movie_showtimes : params(movie_name, date) --> movie showtimes
- /showtimes : params(theater, date, movie) -> movie showtimes
- /seats : params(theater_name, date, movie_name, time, requested_seats, username) --> reserved seats
- /get_seats : params(username) --> reserved seatings
- /get_food : params(username) --> reserved foods
- /movie_info : params(movie_name) --> movie descriptions
- /food : params(theater_name, date, movie_name, time, food, quantity, username) --> reserved foods
- /user : params(username) --> User object
- /add_food : params(username, food_id, food_desc) --> created food
- /add_showtime : params(username, movie_name, theater_name, date, time, type) --> created movie showtime
- /showtime_info : params(showtime_id) --> movie showtime description
- /food_info : params(None) --> available food descriptions

##### Build Backend Server with Maven
```console
mvn clean compile                         // compiles the project
mvn package                               // compile and produce a deployable artifact (JAR)
java -jar ${path to JAR file} Application // default port set to 1010
```
##### Build Frontend with Yarn
```console
yarn start
yarn test
yarn build
yarn eject
```