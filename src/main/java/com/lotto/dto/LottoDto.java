package com.lotto.dto;

import com.lotto.entity.Lotto;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LottoDto {
    @Id
    private Integer drwNo;

    private Integer drwtNo1;
    private Integer drwtNo2;
    private Integer drwtNo3;
    private Integer drwtNo4;
    private Integer drwtNo5;
    private Integer drwtNo6;
    private Integer bnusNo;
    private String returnValue;
    private Long totSellamnt;
    private String drwNoDate;
    private Long firstWinamnt;
    private Integer firstPrzwnerCo;
    private Long firstAccumamnt;

    public Lotto toEntity() {
        return new Lotto(
                this.drwNo,
                this.drwtNo1,
                this.drwtNo2,
                this.drwtNo3,
                this.drwtNo4,
                this.drwtNo5,
                this.drwtNo6,
                this.bnusNo,
                this.totSellamnt,
                this.drwNoDate,
                this.firstWinamnt,
                this.firstPrzwnerCo,
                this.firstAccumamnt
        );
    }
}
