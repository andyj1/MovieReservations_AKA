import React, { Component } from 'react';
import {Accordion, Button, ButtonToolbar, Card, Carousel, Jumbotron} from "react-bootstrap";
import loggedIn from "./loggedIn";


class Theater extends Component {
  constructor(props) {
    super(props);
    this.state = {
      theater: "",
      movies: ['Movie 1'],
      times: {
        'Movie 1': ['1:00 pm', '2:00 pm']
      },
      seats: {
        'Movie 1': [[1,2,3], [1,2,3]]
      },
      date: '2019-12-11',
    }
  }

  componentDidMount() {
    var theater_name = this.props.match.params.theater;
    this.setState({theater: this.props.match.params.theater});
    this.getMovieShowtimes(this.state.date, theater_name)
  };

  getMovieShowtimes = (date, theater) => {
    fetch('http://192.168.1.158:1010/all_showtimes?theater_name=' + encodeURI(theater)
      + '&date=' + date)
      .then(response => response.text())
      .then(data => {
        console.log(data);
        var temp = data.substr(1, data.length-3).split('}, ');
        var new_arr = temp.map((str, index) => {
          var movie_json = str.replace("Showtime{showtime_id=, ", "");
          movie_json = movie_json.replace(/=/g, ':');
          movie_json = movie_json.replace(/([a-zA-Z0-9-_]+):([a-zA-Z0-9-: ().'&]+)/g, "\"$1\":\"$2\"");
          movie_json = movie_json.replace("seats", "\"seats\"");
          movie_json = '{' + movie_json + '}';
          return JSON.parse(movie_json);
        });
        var movies = [];
        var movie_times = [];
        var seats = [];
        var type = [];
        new_arr.forEach((showtime, index) => {
          if (movies.includes(showtime['movie_id'])) {
            movie_times[showtime['movie_id']].push(showtime['time']);
            seats[showtime['movie_id']].push(showtime['seats']);
            type[showtime['movie_id']].push(showtime['type'])
          } else {
            var time_arr = [];
            var seat_arr = [];
            var type_arr = [];
            time_arr.push(showtime['time']);
            seat_arr.push(showtime['seats']);
            type_arr.push(showtime['type']);
            movies.push(showtime['movie_id']);
            movie_times[showtime['movie_id']] = time_arr;
            seats[showtime['movie_id']] = seat_arr;
            type[showtime['movie_id']] = type_arr;
          }
        });
        this.setState({
          movies: movies,
          times: movie_times,
          seats: seats,
          showing_type: type,
        });
        console.log(type);
      });
  };
  showRecentMovies = () => (
    <Carousel bg="dark" variant="dark">
      {this.state.movies.map((movie, index) => (
        <Carousel.Item key={index}>
          <div style={{backgroundColor: '#007bff', height: '40vh', width: '50vw'}}>
            <Carousel.Caption>
              <h3 onClick={(e) => {this.redirectMovie(e, movie)}}>{movie}</h3>
              <p>{}</p>
            </Carousel.Caption>
          </div>
        </Carousel.Item>
      ))}
    </Carousel>
  );

  showReservation = (event, theater, time, movie) => {
    loggedIn() ? this.props.history.push('/reservation', {
        theater: theater,
        time: time,
        date: this.state.date,
        movie: movie,
        seats: this.state.seats[movie][this.state.times[movie].indexOf(time)]
      })
      : this.props.history.replace('/')
  };

  showMovieShowtimes = () => (
    <Accordion>
      {this.state.movies.map((movie, index) => (
          <Card key={index}>
            <Accordion.Toggle as={Card.Header} eventKey={index.toString()}>
              {movie}
            </Accordion.Toggle>
            <Accordion.Collapse eventKey={index.toString()}>
              <Card.Body>
                {this.showMovieTimes(this.state.times[movie], movie)}
              </Card.Body>
            </Accordion.Collapse>
          </Card>
        )
      )}
    </Accordion>
  );

  showMovieTimes = (times, movie) => (
    <>
      <ButtonToolbar>
        {times.map((time, index) => (
          <Button onClick={(e) => this.showReservation(e, this.state.theater, time, movie)}variant="primary" key={index+100} style={{marginLeft: '.1vw', marginRight: '.1vw'}}>
            {time}
          </Button>
        ))}
      </ButtonToolbar>
    </>
  );


  render() {
    return (
      <>
        <Jumbotron>
          <h1>{this.state.theater}</h1>
        </Jumbotron>
        <div style={{paddingTop: '1vh', width: '50vw', marginLeft: 'auto', marginRight: 'auto'}}>
          {this.showRecentMovies()}
        </div>
        <div>
          {this.showMovieShowtimes()}
        </div>
      </>
    )
  }
}

export default Theater