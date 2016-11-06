package io.codechobo.person.application;

import io.codechobo.person.domain.Person;
import io.codechobo.person.domain.repository.PersonRepository;
import io.codechobo.person.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Person getPerson(final Long no) {
        if (no == null) {
            throw new IllegalArgumentException("Does not specify person no");
        }

        return  repository.findOne(no);
    }

    /**
     * 인물을 등록한다.
     *
     * @param dto 등록할 인물 정보
     * @return 등록된 인물 정보
     */
    public Person registerPerson(final PersonDto dto) {
        final Person person = this.repository.save(new Person(dto.getName(), dto.getDateOfBirth()));
        dto.setNo(person.getNo());
        return person;
    }

    public Person modifyPerson(final PersonDto dto) {
        if (dto.getNo() == null) {
            throw new IllegalArgumentException();
        }
        final Person person = this.repository.save(new Person(dto.getNo(), dto.getName(), dto.getDateOfBirth()));
        return person;
    }
}
