package com.lotto.schedule;

import com.lotto.repository.LottoRepository;
import com.lotto.service.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LottoScheduler {

    private final LottoService lottoService;

    // 매일 오전 1시에 실행
    @Scheduled(cron = "0 0 1 * * ?")
    public void runLottoJob() {
        int nextDrwNo = lottoService.calculateNextDrwNo(); // 실제로는 마지막 번호 + 1로 계산
        lottoService.fetchAndSaveLottoNumber(nextDrwNo);
    }

}
