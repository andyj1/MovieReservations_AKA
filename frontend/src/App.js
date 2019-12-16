import React, {Component} from 'react';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Navbar, Nav, Form, FormControl, Button } from 'react-bootstrap';

import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import Signin from './signin'
import Signup from './signup'
import Movies from './movies'
import Movie from './movie'
import Theaters from './theaters'
import Theater from './theater'
import Reservation from './reservation'
import Profile from './Profile'

class App extends Component {
  render() {
    return (
        <>
          <Navbar bg="primary" variant="dark">
            <Navbar.Brand href="/">AKA</Navbar.Brand>
            <Nav className="mr-auto">
              <Nav.Link href="/">Home</Nav.Link>
              <Nav.Link href="/Movies">Movies</Nav.Link>
              <Nav.Link href="/Theaters">Theaters</Nav.Link>
            </Nav>
          </Navbar>
          <Router>
            <Switch>
              <Route exact path="/" component={Signin} />
              <Route exact path="/Movies" component={Movies} />
              <Route exact path="/Theaters" component={Theaters} />
              <Route exact path="/Movie/:movie" component={Movie} />
              <Route exact path="/Theater/:theater" component={Theater}/>
              <Route exact path="/Reservation" component={Reservation} />
              <Route exact path="/Signup" component={Signup} />
              <Route exact path="/Profile" component={Profile} />
            </Switch>
          </Router>
        </>
    );
  }
}

export default App;
