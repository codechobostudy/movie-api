package io.codechobo.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BookService {
    @Autowired
    BookRepository repository;

    @PostConstruct
    public void initDummy(){
        repository.save(new Book("칼의노래", "김훈"));
        repository.save(new Book("흑산", "김훈"));
        repository.save(new Book("코스모스", "칼 세이건"));
    }
}
