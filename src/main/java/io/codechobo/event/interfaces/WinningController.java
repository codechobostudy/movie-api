package io.codechobo.event.interfaces;

import io.codechobo.event.application.WinningService;
import io.codechobo.event.domain.repository.WinningRepository;
import io.codechobo.event.interfaces.api.support.WinningDto;
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
 * @since 2016-09-10
 */
@RestController
@RequestMapping("/api/wins")
public class WinningController {

    @Autowired
    WinningRepository winningRepository;

    @Autowired
    WinningService winningService;

    @RequestMapping(value = "/events/{eventId}/joins/{joinId}", method = RequestMethod.POST)
    public ResponseEntity addWinning(@PathVariable Long eventId,
                                     @PathVariable Long joinId,
                                     @RequestBody WinningDto winningDto) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/events/{eventId}", method = RequestMethod.GET)
    public ResponseEntity list(@PathVariable Long eventId) {
        return new ResponseEntity(winningRepository.findByEventId(eventId), HttpStatus.OK);
    }
}
