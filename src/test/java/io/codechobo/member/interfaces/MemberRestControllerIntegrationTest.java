package io.codechobo.member.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobo.member.application.MemberService;
import io.codechobo.member.domain.support.MemberDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author loustler
 * @since 10/02/2016 10:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(profiles = "test")
public class MemberRestControllerIntegrationTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private MemberService memberService;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void 설정_테스트_config_test() {}

    @Test
    public void 멤버_등록_create_member_via_create() throws Exception{
        MemberDto memberDto = new MemberDto.Builder().id("id").password("password").email("email@gmail.com").nickName("닉네임").build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(memberDto);
        System.out.println(json);
        mvc.perform(post("/api/member")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    @Test
    public void 멤버_가져오기_get_member() throws Exception {
        MemberDto memberDto = new MemberDto.Builder().id("id").password("password").email("email@gmail.com").nickName("닉네임").build();
        MemberDto member = memberService.createMember(memberDto);

        mvc.perform(get("/api/member/"+member.getSequence()))
                .andExpect(status().isOk());
    }

    @Test
    public void 멤버_업데이트_update_member() throws Exception {
        MemberDto memberDto = new MemberDto.Builder().id("id").password("password").email("email@gmail.com").nickName("닉네임").build();
        MemberDto member = memberService.createMember(memberDto);

        member.setPassword("pwd");
        member.setEmail("dev.loustler@gmail.com");
        member.setNickName("loustler");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(member);

        mvc.perform(put("/api/member/"+member.getSequence())
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void 멤버_삭제_delete_member() throws Exception {
        MemberDto memberDto = new MemberDto.Builder().id("id").password("password").email("email@gmail.com").nickName("닉네임").build();
        MemberDto member = memberService.createMember(memberDto);

        mvc.perform(delete("/api/member/"+member.getSequence()))
                .andExpect(status().isOk());
    }
}
