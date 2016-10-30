package io.codechobo.member.interfaces.api;

import io.codechobo.member.application.SocialService;
import io.codechobo.member.domain.support.SocialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author loustler
 * @since 10/24/2016 23:53
 */
@RestController
@RequestMapping("/api/social")
public class SocialRestController {
    @Autowired
    private SocialService socialService;

    @RequestMapping(value = "/{memberSeq}", method = RequestMethod.GET)
    public ResponseEntity show(@PathVariable Long memberSeq) {
        return new ResponseEntity(socialService.getSocials(memberSeq), HttpStatus.OK);
    }

    @RequestMapping(value = "/one/{socialSeq}", method = RequestMethod.GET)
    public ResponseEntity one(@PathVariable Long socialSeq) {
        return new ResponseEntity(socialService.getSocial(socialSeq), HttpStatus.OK);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody SocialDto socialDto) {
        return new ResponseEntity(socialService.createSocial(socialDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "{seq}", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody SocialDto socialDto, @PathVariable Long seq) {
        socialDto.setSequence(seq);
        return new ResponseEntity(socialService.updateSocial(socialDto), HttpStatus.OK);
    }

    @RequestMapping(value = "{seq}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long seq) {
        socialService.deleteSocial(seq);
        return new ResponseEntity(HttpStatus.OK);
    }
}
