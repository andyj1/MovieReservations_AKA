import React, {Component} from 'react';
import {Jumbotron, Button, Accordion, Card, ButtonToolbar, DropdownButton, Dropdown, Collapse} from "react-bootstrap";
import loggedIn from './loggedIn'


class Movie extends Component {
    constructor(props) {
        super(props);
        this.state = {
            movie: "",
            description: "Movie about some thing with someone",
            theaters: ["AMC West"],
            movie_time: {
                "AMC West": ["5:45 PM", "5:55 PM", "5:30 PM"],
            },
            movie_seats : {
                "AMC West": [[1,2,3], [1,2,3], [1,2,3]]
            },
            dates: ["2019-12-10", "2019-12-11", "2019-12-12"],
            date: "2019-12-10",
            rating: 'No Rating Provided',
            genre: [""],
            open: false,
            critic_rating: "Not Available",
            director: "Not Available",
            writer: "Not Available"
        };
    }
    componentDidMount() {
        var movie_name = decodeURI(this.props.match.params.movie);
        this.setState({movie: movie_name});
        this.getMovieTimes(movie_name, this.state.date);
        this.getMovieInfo(movie_name);
    };
    //Movie{movie_id=, title=Siddharth, consensus=takes an achingly compassionate and deeply unsettling look at all-too-common circumstances in modern-day India., critic_rating=90%, critic_count=41, audience_count=379, description=Siddharth is the spellbinding and gorgeously wrought tale of one father's journey across India in search of his son. Mehendra is a chain-wallah, eking out a living fixing zippers on the bustling streets of New Delhi. To ease his financial woes, he sends twelve-year-old Siddharth to work in a distant factory. When the boy doesn't come home for the Diwali holiday, Mehendra and his wife Suman slowly begin to suspect that he was kidnapped by child traffickers. With few resources and no connections, Mehendra desperately travels to Punjab and Mumbai with the hope that whoever took Siddharth might return him unharmed. A powerful family drama both heart-rending and suspenseful, Siddharth won Best Film (and Best Director for Richie Mehta) at the South Asian International Film Festival and is an Official Selection of Human Rights Watch. (c) Zeitgeist, rating=NR, genre=[Drama], director=[Richie Mehta], writer=[Richie Mehta], air_date=Jun 27, 2014, runtime=null, studio=Zeitgeist Films}
    getMovieInfo = (movie) => {
        fetch('http://192.168.1.158:1010/movie_info?movie_name=' + movie)
          .then(response => response.text())
          .then(data => {
              if (data !== 'Movie Info Not Available') {
                  var movie_json = data.substr(0, data.length - 1).replace("Movie{movie_id=, ", "");
                  movie_json = (',' + movie_json).replace(/,([a-zA-Z0-9-:_ ().@']+)=/g, "|$1=");
                  movie_json = ('| ' + movie_json.substr(1, movie_json.length - 1)).replace(/(\| )([a-zA-Z0-9-_']+)=([a-zA-Z0-9-: '.()%,]+)/g, "\"$2\":\"$3\", ");
                  movie_json = movie_json.replace(/(\| )([a-zA-Z0-9-_' ]+)=([a-zA-Z0-9-: '.()%,\[\]]+)/g, "\"$2\":$3, ");
                  movie_json = movie_json.replace(/(genre":)\[([a-zA-Z0-9, ]+)(]. ")(director":\[)/g, "$1[\"$2\"$3$4");
                  movie_json = movie_json.replace(/(director":\[)([a-zA-Z0-9, ]+)(]. ")/g, "$1\"$2\"$3");
                  movie_json = movie_json.replace(/(writer":\[)([a-zA-Z0-9, ]+)/g, "$1\"$2\"");

                  //movie_json = movie_json.replace(/(genre":)\[([a-zA-Z0-9, ]+)(]. ")(director":\[)([a-zA-Z0-9, ]+)(]. ")(writer":\[)([a-zA-Z0-9, ]+)/g, "$1[\"$2\"$3$4\"$5\"$6$7\"$8\"");
                  movie_json = '{' + movie_json.substr(0, movie_json.length - 2) + '}';
                  console.log(movie_json);

                  movie_json = JSON.parse(movie_json);
                  this.setState({
                      description: movie_json['description'],
                      genre: movie_json['genre'],
                      director: movie_json['director'],
                      writer: movie_json['writer'],
                      critic_rating: movie_json['critic_rating']
                  });

              } else {
                  this.setState({description: 'Movie Info Not Available'});
              }

          })

    };

    getMovieTimes = (movie_name, date) => {
        fetch('http://192.168.1.158:1010/movie_showtimes?movie_name=' + movie_name
          + '&date=' + date)
          .then(response => response.text())
          .then(data => {
              var temp = data.substr(1, data.length-3).split('}, ');
              var new_arr = temp.map((str, index) => {
                  var movie_json = str.replace("Showtime{showtime_id=, ", "");
                  movie_json = movie_json.replace(/=/g, ':');
                  movie_json = movie_json.replace(/([a-zA-Z0-9-_]+):([a-zA-Z0-9-: ().']+)/g, "\"$1\":\"$2\"");
                  movie_json = movie_json.replace("seats", "\"seats\"");
                  movie_json = '{' + movie_json + '}';
                  return JSON.parse(movie_json);
              });
              var movie_theaters = [];
              var movie_times = {};
              var movie_seats = {};
              new_arr.forEach((showtime, index) => {
                  if (movie_theaters.includes(showtime['theater_id'])) {
                      movie_times[showtime['theater_id']].push(showtime['time']);
                      movie_seats[showtime['theater_id']].push(showtime['seats']);
                  } else {
                      var time_arr = [];
                      var seat_arr = [];
                      time_arr.push(showtime['time']);
                      seat_arr.push(showtime['seats']);
                      movie_theaters.push(showtime['theater_id']);
                      movie_times[showtime['theater_id']] = time_arr;
                      movie_seats[showtime['theater_id']] = seat_arr;
                  }
              });
              this.setState({theaters: movie_theaters, movie_time: movie_times, movie_seats: movie_seats})
          });
    };

    showReservation = (event, theater, time) => (
        loggedIn() ? this.props.history.push('/reservation', {
            theater: theater,
            time: time,
            date: this.state.date,
            movie: this.state.movie,
            seats: this.state.movie_seats[theater][this.state.movie_time[theater].indexOf(time)]
        })
          : this.props.history.replace('/')
    );

    changeDate = (event, date) => {
        // Add query to change state of movie times
        this.setState({date: date})
        this.getMovieTimes(this.state.movie, date);
    };

    showMovieTimes = (theater) => (
        <>
            <ButtonToolbar>
                    {this.state.movie_time[theater].map((time, index) => (
                        <Button onClick={(e) => this.showReservation(e, theater, time)}variant="primary" key={index+100} style={{marginLeft: '.1vw', marginRight: '.1vw'}}>
                            {time}
                        </Button>
                    ))}
            </ButtonToolbar>
        </>
    );

    showNearbyTheaters = () => (
        <Accordion>
            {this.state.theaters.map((theater, index) => (
                    <Card key={index}>
                        <Accordion.Toggle as={Card.Header} eventKey={index.toString()}>
                            {theater}
                        </Accordion.Toggle>
                        <Accordion.Collapse eventKey={index.toString()}>
                            <Card.Body>
                                {this.showMovieTimes(theater)}
                            </Card.Body>
                        </Accordion.Collapse>
                    </Card>
                )
            )}
        </Accordion>
    );

    setOpen = (state) => (
      this.setState({open: state})
    );

    render() {
        return(
        <>
            <Jumbotron>
                <h1>{this.state.movie}</h1>
                <p>
                    {this.state.description}
                </p>
                <p>
                    <Button onClick={() => this.setOpen(!this.state.open)} variant="primary">Learn more</Button>
                </p>
                <Collapse in={this.state.open}>
                    <div>
                        <h5>{'Genre(s): ' + this.state.genre}</h5>
                        <h5>{'Director(s): ' + this.state.director}</h5>
                        <h5>{'Writer(s): ' + this.state.writer}</h5>
                        <h5>{'Critic Rating: ' + this.state.critic_rating}</h5>
                    </div>
                </Collapse>
                <DropdownButton id="dropdown-basic-button" title={this.state.date} style={{paddingTop: '1vh', paddingBottom: '2vh'}}>
                    {this.state.dates.map((date, index) => (
                        <Dropdown.Item onClick={(e) => this.changeDate(e, date)} key={index+200}>{date}</Dropdown.Item>
                    ))}
                </DropdownButton>
                {this.showNearbyTheaters()}
            </Jumbotron>

        </>
        )
    }
}

export default Movie;