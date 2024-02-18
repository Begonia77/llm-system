package cn.iocoder.yudao.module.system.enums.warehouse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WareHouseType {

    LARGE(1),
    MEDIUM(2),
    SMALL(3),
    ;

    private final int type;
}
