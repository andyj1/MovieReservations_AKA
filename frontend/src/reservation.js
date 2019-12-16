import React, {Component} from 'react';
import {Jumbotron, Container, Row, Col, Button, Form} from "react-bootstrap";

class Reservation extends Component {
    constructor(props) {
        super(props);
        this.state = {
            movie: '',
            theater: '',
            date: '',
            time: '',
            status: false,
            seats: [1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 15, 17, 18, 20, 21, 25, 28, 30],
            seat: [],
            seat1d: []
        };
    }
    createSeatIndex = () => {
        let seat = new Array(30).fill(0);
        console.log(seat);
        this.state.seats.map((seatid) => (
          seatid < 31 ? seat[seatid - 1] = 1 : console.log('Invalid Index' + seatid)
        ));
        return seat;
    };

    static getDerivedStateFromProps(prevProps, prevState) {
        if (prevProps.location.state == null) {
            prevProps.history.replace('/Movies')
            return null;
        } else if (prevState.seat1d.length === 0) {
            let seat = new Array(30).fill(0);
            prevProps.location.state.seats.map((seatid) => (
              seatid < 31 ? seat[seatid - 1] = 1 : console.log('Invalid Index' + seatid)
            ));
            var arrange_seats = [];
            var temp = [...seat];
            while (seat.length) {
                arrange_seats.push(seat.splice(0, 5));
            }
            return {
                movie: prevProps.location.state.movie,
                theater: prevProps.location.state.theater,
                time: prevProps.location.state.time,
                date: prevProps.location.state.date,
                seat: arrange_seats,
                seat1d: temp,
            };
        } else {
            return {
                seat: prevState.seat
            }
        }

    };

    handleSeatClick = (event, index) => {
      var reserved_seats = this.state.seat1d.reduce(function(a, e, i) {
          if (e === 2)
              a.push(i);
          return a;
      }, []);
      if (reserved_seats instanceof Array && reserved_seats.includes(index)) {
          var temp = this.state.seat1d;
          temp[index] = 1;
      } else {
          var temp = this.state.seat1d;
          temp[index] = 2;
      }
      var arrange_seats = [];
      var seat = [...temp];
      while (seat.length) {
        arrange_seats.push(seat.splice(0, 5));
      }
      this.setState({
          seat1d: temp,
          seat: arrange_seats
      });
    };

    handleSeatReserve = (event) => (
      this.setState({status: true})
    );

    createSeatGrid = () => (
      <>
        <Container style={{width: '25vw', height: '20vh'}}>
            {this.state.seat.map((seatRow, index) => (
              <Row key={index + 100}>
                  {seatRow.map((seatState, index2) => (
                    <Col key={index2 + 200} style={{paddingLeft: '0', paddingRight: '0' ,paddingTop: '0.95vh', paddingBottom: '0.95vh', marginLeft: 'auto', marginRight: 'auto', marginTop: 'auto', marginBottom: 'auto'}}>
                        <div style={{width: '5vw', height: '4vh'}}>
                        {seatState === 1 ?
                          <Button onClick={(e) => this.handleSeatClick(e, index * 5 + index2)} variant='primary' block>
                              <div style={{paddingLeft: '0', paddingRight: '0' ,paddingTop: '0.95vh', paddingBottom: '0.95vh', marginLeft: 'auto', marginRight: 'auto', marginTop: 'auto', marginBottom: 'auto'}}>
                                {index * 5 + index2}
                              </div>
                          </Button>
                          : seatState === 2 ?
                            <Button onClick={(e) => this.handleSeatClick(e, index * 5 + index2)} variant="outline-primary" block>
                                <div style={{paddingLeft: '0', paddingRight: '0' ,paddingTop: '0.95vh', paddingBottom: '0.95vh', marginLeft: 'auto', marginRight: 'auto', marginTop: 'auto', marginBottom: 'auto'}}>
                                    {index * 5 + index2}
                                </div>
                            </Button>
                            : <Button variant='primary' disabled  block>
                              <div style={{paddingLeft: '0', paddingRight: '0' ,paddingTop: '0.95vh', paddingBottom: '0.95vh', marginLeft: 'auto', marginRight: 'auto', marginTop: 'auto', marginBottom: 'auto'}}>
                                  {index * 5 + index2}
                              </div>
                          </Button>}
                        </div>
                    </Col>
                  ))}
              </Row>
            ))}
            <Row>
                <Button onClick={(e) => this.handleSeatReserve(e)} type="submit" style={{marginLeft: 'auto', marginRight: 'auto', marginTop: '5vh'}}>
                    <div style={{paddingLeft: '0', paddingRight: '0' ,paddingTop: '0.95vh', paddingBottom: '0.95vh', marginLeft: 'auto', marginRight: 'auto', marginTop: 'auto', marginBottom: 'auto'}}>
                        Continue
                    </div>
                </Button>
            </Row>
        </Container>

      </>
    );

    handleSubmit = (event) => {
        event.preventDefault();
        const form = event.currentTarget;
        let reserved_seats = this.state.seat1d.reduce(function(a, e, i) {
            if (e === 2)
                a.push(i+1);
            return a;
        }, []);
        console.log(this.state.theater);
        console.log(this.state.date);
        console.log(this.state.movie);
        console.log(this.state.time);
        console.log(form.elements.popcornL.value);
        console.log(form.elements.popcornS.value);
        console.log(form.elements.soda.value);

        console.log(reserved_seats.toString());
        fetch('http://192.168.1.158:1010/seats?theater_name=' + this.state.theater
            + '&date=' + this.state.date
            + '&movie_name=' + this.state.movie
            + '&time=' + this.state.time
            + '&seats=' + reserved_seats.toString()
            + '&username=' + localStorage.getItem('username'))
          .then(response => console.log(response));


    };


    showFoods = () => (
        <>
            <Form onSubmit={(e) => this.handleSubmit(e)}>
                <Form.Group as={Row} controlId="popcornL">
                    <Form.Label column sm={2}>
                        Popcorn Large
                    </Form.Label>
                    <Col sm={10}>
                        <Form.Control type="number" placeholder="Enter Amount"/>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="popcornS">
                    <Form.Label column sm={2}>
                        Popcorn Small
                    </Form.Label>
                    <Col sm={10}>
                        <Form.Control type="number" placeholder="Enter Amount"/>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="Soda">
                    <Form.Label column sm={2}>
                        Soda
                    </Form.Label>
                    <Col sm={10}>
                        <Form.Control type="number" placeholder="Enter Amount"/>
                    </Col>
                </Form.Group>
                <Row>
                    <Button type="submit" style={{marginLeft: 'auto', marginRight: 'auto', marginTop: '2vh'}}>
                        <div style={{paddingLeft: '0', paddingRight: '0' ,paddingTop: '0.5vh', paddingBottom: '0.5vh', marginLeft: 'auto', marginRight: 'auto', marginTop: 'auto', marginBottom: 'auto'}}>
                            Reserve
                        </div>
                    </Button>
                </Row>
            </Form>

        </>
    );


    render() {


        return (
            <Jumbotron style={{height: '70vh'}}>
                <h1>{this.state.movie}</h1>
                <h4>{this.state.theater}</h4>
                <h4>{this.state.date + ' ' + this.state.time}</h4>
                {this.state.status ? this.showFoods() : this.createSeatGrid()}
            </Jumbotron>
        );
    }
}

export default Reservation;