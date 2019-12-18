import React, {Component} from 'react';
import {Button, Card, Form, Col, InputGroup} from "react-bootstrap";
import loggedIn from './loggedIn'

class Signup extends Component {
  constructor(props) {
    super(props);
    this.state = {
      validated: '',
      setValidated: '',
    };
  }
  handleSubmit = (event) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    }
    event.preventDefault();
    fetch('http://192.168.1.158:1010/signup?username=' + form.elements.validationCustomUsername.value
      + '&password=' + form.elements.validationPassword.value
      + '&name=' + form.elements.validationName.value
      + '&email=' + form.elements.validationEmail.value
      + '&genre=' + form.elements.validationGenre.value
      + '&zip_code=' + form.elements.validationZIP.value
      + '&admin=' + form.elements.adminStatus.checked, {
      method: 'GET'
    }).then(response => {
        if(response.status === 200) {
          this.props.history.push('/');
        }
      });
    this.setState({setValidated: true});
  };

  componentDidMount() {
    if (loggedIn()) {
      this.props.history.push('/Movies');
    }
  }

  render() {
    return (
      <>
        <div style={{paddingTop: '3vh'}}/>
        <Card style={{width: '30vw', marginLeft: 'auto', marginRight: 'auto'}}>
          <h2 style={{marginLeft: 'auto', marginRight: 'auto', marginTop: '2vh'}}> Sign Up </h2>
          <Form noValidate validated={this.state.validated} onSubmit={(e) => this.handleSubmit(e)} style={{
            width: '28vw',
            marginLeft: 'auto',
            marginRight: 'auto',
            paddingTop: '2vh',
            paddingBottom: '2vh'
          }}>
            <Form.Row>
              <Form.Group as={Col} md="6" name="name" controlId="validationName">
                <Form.Label>Name</Form.Label>
                <Form.Control
                  required
                  type="text"
                  placeholder="Enter name"
                />
                <Form.Control.Feedback></Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="6" name="username" controlId="validationCustomUsername">
                <Form.Label>Username</Form.Label>
                <InputGroup>
                  <InputGroup.Prepend>
                    <InputGroup.Text id="inputGroupPrepend">@</InputGroup.Text>
                  </InputGroup.Prepend>
                  <Form.Control
                    type="text"
                    placeholder="Username"
                    aria-describedby="inputGroupPrepend"
                    required
                  />
                  <Form.Control.Feedback type="invalid">
                    Please choose a username.
                  </Form.Control.Feedback>
                </InputGroup>
              </Form.Group>
            </Form.Row>
            <Form.Row>
              <Form.Group as={Col} md="6" name="email" controlId="validationEmail">
                <Form.Label>Email</Form.Label>
                <Form.Control type="email" placeholder="Email" required/>
                <Form.Control.Feedback type="invalid">
                  Please enter a valid Email
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="6" name="password" controlId="validationPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Password" required/>
                <Form.Control.Feedback type="invalid">
                  Please enter a Password
                </Form.Control.Feedback>
              </Form.Group>
            </Form.Row>
            <Form.Row>
              <Form.Group as={Col} md="6" name="genre" controlId="validationGenre">
                <Form.Label>Favorite Genre</Form.Label>
                <Form.Control type="text" placeholder="Your genre"/>
                <Form.Control.Feedback type="invalid">
                  Please enter a genre
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="6" name="zip" controlId="validationZIP">
                <Form.Label>Zip Code</Form.Label>
                <Form.Control type="text" placeholder="ZIP Code"/>
                <Form.Control.Feedback type="invalid">
                  Please provide a valid zip.
                </Form.Control.Feedback>
              </Form.Group>
            </Form.Row>
            <Form.Group name="adminstatus" controlId="adminStatus">
              <Form.Check
                label="Admin Account"
              />
            </Form.Group>
            <Button type="submit">Sign Up</Button>
          </Form>
        </Card>
      </>
    );
  }
}

export default Signup;

