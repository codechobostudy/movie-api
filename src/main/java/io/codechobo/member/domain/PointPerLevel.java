package io.codechobo.member.domain;

import lombok.Getter;

/**
 * @author loustler
 * @since 08/16/2016
 */
@Getter
public enum PointPerLevel {
    BASIC(0, 100),      // Basic Level
    STANDARD(100, 200),   // Standard Level
    VIP(200, 500),        // VIP Level
    VVIP(500, 100000);        // VVIP Level

    private int min;
    private int max;

    PointPerLevel(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static PointPerLevel valueOf(int point) {
        if(0 <= point && point < 100) return BASIC;
        else if(100 <= point && point < 200) return STANDARD;
        else if(200 <= point && point < 500) return VIP;
        else return VVIP;
    }
}
