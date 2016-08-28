package io.codechobo.theater.interfaces.api;

import io.codechobo.theater.domain.Theater;
import io.codechobo.theater.domain.repository.TheaterRepository;
import io.codechobo.theater.domain.support.TheaterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theaters")
public class TheaterRestController {

    @Autowired
    private TheaterRepository theaterRepository;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody TheaterDto theaterDto) {
        return new ResponseEntity<>(theaterRepository.save(new Theater(theaterDto)), HttpStatus.CREATED);
    }

    @RequestMapping(value = {"", "/"})
    public ResponseEntity list() {
        return new ResponseEntity<>(theaterRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}"})
    public ResponseEntity show(@PathVariable Long id) {
        return new ResponseEntity<>(theaterRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Long id, @RequestBody TheaterDto theaterDto) {
        return new ResponseEntity<>(theaterRepository.save(new Theater(theaterDto)), HttpStatus.OK);
    }
}
