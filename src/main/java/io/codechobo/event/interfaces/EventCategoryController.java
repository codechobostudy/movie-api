package io.codechobo.event.interfaces;

import io.codechobo.event.domain.EventCategory;
import io.codechobo.event.domain.repository.EventCategoryRepository;
import io.codechobo.event.interfaces.api.support.EventCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kj Nam
 * @since 2016-09-05
 */
@RestController
@RequestMapping("/api/eventcategories")
public class EventCategoryController {

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    private ResponseEntity save(@RequestBody EventCategoryDto eventCategoryDto) {
        return new ResponseEntity<>(eventCategoryRepository.save(new EventCategory(eventCategoryDto)), HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    private ResponseEntity update(@PathVariable Long id, @RequestBody EventCategoryDto eventCategoryDto) {
        return new ResponseEntity<>(eventCategoryRepository.save(new EventCategory(eventCategoryDto)), HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    private ResponseEntity view(@PathVariable Long id) {
        return new ResponseEntity<>(eventCategoryRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    private ResponseEntity delete(@PathVariable Long id) {
        // TODO 삭제 요청에 대한 처리. 리다이렉트
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
