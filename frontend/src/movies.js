import React, {Component} from 'react';
import {Carousel, Spinner} from "react-bootstrap";
import key from '../secrets/key'


class Movies extends Component {
  constructor(props) {
    super(props);
    this.state = {
      movies: ["Avatar", "Dragon Ball Z", "Ali and Bollywood"],
      img_link: {
        'test': ''
      },
      img_received: 0,
    };
  }

  redirectMovie = (evemt, movie) => (
      this.props.history.push('/movie/' + movie)
  );

  componentDidMount() {
    fetch('http://192.168.1.158:1010/movies')
      .then(response => response.json())
      .then(data => {
        var img_links = {};
        var movies = [...data];
        data.map((movie, index) => {
          fetch('https://www.googleapis.com/customsearch/v1?cx=010013580991372353059:ljv0pdmangk&searchType=image&num=1&key=' + key + '&q=' + encodeURI(movie + ' movie'), {
            method: 'GET',
          }).then(response => response.json())
              .then(data => {
                  console.log(data);
                img_links[movies[index]] = data.items[0]['link'];
                this.setState({img_link: img_links});
              });
        });
        this.setState({movies: data});
      });
  }

  showRecentMovies = () => (
      <Carousel bg="dark" variant="dark">
        {this.state.movies.map((movie, index) => (
                <Carousel.Item key={index}>
                  <div style={{backgroundColor: '#007bff', height: '40vh', width: '50vw'}}>
                        <img
                            src={this.state.img_link[movie]}
                            alt="Third slide"
                            style={{
                              height: '40vh',
                              width: '50vw',
                            }}
                        />
                    <Carousel.Caption>
                      <h3 onClick={(e) => {this.redirectMovie(e, movie)}}>{movie}</h3>
                      <p>{}</p>
                    </Carousel.Caption>
                  </div>
                </Carousel.Item>
          )) }
      </Carousel>
  );

  render() {
    return (
        Object.keys(this.state.img_link).length === this.state.movies.length ?
      <>
        <div style={{paddingTop: '1vh', width: '50vw', marginLeft: 'auto', marginRight: 'auto'}}>
            {this.showRecentMovies()}
        </div>
      </>
              :
              <>
                <div style={{paddingTop: '1vh', width: '50vw', marginLeft: 'auto', marginRight: 'auto'}}>
                </div>
              </>
    );
  }
}
export default Movies;