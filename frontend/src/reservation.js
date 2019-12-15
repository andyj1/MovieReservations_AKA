import React, {Component} from 'react';
import {Jumbotron, Container, Row, Col, Button} from "react-bootstrap";

class Reservation extends Component {
    constructor(props) {
        super(props);
        this.state = {
            movie: '',
            theater: '',
            date: '',
            time: '',
            seats: [1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 15, 17, 18, 20, 21, 25, 28, 30],
            seat: [],
        };
    }
    createSeatIndex = () => {
        console.log(this.state.seats);
        let seat = new Array(30).fill(0);
        console.log(seat);
        this.state.seats.map((seatid) => (
          seatid < 31 ? seat[seatid - 1] = 1 : console.log('Invalid Index' + seatid)
        ));
        return seat;
        /*
        while (seat.length) {
            arrange_seats.push(seat.splice(0, 5));
        }
        */
    };

    static getDerivedStateFromProps(prevProps, prevState) {
        if (prevProps.location.state == null) {
            prevProps.history.replace('/Movies')
            return null;
        } else {
            let seat = new Array(30).fill(0);
            prevState.seats.map((seatid) => (
              seatid < 31 ? seat[seatid - 1] = 1 : console.log('Invalid Index' + seatid)
            ));
            var arrange_seats = []
            while (seat.length) {
                arrange_seats.push(seat.splice(0, 5));
            }
            return {
                movie: prevProps.location.state.movie,
                theater: prevProps.location.state.theater,
                time: prevProps.location.state.time,
                date: prevProps.location.state.date,
                seat: arrange_seats
            };
        }

    };

    createSeatGrid = () => (
        <Container>
            {this.state.seat.map((seatRow, index) => (
              <Row key={index + 100}>
                  {seatRow.map((seatState, index2) => (
                    <Col key={index2 + 200}>
                        <div style={{width: '5vw', height: '4vh'}}>
                        {seatState ?
                          <Button variant='primary' size="lg" block>
                              {index * 5 + index2}
                          </Button>
                          : <Button variant='primary' disabled size="lg" block>
                              {index * 5 + index2}
                          </Button>}
                        </div>
                    </Col>
                  ))}
              </Row>
            ))}
        </Container>
    );


    render() {


        return (
            <Jumbotron>
                <h1>{this.state.movie}</h1>
                <h4>{this.state.theater}</h4>
                <h4>{this.state.date + ' ' + this.state.time}</h4>
                {this.createSeatGrid()}
            </Jumbotron>
        );
    }
}

export default Reservation;