import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

function AstroidDetailsComponent() {
  const navigate = useNavigate();
  const { id } = useParams();
  const [asteroid, setAsteroid] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:9898/api/asteroids/${id}`);
        setAsteroid(response.data);
      } catch (error) {
        console.error('Error fetching asteroid details:', error);       
        navigate('/error');
      }
    };

    fetchData();
  }, [id, navigate]);

  if (!asteroid) {
    return <div>Loading...</div>;
  }

  return (  
    <div className="container mt-5">
      <h1>Asteroid Details</h1>
      <div className="card">
        <div className="card-body">
          <h5 className="card-title">{asteroid.name}</h5>
          <p className="card-text">Close Approach Date: {asteroid.closeApproachDate}</p>
          <p className="card-text">Miss Distance: {asteroid.missDistanceKilometers} km</p>
          <p className="card-text">Relative Velocity: {asteroid.relativeVelocityKmPerHour} km/h</p>
          <p className="card-text">Potentially Hazardous: {asteroid.potentiallyHazardousAsteroid ? 'Yes' : 'No'}</p>
          <a href={asteroid.nasaJplUrl} className="btn btn-primary">
            NASA JPL Link
          </a>
        </div>
      </div>
    </div>
  );
}

export default AstroidDetailsComponent; 