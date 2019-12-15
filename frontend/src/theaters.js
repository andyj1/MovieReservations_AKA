import React, { Component } from 'react';
import {Accordion, Card} from "react-bootstrap";

class Theaters extends Component{
  constructor(props) {
    super(props);
    this.state = {
      theaters: [{"theater_id":"AMC Loews 19th St. East 6","theater_address":["890 Broadway","New York","NY","10003"],"theater_phone":"(888) 262-4386","admin_id":""}]
    };
  }
  componentDidMount() {
    fetch('http://192.168.1.158:1010/theaters', {
      method: 'GET',
    }).then(response => response.json())
      .then(data => {
        this.setState({theaters: data});
      });
  }

  redirectTheater = (event, theater_name) => {
    this.props.history.push('/theater/' + theater_name);
  };

  showNearbyTheaters = () => (
      <Accordion>
      {this.state.theaters.map((theater, index) => (
          <Card>
            <Accordion.Toggle as={Card.Header} eventKey={index.toString()}>
              {theater['theater_id']}
            </Accordion.Toggle>
            <Accordion.Collapse eventKey={index.toString()}>
              <div style={{marginLeft: '2vw', marginTop: '2vh', marginBottom: '2vh'}}>
                <Card.Title onClick={(e) => this.redirectTheater(e, theater['theater_id'])}>
                  {theater['theater_id']}
                </Card.Title>
                <Card.Text>
                  {theater['theater_address'][0] + '\n'}
                  {theater['theater_address'][1] + ', ' + theater['theater_address'][2] + ' ' + theater['theater_address'][3]}
                </Card.Text>
                <Card.Text style={{marginTop: '-1vh'}}>
                  {theater['theater_phone']}
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
          <div style={{paddingTop: '1vh', width: '50vw', marginLeft: 'auto', marginRight: 'auto'}}>
            {this.showNearbyTheaters()}
          </div>
        </>
    );
  }
}

export default Theaters;