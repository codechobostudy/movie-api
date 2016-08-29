package io.codechobo.member.domain.support;

import io.codechobo.member.domain.PointPerLevel;
import io.codechobo.member.domain.Social;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author Loustler
 * @since 8/29/16
 */
@Setter
@Getter
@NoArgsConstructor
public class MemberDto {

    private Long sequence;
    private String id;
    private String password;
    private String email;
    private String nickName;
    private Date regiDate;
    private Integer point;
    private PointPerLevel level;
    private List<Social> socials;

}
