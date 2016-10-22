package io.codechobo.member.interfaces.api;

import io.codechobo.member.application.MemberService;
import io.codechobo.member.domain.support.MemberDto;
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
 * @since 10/02/2016 10:17
 */
@RestController
@RequestMapping("/api/member")
public class MemberRestController {
    @Autowired
    MemberService memberService;

    @RequestMapping(value = {"","/"}, method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody MemberDto memberDto) {
        return new ResponseEntity(memberService.createMember(memberDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ResponseEntity show() {
        return new ResponseEntity(memberService.getMembers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.GET)
    public ResponseEntity show(@PathVariable Long seq) {
        return new ResponseEntity(memberService.getMember(seq), HttpStatus.OK);
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Long seq, @RequestBody MemberDto memberDto) {
        memberDto.setSequence(seq);
        return new ResponseEntity(memberService.updateMember(memberDto), HttpStatus.OK);
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long seq) {
        memberService.deleteMember(seq);
        return new ResponseEntity(HttpStatus.OK);
    }
}
