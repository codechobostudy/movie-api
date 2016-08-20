package io.codechobo.domain;


import helper.EntityIntegrationTestHelper;
import io.codechobo.domain.repository.TheaterRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class TheaterIntegrationTest extends EntityIntegrationTestHelper {

    @Autowired
    private TheaterRepository theaterRepository;

    private Theater saveTheater;

    @Before
    public void setUp() {
        saveTheater = createTheaterFixture();
    }

    @After
    public void cleanUp() {
        theaterRepository.deleteAll();
    }

    @Test
    public void 테스트설정_확인_테스트_Theater_Integration_Test() {
        assertNotNull(saveTheater.getId());
        assertThat(saveTheater.getName(), is("name"));
        assertThat(saveTheater.getLocation(), is("location"));
    }

    @Test
    @Transactional
    public void 극장에_스크린정보_추가하기() {

        //given
        Screen screen = new Screen();

        //when
        saveTheater.addScreen(screen);

        //then
        assertThat(saveTheater.getScreens().size(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 극장에_존재하지않는_스크린정보_추가시_예외() {
        //given
        Screen screen = null;

        //when
        saveTheater.addScreen(screen);

        //then
        //Exception
    }

    private Theater createTheaterFixture() {
        Theater theater = new Theater("name", "location");
        return theaterRepository.save(theater);
    }
}
