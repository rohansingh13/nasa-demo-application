package com.example.demobackend.controller;

import com.example.demobackend.model.Asteroid;
import com.example.demobackend.service.AsteroidService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
public class AsteroidControllerTest {

    @Mock
    private AsteroidService asteroidService;

    @InjectMocks
    private AsteroidController asteroidController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAsteroidsForWeek_shouldReturnAsteroids() throws IOException, URISyntaxException {
        List<Asteroid> expectedAsteroids = Arrays.asList(createMockAsteroid(234567L, "Asteroid1"),
                createMockAsteroid(342346L, "Asteroid2"));
        when(asteroidService.getAsteroidsForWeek(anyString(), anyString())).thenReturn(expectedAsteroids);

        List<Asteroid> result = asteroidController.getAsteroidsForWeek("startDate", "endDate");

        verify(asteroidService, times(1)).getAsteroidsForWeek(anyString(), anyString());

        assertEquals(expectedAsteroids, result);
    }

    @Test
    void getAsteroidDetails_shouldReturnAsteroidDetails() throws IOException {
        Asteroid expectedAsteroid = createMockAsteroid(123456L, "Asteroid1");
        when(asteroidService.getAsteroidDetailsById(anyLong())).thenReturn(expectedAsteroid);

        ResponseEntity<Asteroid> result = asteroidController.getAsteroidDetails(1L);

        verify(asteroidService, times(1)).getAsteroidDetailsById(anyLong());

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedAsteroid, result.getBody());
    }

    @Test
    void getAsteroidDetails_shouldReturnNotFound() throws IOException {
        when(asteroidService.getAsteroidDetailsById(anyLong())).thenReturn(null);

        ResponseEntity<Asteroid> result = asteroidController.getAsteroidDetails(1L);

        verify(asteroidService, times(1)).getAsteroidDetailsById(anyLong());

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void getAsteroidDetails_shouldReturnInternalServerError() throws IOException {
        when(asteroidService.getAsteroidDetailsById(anyLong())).thenThrow(new RuntimeException("Test Exception"));

        ResponseEntity<Asteroid> result = asteroidController.getAsteroidDetails(1L);

        verify(asteroidService, times(1)).getAsteroidDetailsById(anyLong());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

    private Asteroid createMockAsteroid(Long id, String name) {
        Asteroid asteroid = new Asteroid();
        asteroid.setId(id);
        asteroid.setName(name);
        return asteroid;
    }
}
