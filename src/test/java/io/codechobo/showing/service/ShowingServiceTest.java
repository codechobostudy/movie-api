package io.codechobo.showing.service;

import io.codechobo.showing.dto.ShowingCreateRequest;
import io.codechobo.showing.model.type.SeatState;
import io.codechobo.showing.model.Showing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.codechobo._testhelper.SejongAssert.objectAssertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShowingServiceTest {
    @Autowired
    ShowingService service;

    @Test
    public void Showing생성하기() throws Exception {
        //given
        ShowingCreateRequest request = new ShowingCreateRequest(
                "센과 치히로의 행방불명", 100
        );

        //when
        Showing showing = service.create(request);

        //then
        objectAssertThat(showing)
                .is("movieName", request.getMovieName());

        objectAssertThat(showing.getSeats()).asList()
                .hasSize(100);

        objectAssertThat(showing.getSeats().get(0))
                .is("state", SeatState.INIT);
    }
}