package com.example.untiled1.global.constants;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum NameAndValueEnum {
    PB("01-pb","PhongBanRp"),

    TK("01-tk","TaiKhoanRp");

    private final String value;
    private final String name;

    NameAndValueEnum(String value, String name){
        this.value = value;
        this.name = name;
    };

    public static NameAndValueEnum fromValue(String text) {
        for (NameAndValueEnum n: NameAndValueEnum.values()) {
            if (n.value.equalsIgnoreCase(text)) {
                return n;
            }
        }
        return null;
    }


}
