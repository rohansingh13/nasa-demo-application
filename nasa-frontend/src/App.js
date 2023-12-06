import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AsteroidListComponent from './components/AsteroidListComponent';
import AstroidDetailsComponent from './components/AstroidDetailsComponent';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<AsteroidListComponent />} exact />
        <Route path="/asteroids/:id" element={< AstroidDetailsComponent />} />
      </Routes>
    </Router>
  );
}

export default App;
