package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Camada serviços.
 */
@Service
public class MuseumService implements MuseumServiceInterface {


  private final MuseumFakeDatabase database;

  @Autowired
  public MuseumService(MuseumFakeDatabase database) {
    this.database = database;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    boolean isValid = CoordinateUtil.isCoordinateValid(coordinate);
    if (!isValid) {
      throw new InvalidCoordinateException();
    }
    Optional<Museum> returnedMuseum = database.getClosestMuseum(coordinate, maxDistance);
    if (returnedMuseum.isEmpty()) {
      throw new MuseumNotFoundException();
    }
    return returnedMuseum.get();
  }

  @Override
  public Museum createMuseum(Museum museum) {
    boolean isValid = CoordinateUtil.isCoordinateValid(museum.getCoordinate());
    if (!isValid) {
      throw new InvalidCoordinateException();
    }
    return database.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    Optional<Museum> returnedMuseum = database.getMuseum(id);
    if (returnedMuseum.isEmpty()) {
      throw new MuseumNotFoundException();
    }
    return returnedMuseum.get();
  }
}
