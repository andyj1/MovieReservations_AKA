import React, {Component} from 'react';
import {BrowserRouter as Router,  Switch, Route, Link} from "react-router-dom";
import { Navbar, Nav, Form, FormControl, Button, Card } from 'react-bootstrap';

import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import signup from './signup'
import Movies from './movies'
import Theaters from './theaters'

class App extends Component {
  render() {
    return (
        <>
          <Navbar bg="primary" variant="dark">
            <Navbar.Brand href="#home">AKA</Navbar.Brand>
            <Nav className="mr-auto">
              <Nav.Link href="/">Home</Nav.Link>
              <Nav.Link href="Movies">Movies</Nav.Link>
              <Nav.Link href="Theaters">Theaters</Nav.Link>
            </Nav>
            <Form inline>
              <FormControl type="text" placeholder="Search" className="mr-sm-2" />
              <Button variant="outline-light">Search</Button>
            </Form>
          </Navbar>
          <Router>
            <Switch>
              <Route exact path="/" component={signup} />
              <Route exact path="/Movies" component={Movies} />
              <Route exact path="/Theaters" component={Theaters} />
            </Switch>
          </Router>
        </>
    );
  }
}

export default App;
