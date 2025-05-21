package com.lotto.dto;

import lombok.Data;

@Data
public class NumberFrequency {
    private Integer number;
    private Long frequency;

    public NumberFrequency() {
    }

    public NumberFrequency(Integer number, Long frequency) {
        this.number = number;
        this.frequency = frequency;
    }
}
