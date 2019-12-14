import React from 'react';
import {Button, Card, Form} from "react-bootstrap";


function signup() {
  return (
    <>
    <div style={{paddingTop: '3vh'}}/>
      <Card style={{ width: '20vw', marginLeft: 'auto', marginRight: 'auto'}}>
      <Form style={{ width: '18vw', marginLeft: 'auto', marginRight: 'auto', paddingTop: '2vh', paddingBottom: '2vh'}}>
        <Form.Group controlId="formBasicEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" placeholder="Enter email" />
        </Form.Group>

        <Form.Group controlId="formBasicPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control type="password" placeholder="Password" />
        </Form.Group>
        <Button variant="primary" type="submit">
        Submit
        </Button>
      </Form>
      </Card>
    </>
  );
}

export default signup;

