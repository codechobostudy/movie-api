package io.codechobo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name; /* 극장 이름 */
    private String location; /* 극장 위치 */

    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    private Set<Screen> screens = new HashSet<>();

    public Theater(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void addScreen(Screen screen) {
        if(screen == null) throw new IllegalArgumentException("스크린 정보를 찾을 수 없습니다");
        screens.add(screen);
    }
}
