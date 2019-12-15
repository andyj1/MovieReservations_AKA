import React, {Component} from 'react';
import {Jumbotron, Button, Accordion, Card, ButtonToolbar, DropdownButton, Dropdown} from "react-bootstrap";
import loggedIn from './loggedIn'


class Movie extends Component {
    constructor(props) {
        super(props);
        this.state = {
            movie: "",
            description: "Movie about some thing with someone",
            theaters: ["AMC West", "AMC East", "AMC Weast"],
            movie_time: {
                "AMC West": ["5:45 PM", "5:55 PM", "5:30 PM"],
                "AMC East": ["2:45 PM", "5:55 PM", "5:30 PM"],
                "AMC Weast": ["3:45 PM", "5:55 PM", "5:30 PM"]
            },
            dates: ["12/11/2019", "12/12/2019", "12/13/2019"],
            date: "12/11/2019"
        };
    }
    componentDidMount() {
        this.setState({movie: this.props.match.params.movie})
    };

    showReservation = (event, theater, time) => (
        loggedIn() ? this.props.history.push('/reservation', {theater: theater, time: time, date: this.state.date, movie: this.state.movie}) : this.props.history.replace('/')
    );

    changeDate = (event, date) => (
        // Add query to change state of movie times
        this.setState({date: date})
    );

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

    render() {
        return(
        <>
            <Jumbotron>
                <h1>{this.state.movie}</h1>
                <p>
                    {this.state.description}
                </p>
                <p>
                    <Button variant="primary">Learn more</Button>
                </p>
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