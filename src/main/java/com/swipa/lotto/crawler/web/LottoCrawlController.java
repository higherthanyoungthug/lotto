package com.swipa.lotto.crawler.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.swipa.lotto.crawler.domain.LottoRound;
import com.swipa.lotto.crawler.repository.LottoCrawlRepository;
import com.swipa.lotto.crawler.service.LottoCrawlService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by IntelliJ IDEA.
 * User: DLo
 * Date: 2020/07/25
 * Time: 12:51
 * Title: LottoCrawlController
 * Desc:
 * == Modification Infomation ==
 * <p>
 * 수정일         수정자         수정내용
 * ------------ ------------ ----------------------------
 * 2020/07/25   DLo           최초생성
 */
@RestController
@Slf4j
@AllArgsConstructor
public class LottoCrawlController {

    private final LottoCrawlService lottoCrawlService;

    @GetMapping("get")
    public ResponseEntity<LottoRound> getLottoNum() throws JsonProcessingException {
        LottoRound save = null;
            //20200725 = 921
            String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=921";

            UriComponents uriComponents = UriComponentsBuilder
                    .fromHttpUrl(url)
                    .build();
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

            ResponseEntity<String> exchange = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, httpEntity, String.class);

            String body = exchange.getBody();
            JsonObject obj = JsonParser.parseString(body).getAsJsonObject();
            save = lottoCrawlService.save(LottoRound.createLotto(obj));



            return ResponseEntity.ok(save);

    }
}
