package com.imoovo.boundary;

import com.imoovo.business.entity.Distance;
import com.imoovo.business.entity.FindDistanceException;
import com.imoovo.business.service.DistanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/distance/", description = "Current distance", tags = {"CurrentDistance"})
@RestController
@RequestMapping("/calculation")
public class DistanceResource {
  private DistanceService distanceService;

  @Autowired
  public DistanceResource(DistanceService distanceService) {
    this.distanceService = distanceService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Created", response = Distance.class),
      @ApiResponse(code = 500, message = "Internal server error")
  })
  public ResponseEntity<?> add(@RequestBody(required = false) Distance distance) {
    try {
      distanceService.savePoints(distance);
      Double distanceBetweenTwoPoints = distanceService.calculate(distance);
      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.setLocation(URI.create(String.format("/distance/%d", distance.getId())));
      return new ResponseEntity<>(distanceBetweenTwoPoints, responseHeaders, HttpStatus.CREATED);
    } catch (Exception e) {
      return getResponseForError("Internal server error");
    }
  }

  @GetMapping("/{distanceId}")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Distance.class),
      @ApiResponse(code = 204, message = "No content"),
      @ApiResponse(code = 500, message = "Internal server error"),
  })
  public ResponseEntity<?> getDistance(@PathVariable("distanceId") Long distanceId) {
    try {
      Distance distance = distanceService.getDistanceById(distanceId);
      return new ResponseEntity<>(distance, HttpStatus.OK);
    } catch (FindDistanceException e) {
      return getResponseForNoContent(String.format("Cannot find distance details with id: %d", distanceId));
    } catch (Exception e) {
      return getResponseForError("Internal server error");
    }
  }

  @GetMapping("/all")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK", response = Distance.class),
      @ApiResponse(code = 500, message = "Internal server error"),
  })
  public ResponseEntity<?> getAll() {
    try {
      List<Distance> allDistances = distanceService.getAllDistances();
      return new ResponseEntity<>(allDistances, HttpStatus.OK);
    } catch (Exception e) {
      return getResponseForError("Internal server error");
    }
  }

  private ResponseEntity<String> getResponseForError(String message) {
    return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<String> getResponseForNoContent(String message) {
    return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
  }
}
