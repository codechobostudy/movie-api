package io.codechobo.person.dto;

import lombok.Getter;
import lombok.Setter;
import org.javers.core.metamodel.annotation.Id;

import java.util.Date;

@Setter
@Getter
public class PersonDto {

    @Id
    private Long no;

    private String name;

    private Date dateOfBirth;

    public PersonDto(final String name, final Date dateOfBirth) {
        this(null, name, dateOfBirth);
    }

    public PersonDto(final Long no, final String name, final Date dateOfBirth) {
        this.no = no;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }
}
