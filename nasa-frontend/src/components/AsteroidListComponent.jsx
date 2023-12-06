import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

class AsteroidListComponent extends Component {
  constructor(props) {
    super(props)

    this.state = {
      asteroids : []
    }
  }

  componentDidMount() {
    this.fetchData();
  }

  fetchData = async () => {
    try {
      const response = await axios.get(
        'http://localhost:9898/api/asteroids/week?startDate=2015-09-07&endDate=2015-09-08'
      );
      this.setState({ asteroids: response.data });
    } catch (error) {
      console.error('Error fetching asteroid data:', error);
    }
  };

  render() {
    const { asteroids } = this.state;
    return (    
      <div className="container mt-5">
        <h1 className="mb-4">Asteroids Landing Page</h1>
        <div className="row">
          {asteroids.map((asteroid) => (
            <div key={asteroid.id} className="col-md-4 mb-4">
              <div className={`card ${asteroid.potentiallyHazardousAsteroid ? 'border-danger' : ''}`}>
                <div className="card-body">
                  <h5 className="card-title">{asteroid.name}</h5>
                  <p className="card-text">Close Approach Date: {asteroid.closeApproachDate}</p>
                  <p className="card-text">Miss Distance: {asteroid.missDistanceKilometers} km</p>
                  <p className="card-text">
                    Potentially Hazardous: {asteroid.potentiallyHazardousAsteroid ? 'Yes' : 'No'}
                  </p>
                  <Link to={`/asteroids/${asteroid.id}`} className="btn btn-primary">
                    View Details
                  </Link>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }
}

export default AsteroidListComponent;