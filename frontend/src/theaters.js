import React, { Component } from 'react';
import {Accordion, Card} from "react-bootstrap";

class Theaters extends Component{
  constructor(props) {
    super(props);
    this.state = {
      theaters: ["AMC west", "AMC north", "AMC east"]
    };
  }

  showNearbyTheaters = () => (
      <Accordion>
        {this.state.theaters.map((theater, index) => (
            <Card>
              <Accordion.Toggle as={Card.Header} eventKey={index.toString()}>
                {theater}
              </Accordion.Toggle>
              <Accordion.Collapse eventKey={index.toString()}>
                <Card.Body>{theater}</Card.Body>
              </Accordion.Collapse>
            </Card>
            )
        )}
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