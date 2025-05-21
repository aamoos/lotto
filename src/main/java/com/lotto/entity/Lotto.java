package com.lotto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Lotto {

    // 회차 번호 (로또 회차를 고유 식별하는 ID)
    @Id
    private Integer drwNo;

    // 당첨 번호 6개
    private Integer drwtNo1;
    private Integer drwtNo2;
    private Integer drwtNo3;
    private Integer drwtNo4;
    private Integer drwtNo5;
    private Integer drwtNo6;

    // 보너스 번호
    private Integer bnusNo;

    // 총 판매 금액
    private Long totSellamnt;

    // 추첨일자 (YYYY-MM-DD 형식의 문자열)
    private String drwNoDate;

    // 1등 당첨금액
    private Long firstWinamnt;

    // 1등 당첨자 수
    private Integer firstPrzwnerCo;

    // 1등 누적 당첨금액
    private Long firstAccumamnt;

    // 데이터 삽입 시간
    private LocalDateTime insertTime;

    public Lotto() {
    }

    public Lotto(Integer drwNo, Integer drwtNo1, Integer drwtNo2, Integer drwtNo3, Integer drwtNo4, Integer drwtNo5, Integer drwtNo6, Integer bnusNo, Long totSellamnt, String drwNoDate, Long firstWinamnt, Integer firstPrzwnerCo, Long firstAccumamnt) {
        this.drwNo = drwNo;
        this.drwtNo1 = drwtNo1;
        this.drwtNo2 = drwtNo2;
        this.drwtNo3 = drwtNo3;
        this.drwtNo4 = drwtNo4;
        this.drwtNo5 = drwtNo5;
        this.drwtNo6 = drwtNo6;
        this.bnusNo = bnusNo;
        this.totSellamnt = totSellamnt;
        this.drwNoDate = drwNoDate;
        this.firstWinamnt = firstWinamnt;
        this.firstPrzwnerCo = firstPrzwnerCo;
        this.firstAccumamnt = firstAccumamnt;
        this.insertTime = LocalDateTime.now();
    }
}
