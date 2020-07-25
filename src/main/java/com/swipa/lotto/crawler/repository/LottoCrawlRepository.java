package com.swipa.lotto.crawler.repository;

import com.swipa.lotto.crawler.domain.LottoRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LottoCrawlRepository extends JpaRepository<LottoRound,Long> {
}
