import React, { Component } from 'react';
import {Carousel, Jumbotron} from "react-bootstrap";


class Theater extends Component {
  constructor(props) {
    super(props);
    this.state = {
      theater: '',
      movies: ['Movie 1', 'Movie 2'],
      date: '2019-12-10',
    }
  }

  componentDidMount() {
    this.setState({theater: this.props.match.params.theater})
    fetch('http://192.168.1.158:1010/showtimes?theater_name=' + this.state.theater
          + '&date=' + this.state.date)
  };

  showRecentMovies = () => (
    <Carousel bg="dark" variant="dark">
      {this.state.movies.map((movie, index) => (
        <Carousel.Item key={index}>
          <div style={{backgroundColor: '#007bff', height: '40vh', width: '50vw'}}>
            <Carousel.Caption>
              <h3 onClick={(e) => {this.redirectMovie(e, movie)}}>{movie}</h3>
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
        <Jumbotron>
          <h1>{this.state.theater}</h1>
        </Jumbotron>
        <div style={{paddingTop: '1vh', width: '50vw', marginLeft: 'auto', marginRight: 'auto'}}>
          {this.showRecentMovies()}
        </div>
      </>
    )
  }
}

export default Theater