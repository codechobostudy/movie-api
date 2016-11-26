package io.codechobo.movie.domain;

import io.codechobo.movie.domain.MovieDirector.MovieDirectorId;
import io.codechobo.person.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@IdClass(MovieDirectorId.class)
public class MovieActor {


    @Id
    @Column(name = "MOVIE_NO", insertable = false, updatable = false)
    private Long movieNo;

    @Id
    @Column(name="PERSON_NO", insertable = false, updatable = false)
    private Long personNo;

    @Column
    private Integer sort;


    @ManyToOne
    @PrimaryKeyJoinColumn(name="MOVIE_NO", referencedColumnName="NO")
    @MapsId("movieNo")
    private Movie movie;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="PERSON_NO", referencedColumnName="NO")
    @MapsId("personNo")
    private Person person;


    public MovieActor(final Movie movie, final Person person, final Integer sort) {
        this.movieNo = movie.getNo();
        this.movie = movie;
        this.personNo = person.getNo();
        this.person = person;
        this.sort = sort;
    }

    public static class MovieActorId implements Serializable {

        private Long movieNo;
        private Long personNo;

        public MovieActorId() {
        }

        public MovieActorId(final Long movieNo, final Long personNo) {
            this.movieNo = movieNo;
            this.personNo = personNo;
        }

        public Long getMovieNo() {
            return movieNo;
        }

        public Long getPersonNo() {
            return personNo;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((movieNo == null) ? 0 : movieNo.hashCode());
            result = prime * result + ((personNo == null) ? 0 : personNo.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            MovieActorId other = (MovieActorId) obj;
            if (movieNo == null) {
                if (other.movieNo != null) {
                    return false;
                }
            } else if (!movieNo.equals(other.movieNo)) {
                return false;
            }
            if (personNo == null) {
                if (other.personNo != null) {
                    return false;
                }
            } else if (!personNo.equals(other.personNo)) {
                return false;
            }
            return true;
        }


    }
}
