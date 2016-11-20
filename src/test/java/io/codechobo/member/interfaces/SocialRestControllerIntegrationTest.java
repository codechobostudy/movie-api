package io.codechobo.member.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobo.member.application.MemberService;
import io.codechobo.member.application.SocialService;
import io.codechobo.member.domain.support.MemberDto;
import io.codechobo.member.domain.support.SocialDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author loustler
 * @since 10/30/2016 18:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(profiles = "test")
public class SocialRestControllerIntegrationTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private MemberService memberService;

    @Autowired
    private SocialService socialService;

    private MemberDto memberDto;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
        MemberDto memberDto = new MemberDto.Builder().id("id").password("password").email("email@gmail.com").nickName("닉네임").build();
        this.memberDto = memberService.createMember(memberDto);
    }

    @Test
    public void 설정_테스트_config_test() {}

    @Test
    public void create_social() throws Exception {
        // when
        SocialDto createData = socialDtoFactory();
        ObjectMapper mapper = new ObjectMapper();

        // given
        String json = mapper.writeValueAsString(createData);

        // then
        mvc.perform(post("/api/social/")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    @Test
    public void get_social() throws Exception {
        // given & when
        create_social();

        // then
        mvc.perform(get("/api/social/"+this.memberDto.getSequence())).andExpect(status().isOk());
    }

    @Test
    public void update_social() throws Exception {
        // given
        SocialDto created = new SocialDto.Builder().memberSequence(this.memberDto.getSequence()).token("sdfj12k3j12j3b13j5j4543k42kjljklbjdsf").type("github").build();
        created = socialService.createSocial(created);
        SocialDto update = created.setToken("sdfjs7dfdsfdsf8ds6fd5s8fsdf92934").setType("facebook");

        // when
        update = socialService.updateSocial(update);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(update);

        // then
        mvc.perform(put("/api/social/"+update.getSequence())
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_social() throws Exception {
        // given
        SocialDto created = socialDtoFactory();
        created = socialService.createSocial(created);

        // when & then
        mvc.perform(delete("/api/social/"+created.getSequence()))
                .andExpect(status().isOk());
    }

    private SocialDto socialDtoFactory() {
        return new SocialDto.Builder().memberSequence(this.memberDto.getSequence()).token("sdfj12k3j12j3b13j5j4543k42kjljklbjdsf").type("github").build();
    }

}
