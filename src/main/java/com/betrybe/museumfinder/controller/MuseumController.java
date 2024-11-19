package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *  Controller do museuns.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {
  private MuseumServiceInterface service;

  @Autowired
  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }

  /**
   *  Controller do museuns.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Museum createMuseum(@RequestBody MuseumDto museumDto) {
    Museum formattedMuseum = ModelDtoConverter.dtoToModel(museumDto);
    service.createMuseum(formattedMuseum);
    return formattedMuseum;
  }

  @GetMapping("/closest")
  @ResponseStatus(HttpStatus.OK)
  public MuseumDto getClosestMuseum(
      @RequestParam Double lat,
      @RequestParam Double lng,
      @RequestParam(name = "max_dist_km") Double maxDistance
  ) {
    Museum returnedMuseum = service.getClosestMuseum(new Coordinate(lat, lng), maxDistance);
    return ModelDtoConverter.modelToDto(returnedMuseum);
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public MuseumDto getMuseum(@PathVariable Long id) {
    Museum museum = service.getMuseum(id);
    return ModelDtoConverter.modelToDto(museum);
  }
}

