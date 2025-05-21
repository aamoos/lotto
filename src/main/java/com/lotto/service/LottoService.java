package com.lotto.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotto.dto.LottoDto;
import com.lotto.dto.NumberFrequency;
import com.lotto.entity.Lotto;
import com.lotto.repository.LottoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LottoService {

    private final LottoRepository lottoRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${lotto.latest-draw-no}")
    private int latestDrawNo;

    //로또 마지막번호 가져오기
    public int calculateNextDrwNo() {

        return lottoRepository.findTopByOrderByDrwNoDesc()
                .map(lotto -> lotto.getDrwNo() + 1)
                .orElse(latestDrawNo);
    }
    
    //로또번호로 당첨번호 가져오기
    public void fetchAndSaveLottoNumber(int drwNo) {
        String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + drwNo;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                String bodyStr = response.getBody();
                LottoDto lottoDto = objectMapper.readValue(bodyStr, LottoDto.class);

                if ("success".equals(lottoDto.getReturnValue())) {
                    lottoRepository.save(lottoDto.toEntity());
                }

            } catch (Exception e) {
                // 파싱 실패 처리
                e.printStackTrace();
            }
        }
    }

    public List<NumberFrequency> getTop6MainNumbers() {
        List<Object[]> results = lottoRepository.findTop6MainNumbers();
        return results.stream()
                .map(row -> new NumberFrequency(
                        (Integer) row[0],
                        ((Number) row[1]).longValue()
                ))
                .collect(Collectors.toList());
    }

    public List<NumberFrequency> getTop6BonusNumbers() {
        List<Object[]> results = lottoRepository.findTop6BonusNumbers();
        return results.stream()
                .map(row -> new NumberFrequency(
                        (Integer) row[0],
                        ((Number) row[1]).longValue()
                ))
                .collect(Collectors.toList());
    }
}
