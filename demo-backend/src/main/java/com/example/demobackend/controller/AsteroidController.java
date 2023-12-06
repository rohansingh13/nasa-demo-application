package com.example.demobackend.controller;


import com.example.demobackend.model.Asteroid;
import com.example.demobackend.service.AsteroidService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/asteroids")
public class AsteroidController {


    private AsteroidService asteroidService;

    public AsteroidController(AsteroidService asteroidService) {
        this.asteroidService = asteroidService;
    }

    @GetMapping("/week")
    public List<Asteroid> getAsteroidsForWeek(@RequestParam String startDate, @RequestParam String endDate) throws IOException, URISyntaxException {
        List<Asteroid> asteroids = asteroidService.getAsteroidsForWeek(startDate, endDate);

        // Sort the asteroids by miss distance
        asteroids.sort(Comparator.comparingDouble(asteroid -> {
            String missDistanceKilometers = asteroid.getMissDistanceKilometers();
            return missDistanceKilometers != null ? Double.parseDouble(missDistanceKilometers) : 0.0;
        }));


        return asteroids;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asteroid> getAsteroidDetails(@PathVariable Long id) {
        try {
            Asteroid asteroid = asteroidService.getAsteroidDetailsById(id);

            if (asteroid != null) {
                return new ResponseEntity<>(asteroid, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
