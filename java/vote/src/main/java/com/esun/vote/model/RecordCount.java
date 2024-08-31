package com.esun.vote.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecordCount {
    private Integer element;
    private Long cnt;

    public RecordCount(Integer element, Long cnt) {
        this.element = element;
        this.cnt = cnt;
    }
}
