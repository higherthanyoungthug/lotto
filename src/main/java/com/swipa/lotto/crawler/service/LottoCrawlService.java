package com.swipa.lotto.crawler.service;

import com.swipa.lotto.crawler.domain.LottoRound;
import com.swipa.lotto.crawler.repository.LottoCrawlRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LottoCrawlService {

    private final LottoCrawlRepository lottoCrawlRepository;

    @Transactional
    public LottoRound save(LottoRound entity){
        return lottoCrawlRepository.save(entity);
    }
}
