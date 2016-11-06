package io.codechobo.movie.application;

import io.codechobo.movie.domain.Movie;
import io.codechobo.movie.domain.repository.MovieRepository;
import io.codechobo.movie.dto.MovieDto;
import io.codechobo.person.domain.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;



    public Movie getMovie(final Long no) {
        if (no == null) {
            throw new IllegalArgumentException();
        }
        return this.movieRepository.findOne(no);
//        //TODO 컨버팅 유틸리티 필요
//        if (movie == null) {
//            return null;
//        }
//        return new MovieDto(movie.getNo(), movie.getTitle(), movie.getGenre(), movie.getRunningTime(), movie.getReleaseDate());
    }

    public Movie registerMovie(@Valid final MovieDto movieDto) {
        if (movieDto.getNo() != null) {
            throw new IllegalArgumentException();
        }
        Movie movie = this.movieRepository.save(new Movie(movieDto.getTitle(), movieDto.getGenre(), movieDto.getRunningTime(), movieDto.getReleaseDate()));
        movieDto.getDirectors().forEach(director -> {
            movie.addDirector(personRepository.getOne(director.getNo()));
        });
        movieDto.getActors().forEach(actor -> {
            movie.addActor(personRepository.getOne(actor.getNo()));
        });
        return movie ;
        //TODO 컨버팅 유틸리티 필요
//        return new MovieDto(movie.getNo(), movie.getTitle(), movie.getGenre(), movie.getRunningTime(), movie.getReleaseDate());
    }

    public MovieDto modifyMovie(@Valid final MovieDto movieDto) {
        if (movieDto.getNo() == null) {
            throw new IllegalArgumentException();
        }
        if (!this.movieRepository.exists(movieDto.getNo())) {
            throw new IllegalArgumentException();
        }
        final Movie movie = this.movieRepository.save(new Movie(movieDto.getNo(), movieDto.getTitle(), movieDto.getGenre(), movieDto.getRunningTime(), movieDto.getReleaseDate()));
        return new MovieDto(movie.getNo(), movie.getTitle(), movie.getGenre(), movie.getRunningTime(), movie.getReleaseDate());
    }

    public void removeMovie(final MovieDto movieDto) {
        if (movieDto.getNo() == null) {
            throw new IllegalArgumentException();
        }
        this.movieRepository.delete(movieDto.getNo());
    }

}
