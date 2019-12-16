import React, { Component } from 'react';
import {Tabs, Tab, Jumbotron, Card, Form, Col, InputGroup, Button} from "react-bootstrap";
import loggedIn from "./loggedIn";


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
    }
  };
  componentDidMount() {
    if (loggedIn()){
      fetch("http://192.168.1.158:1010/user?username=" + localStorage.getItem('username'))
        .then(response => {if (response.status === 200) return response.json()})
        .then(data => {
          var user_json = data;//.substr(0, data.length-1).replace("User{", "");
          /*
          user_json = user_json.replace(', password=', '');
          user_json = user_json.replace(/([a-zA-Z0-9-_]+)=([a-zA-Z0-9-: ().@]+)/g, "\"$1\":\"$2\"");
          user_json = '{' + user_json + '}';
          user_json = JSON.parse(user_json);
           */

          this.setState({
            name: user_json['name'],
            username: localStorage.getItem('username'),
            genre: user_json['favorite_genre'],
            zip_code: user_json['zip_code'],
            email: user_json['email'],
            admin: 'true'//user_json['admin'],
          });
        })
      this.getReservations();

    } else {
      this.props.history.push('/');
    }
    fetch('http://192.168.1.158:1010/theaters', {
      method: 'GET',
    }).then(response => response.json())
      .then(data => {
        console.log(data);
      });
  }

  addFood = (event) => {

  }

  addShowtime = (event) => {

  };

  getReservations = () => {
    fetch("http://192.168.1.158:1010/get_seats?username=" + localStorage.getItem('username'))
      .then(response => response.json())
      .then(data => console.log(data));

    fetch("http://192.168.1.158:1010/get_food?username=" + localStorage.getItem('username'))
      .then(response => response.json())
      .then(data => console.log(data))
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
              </Card>
            </Tab>
            {this.state.admin === "true" ?
              <Tab eventKey="Showtimes" title="Create Showtime">
                <Card style={{paddingTop: '2vh', paddingBottom: '2vh', paddingRight: '2vw', paddingLeft: '1vw'}}>
                  <Form noValidate validated={this.state.validated} onSubmit={(e) => this.handleSubmit(e)} style={{
                    width: '28vw',
                    paddingLeft: '4vw',
                    paddingTop: '0vh',
                    paddingBottom: '2vh'
                  }}>
                    <Form.Row>
                      <Form.Group as={Col} md="8" name="name" controlId="exampleForm.ControlSelect1">
                        <Form.Label>Theater</Form.Label>
                        <Form.Control as="select">
                          <option>1</option>
                        </Form.Control>
                      </Form.Group>
                      <Form.Group as={Col} md="8" name="name" controlId="validationUsername">
                        <Form.Label>Time</Form.Label>
                        <Form.Control
                          required
                          type="text"
                          placeholder="Enter Time of showing"
                        />
                        <Form.Control.Feedback>{'Please enter valid time format (H:MM {pm})'}</Form.Control.Feedback>
                      </Form.Group>
                      <Form.Group as={Col} md="8" name="username" controlId="validationPassword">
                        <Form.Label>Date</Form.Label>
                        <Form.Control
                          required
                          type="text"
                          placeholder="Date"
                        />
                      </Form.Group>
                    </Form.Row>
                    <Button type="submit">Sign In</Button>
                  </Form>
                </Card>
              </Tab>
              :
              <Tab eventKey="reservations" title="Reservations">
                <Card style={{paddingTop: '2vh', paddingBottom: '2vh', paddingRight: '2vw', paddingLeft: '1vw'}}>
                </Card>
              </Tab>
            }
            {this.state.admin === "true" ?
              <Tab eventKey="Food" title="Add Food">
                <Card style={{paddingTop: '2vh', paddingBottom: '2vh', paddingRight: '2vw', paddingLeft: '1vw'}}>

                </Card>
              </Tab> : <></>
            }
          </Tabs>
        </Jumbotron>
      </>
    );
  }
}

export default Profile;