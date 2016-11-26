package io.codechobo.person.application;

import io.codechobo._testhelper.AssertUtils;
import io.codechobo.person.domain.Person;
import io.codechobo.person.dto.PersonDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    private PersonDto personFixture;

    @Before
    public void tearUp() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(1976, 6, 19, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        this.personFixture = generateFixture("베네딕트 컴버비치", calendar.getTime());
    }

    @Test
    public void 인물_등록_및_취득_테스트() {
        final PersonDto fixture = personFixture;
        final Person registered = this.personService.registerPerson(fixture);
        final Person retrieved = this.personService.getPerson(registered.getNo());

        assertNotNull(registered.getNo());
        AssertUtils.assertEquals(registered, retrieved);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 인물_취득_예외_테스트() {
        this.personService.getPerson(null);
    }

    @Test
    public void 인물_수정_테스트() {
        final PersonDto fixture = personFixture;
        final Person registered = this.personService.registerPerson(fixture);
        fixture.setName(fixture.getName()+"_MOD");
        final Calendar calendar = Calendar.getInstance();
        calendar.set(1976, 10, 19, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        fixture.setDateOfBirth(calendar.getTime());

        final Person modified = this.personService.modifyPerson(fixture);
        final Person retrieved = this.personService.getPerson(registered.getNo());

        AssertUtils.assertEquals(modified, retrieved);
    }

    public static PersonDto generateFixture(final String name, final Date dateOfBirth) {
        return new PersonDto(name, dateOfBirth);
    }
}
