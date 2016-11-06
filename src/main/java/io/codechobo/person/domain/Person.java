package io.codechobo.person.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long no;

    /**
     * 성명
     */
    @Column(length = 50)
    private String name;

    /**
     * 생년월일
     */
    @Temporal(TemporalType.DATE)
    @Column
    private Date dateOfBirth;

    public Person(final String name) {
        this(name, null);
    }

    public Person(final String name, final Date dateOfBirth) {
        this(null, name, dateOfBirth);
    }

    public Person(final Long no, final String name, final Date dateOfBirth) {
        this.no = no;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

}
