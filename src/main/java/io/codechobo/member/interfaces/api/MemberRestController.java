package io.codechobo.member.interfaces.api;

import io.codechobo.member.domain.Member;
import io.codechobo.member.domain.repository.MemberRepository;
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
 * @author Loustler
 * @since 8/29/16
 */
@RestController
@RequestMapping("/api/member")
public class MemberRestController {
    @Autowired private MemberRepository memberRepository;

    @RequestMapping(value = {"","/"}, method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody MemberDto member) {
        return new ResponseEntity<>(this.memberRepository.save(new Member(member)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.POST)
    public ResponseEntity show(@PathVariable Long seq) {
        return new ResponseEntity<>(memberRepository.findOne(seq), HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list() {
        return new ResponseEntity<>(this.memberRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{seq}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Long seq, @RequestBody MemberDto memberDto) {
        return new ResponseEntity<>(this.memberRepository.save(new Member(memberDto)), HttpStatus.OK);
    }


}
