package io.codechobo.event.interfaces;

import io.codechobo.event.application.JoiningService;
import io.codechobo.event.domain.repository.JoiningRepository;
import io.codechobo.event.interfaces.api.support.JoiningDto;
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
 * @since 2016-09-08
 */
@RestController
@RequestMapping("/api/joins")
public class JoiningController {

    @Autowired
    JoiningService joiningService;

    @Autowired
    JoiningRepository joiningRepository;

    @RequestMapping(value = "/events/{eventId}/members/{memberSeq}", method = RequestMethod.POST)
    public ResponseEntity addJoining(@PathVariable Long eventId,
                                     @PathVariable Long memberSeq,
                                     @RequestBody JoiningDto joiningDto) {
        return new ResponseEntity<>(joiningService.save(eventId, memberSeq, joiningDto), HttpStatus.OK);
    }
}
