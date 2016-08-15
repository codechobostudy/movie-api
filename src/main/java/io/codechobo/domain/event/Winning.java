package io.codechobo.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * 이벤트 당첨 엔티티
 *
 * @author Kj Nam
 * @since 2016-08-06
 */
@Entity
@Getter
@NoArgsConstructor
public class Winning {

    /*
     * TODO: 당첨 엔티티 정의 재검토
     * 이벤트 당첨 객체의 역할은
     * 응모자 객체 사이에서 당첨을 (0...*)를 추첨하고 그 당첨 정보를 가진다.
     *
     * 어떤 속성을 가져야 하고, 어떤 연관관계를 맺어야 할 지 다시 고민 필요
     */

    @Id @GeneratedValue
    @Column(name = "WIN_ID")
    private Long id;

    @OneToMany
    private List<Entry> entries = new ArrayList<>();

    public void addEntry(Entry entry) {
        this.entries.add(entry);
        if (entry.getWinning() != this) {
            entry.setWinning(this);
        }
    }
}
