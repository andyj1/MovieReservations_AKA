import React, {Component} from 'react';
import {Carousel} from "react-bootstrap";

class Movies extends Component {
  constructor(props) {
    super(props);
    this.state = {
      movies: ["Avatar", "Dragon Ball Z", "Ali and B"]
    };
  }
  showRecentMovies = () => (
      <Carousel bg="dark" variant="dark">
        {this.state.movies.map((movie, index) => (
              <Carousel.Item key={index}>
                <div style={{backgroundColor: '#007bff', height: '40vh', width: '50vw'}}>
                  <Carousel.Caption>
                    <h3>{movie}</h3>
                    <p>{}</p>
                  </Carousel.Caption>
                </div>
              </Carousel.Item>
        ))}
      </Carousel>
  );

  render() {
    return (
      <>
        <div style={{paddingTop: '1vh', width: '50vw', marginLeft: 'auto', marginRight: 'auto'}}>
            {this.showRecentMovies()}
        </div>
      </>
    );
  }
}
export default Movies;