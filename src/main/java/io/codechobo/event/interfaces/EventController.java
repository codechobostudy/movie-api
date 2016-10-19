package io.codechobo.event.interfaces;

import io.codechobo.event.domain.Event;
import io.codechobo.event.domain.repository.EventRepository;
import io.codechobo.event.interfaces.api.support.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Kj Nam
 * @since 2016-08-29
 */
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    private ResponseEntity save(@RequestBody @Valid  EventDto eventDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // throw  new IllegalArgumentException(bindingResult.getAllErrors().get(0).getDefaultMessage());
            // FIXME 글로벌 advice 구현시 예외를 던지도록 수정
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(eventRepository.save(new Event(eventDto)), HttpStatus.CREATED);
    }

    @RequestMapping(value = {"", "/"})
    private ResponseEntity list(Pageable pageable) {
        return new ResponseEntity<>(eventRepository.findAll(), HttpStatus.OK);

    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    private ResponseEntity view(@PathVariable Long id) {
        return new ResponseEntity<>(eventRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    private ResponseEntity update(@PathVariable Long id, @RequestBody EventDto eventDto) {
        return new ResponseEntity<>(eventRepository.save(new Event(eventDto)), HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    private ResponseEntity delete(@PathVariable Long id) {
        // TODO 삭제 요청에 대한 처리. 리다이렉트
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
