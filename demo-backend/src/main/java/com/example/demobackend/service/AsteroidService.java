package com.example.demobackend.service;

import com.example.demobackend.external.nasa.model.CloseApproachData;
import com.example.demobackend.external.nasa.model.MissDistance;
import com.example.demobackend.external.nasa.model.NasaAsteroidInfo;
import com.example.demobackend.external.nasa.model.NasaNeoFeedResponse;
import com.example.demobackend.external.nasa.model.RelativeVelocity;
import com.example.demobackend.external.nasa.service.NasaApiService;
import com.example.demobackend.model.Asteroid;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AsteroidService {

    private final NasaApiService nasaApiService;

    @Autowired
    public AsteroidService(NasaApiService nasaApiService) {
        this.nasaApiService = nasaApiService;
    }

    public List<Asteroid> getAsteroidsForWeek(String startDate, String endDate) throws IOException, URISyntaxException {
        String apiResponse = nasaApiService.getAsteroidsForWeek(startDate, endDate);
        NasaNeoFeedResponse nasaNeoFeedResponse = parseApiResponse(apiResponse);

        if (null == nasaNeoFeedResponse) {
            return new ArrayList<>();
        }

        return convertToAsteroids(nasaNeoFeedResponse);
    }

    public Asteroid getAsteroidDetailsById(Long asteroidId) throws IOException {
        String apiResponse = nasaApiService.getAsteroidDetailsById(asteroidId);

        NasaAsteroidInfo asteroidInfo = parseDetailsApiResponse(apiResponse);

        if (null == asteroidInfo) {
            return null;
        }

        return convertToAsteroid(asteroidInfo);
    }

    private NasaAsteroidInfo parseDetailsApiResponse(String apiResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            NasaAsteroidInfo nasaAsteroidInfoResponse = objectMapper.readValue(apiResponse, NasaAsteroidInfo.class);
            return nasaAsteroidInfoResponse;

        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    private NasaNeoFeedResponse parseApiResponse(String apiResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            NasaNeoFeedResponse nasaNeoFeedResponse = objectMapper.readValue(apiResponse, NasaNeoFeedResponse.class);
            return nasaNeoFeedResponse;

        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public List<Asteroid> convertToAsteroids(NasaNeoFeedResponse nasaNeoFeedResponse) {
        List<Asteroid> asteroids = new ArrayList<>();

        if (nasaNeoFeedResponse != null && nasaNeoFeedResponse.getNear_earth_objects() != null) {
            // Iterate over the map entries, where the key is the date and the value is a list of asteroids for that date
            for (Map.Entry<String, List<NasaAsteroidInfo>> entry : nasaNeoFeedResponse.getNear_earth_objects().entrySet()) {
                List<NasaAsteroidInfo> asteroidsForDate = entry.getValue();

                if (null != asteroidsForDate) {
                    for (NasaAsteroidInfo nasaAsteroidInfo : asteroidsForDate) {
                        Asteroid asteroid = convertToAsteroid(nasaAsteroidInfo);
                        asteroids.add(asteroid);
                    }
                }
            }
        }

        return asteroids;
    }

    private Asteroid convertToAsteroid(NasaAsteroidInfo nasaAsteroidInfo) {
        Asteroid asteroid = new Asteroid();
        asteroid.setId(nasaAsteroidInfo.getId());
        asteroid.setName(nasaAsteroidInfo.getName());
        asteroid.setNasaJplUrl(nasaAsteroidInfo.getNasa_jpl_url());
        asteroid.setAbsoluteMagnitude(nasaAsteroidInfo.getAbsolute_magnitude_h());

        if (nasaAsteroidInfo.getClose_approach_data() != null && !nasaAsteroidInfo.getClose_approach_data().isEmpty()) {
            CloseApproachData firstCloseApproachData = nasaAsteroidInfo.getClose_approach_data().get(0);
            MissDistance missDistance = firstCloseApproachData.getMiss_distance();
            if (missDistance != null) {
                asteroid.setMissDistanceKilometers(missDistance.getKilometers());
                asteroid.setMissDistanceMiles(missDistance.getMiles());
                asteroid.setPotentiallyHazardousAsteroid(nasaAsteroidInfo.isIs_potentially_hazardous_asteroid());
            }
        }

        if (nasaAsteroidInfo.getEstimated_diameter() != null && nasaAsteroidInfo.getEstimated_diameter().getKilometers() != null) {
            asteroid.setEstimatedDiameterMin(nasaAsteroidInfo.getEstimated_diameter().getKilometers().getEstimated_diameter_min());
            asteroid.setEstimatedDiameterMax(nasaAsteroidInfo.getEstimated_diameter().getKilometers().getEstimated_diameter_max());
        }

        if (nasaAsteroidInfo.getClose_approach_data() != null && !nasaAsteroidInfo.getClose_approach_data().isEmpty()) {
            CloseApproachData firstCloseApproachData = nasaAsteroidInfo.getClose_approach_data().get(0);
            asteroid.setCloseApproachDate(firstCloseApproachData.getClose_approach_date());

            RelativeVelocity relativeVelocity = firstCloseApproachData.getRelative_velocity();
            if (relativeVelocity != null) {
                asteroid.setRelativeVelocityKmPerSec(relativeVelocity.getKilometers_per_hour());
                asteroid.setRelativeVelocityKmPerHour(relativeVelocity.getKilometers_per_hour());
                asteroid.setRelativeVelocityMilesPerHour(relativeVelocity.getMiles_per_hour());
            }

            MissDistance missDistance = firstCloseApproachData.getMiss_distance();
            if (missDistance != null) {
                asteroid.setMissDistanceAstronomical(missDistance.getAstronomical());
                asteroid.setMissDistanceLunar(missDistance.getLunar());
                asteroid.setMissDistanceKilometers(missDistance.getKilometers());
                asteroid.setMissDistanceMiles(missDistance.getMiles());
            }
        }

        return asteroid;
    }

}
