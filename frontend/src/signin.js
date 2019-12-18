import React, {Component} from 'react';
import {Button, Card, Col, Form, InputGroup} from "react-bootstrap";
import loggedIn from './loggedIn'

class Signin extends Component {
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
      fetch('http://192.168.1.158:1010/login?username=' + form.elements.validationUsername.value
        + '&password=' + form.elements.validationPassword.value
          + '&token=', {
        method: 'GET'
      }).then(response => (response.json()))
        .then(data => {
          console.log(data)
          if (data === 'login failed.') {
            this.setState({setValidated: false})
          } else {
            this.setState({setValidated: true});
            localStorage.setItem('user_id', data);
            localStorage.setItem('username', form.elements.validationUsername.value);
            this.props.history.push('/Movies');
          }
        })
  };

  componentDidMount() {
    if (loggedIn()) {
      this.props.history.push('/Profile');
    }
  }

  redirectSignup = (e) => (
      this.props.history.push('/signup')
  )

  render() {
    return (
      <>
        <div style={{paddingTop: '3vh'}}/>
        <Card style={{width: '30vw', marginLeft: 'auto', marginRight: 'auto'}}>
          <h2 style={{marginLeft: 'auto', marginRight: 'auto', marginTop: '2vh'}}> Sign In</h2>
          <Form noValidate validated={this.state.validated} onSubmit={(e) => this.handleSubmit(e)} style={{
            width: '28vw',
            marginLeft: 'auto',
            marginRight: 'auto',
            paddingTop: '2vh',
            paddingBottom: '2vh'
          }}>
            <Form.Row>
              <Form.Group as={Col} md="6" name="name" controlId="validationUsername">
                <Form.Label>Name</Form.Label>
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
                    Please enter your Username.
                  </Form.Control.Feedback>
                </InputGroup>
              </Form.Group>
              <Form.Group as={Col} md="6" name="username" controlId="validationPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  required
                  type="password"
                  placeholder="Enter password"
                />
                <Form.Control.Feedback></Form.Control.Feedback>
              </Form.Group>
            </Form.Row>
            <Form.Row>
              <Col md="4"/>
                <Button type="submit">Sign In</Button>
              <Col md="14"/>
               <Button onClick={(e) => this.redirectSignup(e)}>Sign Up</Button>
            </Form.Row>
          </Form>
        </Card>
      </>
    );
  }
}

export default Signin;