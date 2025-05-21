package com.lotto.controller;

import com.lotto.dto.NumberFrequency;
import com.lotto.entity.Lotto;
import com.lotto.repository.LottoRepository;
import com.lotto.service.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class LottoController {

    private final LottoService lottoService;
    private final LottoRepository lottoRepository;

    @Value("${lotto.latest-draw-no}")
    private int latestDrawNo;

    //모든 회차 insert
    @GetMapping("/insertAll")
    @ResponseBody
    public String insertAll(){
        for(int i=1; i<=latestDrawNo; i++){
            lottoService.fetchAndSaveLottoNumber(i);
            try {
                Thread.sleep(1000); // 1초 쉬기
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return "ok";
    }

    //특정 회차 insert
    @GetMapping("/insert/{no}")
    @ResponseBody
    public String insert(@PathVariable("no") int no){
        lottoService.fetchAndSaveLottoNumber(no);
        return "ok";
    }

    @GetMapping("/")
    public String showTopNumbers(Model model) {
        List<NumberFrequency> mainNumbers = lottoService.getTop6MainNumbers();
        List<NumberFrequency> bonusNumbers = lottoService.getTop6BonusNumbers();

        model.addAttribute("mainNumbers", mainNumbers);
        model.addAttribute("bonusNumbers", bonusNumbers);
        Optional<Lotto> optionalLotto = lottoRepository.findTopByOrderByDrwNoDesc();
        optionalLotto.ifPresent(lotto -> model.addAttribute("lastNo", lotto.getDrwNo()));

        return "lottoTopNumbers";
    }

}
