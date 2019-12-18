import React, { Component } from 'react';
import {Tabs, Tab, Jumbotron, Card, Form, Col, InputGroup, Button, Accordion} from "react-bootstrap";
import loggedIn from "./loggedIn";

const seat_val = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];

class Profile extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      name: '',
      genre: '',
      zip_code: '',
      admin: '',
      email: '',
      theaters: [],
      validated: '',
    }
  };
  componentDidMount() {
    if (loggedIn()) {
      this.getReservations();
      fetch("http://192.168.1.158:1010/user?username=" + localStorage.getItem('username'))
        .then(response => response.json())
        .then(data => {
            console.log(data);
          var user_json = data;
          if (user_json['admin']) {
            fetch("http://192.168.1.158:1010/theaters")
                .then(response => response.json())
                .then(data => {
                  var theater_names = [];
                  data.map((item) => {
                    theater_names.push(item['theater_id'])
                  });
                  this.setState({
                    theaters: theater_names,
                    name: user_json['name'],
                    username: localStorage.getItem('username'),
                    genre: user_json['favorite_genre'],
                    zip_code: user_json['zip_code'],
                    email: user_json['email'],
                    admin: user_json['admin'],
                  })
                });
          } else {
              this.setState({
                name: user_json['name'],
                username: localStorage.getItem('username'),
                genre: user_json['favorite_genre'],
                zip_code: user_json['zip_code'],
                email: user_json['email'],
                admin: user_json['admin'],
              });
            }
        })
    } else {
      this.props.history.push('/');
    }
  }

  handleShowtimeSubmit = (event) => {
    event.preventDefault();
    const form = event.currentTarget;
      console.log(form);
    fetch('http://192.168.1.158:1010/add_showtime?username=' + localStorage.getItem('username')
          + '&movie_name=' + form.elements.movie.value
          + '&theater_name=' + form.elements.theater_name.value
          + '&date=' + form.elements.date.value
          + '&time=' + form.elements.time.value
          + '&type=' + form.elements.type.value)
        .then(response => {
          if (response.status === 200) {
            this.props.history.push('/');
          }
        });

  };
  handleFoodSubmit = (event) => {
    event.preventDefault();
    const form = event.currentTarget;
    console.log(form.elements.Foodid.value);
    console.log(form.elements.Fooddesc.value)
    fetch('http://192.168.1.158:1010/add_food?username=' + localStorage.getItem('username')
        + '&food_id=' + form.elements.Foodid.value
        + '&food_desc=' + form.elements.Fooddesc.value)
        .then(response => {
            console.log(response)
          if (response.status === 200) {
            this.props.history.push('/');
          }
        });
  };

  getReservations = () => {
    fetch("http://192.168.1.158:1010/get_seats?username=" + localStorage.getItem('username'))
      .then(response => response.json())
      .then(data => {
        var seats = [];
        var movie_name = [];
        var movie_date = [];
        var movie_time = [];
        var movie_type = [];
        var movie_theater = [];
        data.map((reservation, index) => {
          var temp = [...reservation['seat_num']];
          for (var i = 0; i < temp.length; i++) {
            var letter =  seat_val[((temp[i]) / 5) | 0];
            var number = ((temp[i] - 1) % 5) + 1;
            temp[i] = letter + number.toString();
          }
          seats.push(temp);
          fetch("http://192.168.1.158:1010/showtime_info?showtime_id=" + reservation['showtime_id'])
              .then(response => response.json())
              .then(data => {
                movie_name.push(data['movie_id']);
                movie_date.push(data['date']);
                movie_time.push(data['time']);
                movie_type.push(data['type']);
                movie_theater.push(data['theater_id']);
              })

        });

        this.setState({
          seats: seats,
          movies: movie_name,
          dates: movie_date,
          times: movie_time,
          types: movie_type,
          reserve_theaters: movie_theater,
        });
      });

    fetch("http://192.168.1.158:1010/get_food?username=" + localStorage.getItem('username'))
      .then(response => response.json())
      .then(data => {
        var food_id = [];
        var counts = [];
        var movie_name = [];
        var movie_date = [];
        var movie_time = [];
        var movie_type = [];
        var movie_theater = [];
        data.map((reservation, index) => {
          food_id.push(reservation['food_id']);
          counts.push(reservation['count']);
          fetch("http://192.168.1.158:1010/showtime_info?showtime_id=" + reservation['showtime_id'])
              .then(response => response.json())
              .then(data => {
                movie_name.push(data['movie_id']);
                movie_date.push(data['date']);
                movie_time.push(data['time']);
                movie_type.push(data['type']);
                movie_theater.push(data['theater_id']);
              })
        });
        var food_res = {
          food_id: food_id,
          food_count: counts,
          fname: movie_name,
          fdate: movie_date,
          ftime: movie_time,
          ftheater: movie_theater,
        };
        this.setState(food_res);
      })
  };

  handleReservationsClick = (event) => {
    this.props.history.push('/user/reservations', {
      seats: this.state.seats,
      movies: this.state.movies,
      dates: this.state.dates,
      times: this.state.times,
      types: this.state.types,
      reserve_theaters: this.state.reserve_theaters,
      food_id: this.state.food_id,
      food_count: this.state.food_count,
      fname: this.state.fname,
      fdate: this.state.fdate,
      ftime: this.state.ftime,
      ftheater: this.state.ftheater,
    });
  };

  handleLogout = (event) => {
      localStorage.removeItem('username');
      this.props.history.push('/')
  };


  render() {
    return (
      <>
        <Jumbotron>
          <Tabs defaultActiveKey="profile" id="uncontrolled-tab-example">
            <Tab eventKey="profile" title="Profile">
              <Card style={{paddingTop: '2vh', paddingBottom: '2vh', paddingRight: '2vw', paddingLeft: '1vw'}}>
                <Card.Title>{'@' + this.state.username}</Card.Title>
                <Card.Subtitle className="mb-2 text-muted" style={{marginLeft: '1vw'}}>{'Email: ' +  this.state.email}</Card.Subtitle>
                <Card.Subtitle className="mb-2 text-muted" style={{marginLeft: '1vw'}}>{'Name: ' +  this.state.name}</Card.Subtitle>
                <Card.Subtitle className="mb-2 text-muted" style={{marginLeft: '1vw'}}>{'Zip Code: ' +  this.state.zip_code}</Card.Subtitle>
                <Card.Subtitle className="mb-2 text-muted" style={{marginLeft: '1vw'}}>{'Favorite Genre: ' +  this.state.genre}</Card.Subtitle>
                {this.state.admin ? <></> :
                    <Button onClick={this.handleReservationsClick}>
                      Reservations
                    </Button>}
                    <p/>
                  <Button onClick={this.handleLogout}>
                      Logout
                  </Button>
              </Card>
            </Tab>
            {this.state.admin ?
              <Tab eventKey="Showtimes" title="Create Showtime">
                <Card style={{paddingTop: '2vh', paddingBottom: '2vh', paddingRight: '1vw', paddingLeft: '1vw'}}>
                  <Form noValidate validated={this.state.validated} onSubmit={(e) => this.handleShowtimeSubmit(e)} style={{
                    width: '28vw',
                    paddingLeft: '1vw',
                    paddingTop: '0vh',
                    paddingBottom: '2vh'
                  }}>
                    <Form.Row>
                      <Form.Group as={Col} md="8" name="name" controlId="theater_name">
                        <Form.Label>Theater</Form.Label>
                        <Form.Control as="select">
                          {this.state.theaters.map((theater_id) => (
                              <option>{theater_id}</option>
                          ))}
                        </Form.Control>
                      </Form.Group>
                      <Form.Group as={Col} md="8" name="movie" controlId="movie">
                        <Form.Label>Movie Name</Form.Label>
                        <Form.Control
                            required
                            type="text"
                            placeholder="Enter the movie name"
                        />
                        <Form.Control.Feedback></Form.Control.Feedback>
                      </Form.Group>
                      <Form.Group as={Col} md="8" name="time" controlId="time">
                        <Form.Label>Start Time</Form.Label>
                        <Form.Control
                          required
                          type="text"
                          placeholder="HH:MM {pm}"
                        />
                        <Form.Control.Feedback>{'Please enter valid time format (H:MM {pm})'}</Form.Control.Feedback>
                      </Form.Group>
                      <Form.Group as={Col} md="8" name="date" controlId="date">
                        <Form.Label>Date</Form.Label>
                        <Form.Control
                          required
                          type="text"
                          placeholder="YYYY-MM-DD"
                        />
                      </Form.Group>
                      <Form.Group as={Col} md="8" name="Type" controlId="type">
                        <Form.Label>Type</Form.Label>
                        <Form.Control
                            required
                            type="text"
                            placeholder="Type of Theater"
                        />
                      </Form.Group>
                    </Form.Row>
                    <Button type="submit">
                      Create
                    </Button>
                  </Form>
                </Card>
              </Tab>
              : <></>
            }
            {this.state.admin ?
              <Tab eventKey="Food" title="Add Food">
                <Card style={{paddingTop: '2vh', paddingBottom: '2vh', paddingRight: '2vw', paddingLeft: '1vw'}}>
                  <Form onSubmit={(e) => this.handleFoodSubmit(e)} style={{
                    width: '28vw',
                    paddingLeft: '1vw',
                    paddingTop: '0vh',
                    paddingBottom: '2vh'
                  }}>
                    <Form.Row>
                      <Form.Group as={Col} md="8" name="id" controlId="Foodid">
                        <Form.Label>Food ID</Form.Label>
                        <Form.Control
                            required
                            type="text"
                            placeholder="Enter the Food ID"
                        />
                          <Form.Control.Feedback type="invalid">
                          </Form.Control.Feedback>
                      </Form.Group>
                      <Form.Group as={Col} md="8" name="desc" controlId="Fooddesc">
                        <Form.Label>Food Description</Form.Label>
                        <Form.Control
                            required
                            type="text"
                            placeholder="Enter the Food Description"
                        />
                          <Form.Control.Feedback type="invalid">
                          </Form.Control.Feedback>
                      </Form.Group>
                    </Form.Row>
                      <Form.Row>
                        <Button type="submit">
                          Add
                        </Button>
                      </Form.Row>
                  </Form>
                </Card>
              </Tab>
                : <></>
            }
          </Tabs>
        </Jumbotron>
      </>
    );
  }
}

export default Profile;