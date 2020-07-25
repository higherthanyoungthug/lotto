package com.swipa.lotto.crawler.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.util.HashMap;
import java.util.Map;

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
@NoArgsConstructor
public class LottoCrawlController {

    @GetMapping("get")
    public JsonNode getLottoNum() throws JsonProcessingException {
        String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=903";

        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(url)
                .build();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> exchange = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, httpEntity, String.class);

        Object body = exchange.getBody();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(body);
        String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
        JsonNode obj = mapper.readTree(s);
        System.out.println(obj);
        System.out.println(s);

        return obj;
    }
}
