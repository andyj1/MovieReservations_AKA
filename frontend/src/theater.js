import React, { Component } from 'react';
import {Accordion, Button, ButtonToolbar, Card, Carousel, Dropdown, DropdownButton, Jumbotron} from "react-bootstrap";
import loggedIn from "./loggedIn";
import key from '../secrets/key'


class Theater extends Component {
  constructor(props) {
    super(props);
    this.state = {
      theater: "",
      movies: ['Movie 1'],
      times: {
        'Movie 1': ['1:00 pm', '2:00 pm']
      },
        dates: ["2019-12-10", "2019-12-11", "2019-12-12", "2019-12-13", "2019-12-14", "2019-12-15", "2019-12-16", "2019-12-17", "2019-12-18"],
      seats: {
        'Movie 1': [[1,2,3], [1,2,3]]
      },
      img_link: {
          'test': 'test'
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
      .then(response => response.json())
      .then(data => {
        var movies = [];
        var movie_times = [];
        var seats = [];
        var type = [];
        data.forEach((showtime, index) => {
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
          var img_links = {};
          var movies_list = [...movies];
          movies_list.map((movie, index) => {
              fetch('https://www.googleapis.com/customsearch/v1?cx=010013580991372353059:ljv0pdmangk&searchType=image&num=1&key=' + key + '&q=' + encodeURI(movie + ' movie'), {
                  method: 'GET',
              }).then(response => response.json())
                  .then(data => {
                      img_links[movies[index]] = data.items[0]['link'];
                      this.setState({img_link: img_links});
                  });
          });
        this.setState({
          movies: movies,
          times: movie_times,
          seats: seats,
          showing_type: type,
        });
      });
  };
  showRecentMovies = () => (
    <Carousel bg="dark" variant="dark">
      {this.state.movies.map((movie, index) => (
        <Carousel.Item key={index}>
          <div style={{backgroundColor: '#007bff', height: '40vh', width: '50vw'}}>
              <img
                  src={this.state.img_link[movie]}
                  alt="Third slide"
                  style={{
                      height: '40vh',
                      width: '50vw',
                  }}
              />
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

    changeDate = (event, date) => {
        // Add query to change state of movie times
        this.setState({date: date})
        this.getMovieShowtimes(date, this.state.theater);
    };

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

  redirectMovie = (event, movie) => (
      this.props.history.push('/movie/' + movie)
  )


  render() {
    return (
      <>
        <Jumbotron>
          <h1>{this.state.theater}</h1>
            <DropdownButton id="dropdown-basic-button" title={this.state.date} style={{paddingTop: '1vh', paddingBottom: '2vh'}}>
                {this.state.dates.map((date, index) => (
                    <Dropdown.Item onClick={(e) => this.changeDate(e, date)} key={index+200}>{date}</Dropdown.Item>
                ))}
            </DropdownButton>
        </Jumbotron>
          {Object.keys(this.state.img_link).length === this.state.movies.length ?
        <div style={{paddingTop: '1vh', width: '50vw', marginLeft: 'auto', marginRight: 'auto'}}>
          {this.showRecentMovies()}
        </div>
              :
              <div style={{paddingTop: '1vh', width: '50vw', marginLeft: 'auto', marginRight: 'auto'}}>
                  <Carousel bg="dark" variant="dark">
                      <Carousel.Item >
                          <div style={{backgroundColor: '#007bff', height: '40vh', width: '50vw'}}>
                              <Carousel.Caption>
                                  <h3>{'Loading...'}</h3>
                                  <p>{}</p>
                              </Carousel.Caption>
                          </div>
                      </Carousel.Item>
                  </Carousel>
              </div>}
        <div>
          {this.showMovieShowtimes()}
        </div>
      </>
    )
  }
}

export default Theater