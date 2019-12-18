import React, {Component} from 'react';
import {Accordion, Card, Jumbotron, Tab, Tabs} from "react-bootstrap";

class UserReservations extends Component {
    constructor(props) {
        super(props);
        this.state = {
            movies: ['Test'],
            dates: ['2019-12-10'],
            times: ['9:00'],
            reserve_theaters: ['Test Theater'],
            seats: [[1,2,3]],
            types: ['Digital'],
            food_id: ['soda_1'],
            food_count: [5],
            fname: ['Test'],
            fdate: ['2019-12-10'],
            ftime: ['9:00'],
            ftheater: ['Test Theater'],
        }
    };

    static getDerivedStateFromProps(prevProps, prevState) {
        return (prevProps.location.state)
    }

    showFoods = () => (
        <Accordion>
            {this.state.food_id.map((item, index) => (
                <Card>
                    <Accordion.Toggle as={Card.Header} eventKey={index.toString()}>
                        {this.state.food_count[index] + ' ' + item + ' @ ' + this.state.ftheater[index]}
                    </Accordion.Toggle>
                    <Accordion.Collapse eventKey={index.toString()}>
                        <div style={{marginLeft: '2vw', marginTop: '2vh', marginBottom: '2vh'}}>
                            <Card.Title>
                                {this.state.fname[index]}
                            </Card.Title>
                            <Card.Subtitle>
                                {this.state.dates[index] + ' ' + this.state.times[index]}
                            </Card.Subtitle>
                            <Card.Text style={{marginTop: '0.5vh'}}>
                                {this.state.types[index]}
                            </Card.Text>
                        </div>
                    </Accordion.Collapse>
                </Card>
            ))}
        </Accordion>
    );

    showShowtimes = () => (
        <Accordion>
            {this.state.movies.map((movie, index) => (
                <Card>
                    <Accordion.Toggle as={Card.Header} eventKey={index.toString()}>
                        {movie + ' @ ' + this.state.reserve_theaters[index]}
                    </Accordion.Toggle>
                    <Accordion.Collapse eventKey={index.toString()}>
                        <div style={{marginLeft: '2vw', marginTop: '2vh', marginBottom: '2vh'}}>
                            <Card.Title>
                                {movie}
                            </Card.Title>
                            <Card.Subtitle>
                                {this.state.dates[index] + ' ' + this.state.times[index]}
                            </Card.Subtitle>
                            <Card.Text style={{marginTop: '0.5vh'}}>
                                {this.state.types[index]}
                            </Card.Text>
                            <Card.Text style={{marginTop: '-1vh'}}>
                                {this.state.seats[index].toString()}
                            </Card.Text>
                        </div>
                    </Accordion.Collapse>
                </Card>
            ))}
        </Accordion>
    );
    render() {
        return (
                <>
                    <Jumbotron>
                        <Tabs defaultActiveKey="shows" id="uncontrolled-tab-example">
                            <Tab eventKey="shows" title="Shows Reserved">
                                <Card style={{paddingTop: '2vh', paddingBottom: '2vh', paddingRight: '2vw', paddingLeft: '1vw'}}>
                                    {this.showShowtimes()}
                                </Card>
                            </Tab>
                            <Tab eventKey="foods" title="Food Reserved">
                                <Card style={{paddingTop: '2vh', paddingBottom: '2vh', paddingRight: '2vw', paddingLeft: '1vw'}}>
                                    {this.showFoods()}
                                </Card>
                            </Tab>
                        </Tabs>
                    </Jumbotron>
                </>
            )

    };
}

export default UserReservations;