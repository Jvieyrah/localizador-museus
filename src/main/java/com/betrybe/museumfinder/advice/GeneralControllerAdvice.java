package com.betrybe.museumfinder.advice;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe para manipulação de exceções gerais da aplicação.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  /**
   * Manipula exceções de coordenadas inválidas.
   *
   */
  @ExceptionHandler(InvalidCoordinateException.class)
  public ResponseEntity<String> handleInvalidCoordinateException(
      InvalidCoordinateException exception
  ) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("Coordenada inválida!");
  }

  /**
   * Manipula exceções de museu não encontrado.
   */
  @ExceptionHandler(MuseumNotFoundException.class)
  public ResponseEntity<String> handleMuseumNotFoundException(
      MuseumNotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Museu não encontrado!");
  }

  /**
   * Manipula exceções genéricas.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception exception) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Erro interno!");
  }
}
