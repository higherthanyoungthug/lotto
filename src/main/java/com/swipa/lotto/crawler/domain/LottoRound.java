package com.swipa.lotto.crawler.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class LottoRound {

    @Id @GeneratedValue
    @Column(name = "lotto_round_id")
    private Long id;

    private BigDecimal totSell = BigDecimal.ZERO;    //총 판매금액
    private String roundDate;    //추첨일
    private String whichRound;      //몇 회차
    private BigDecimal winnerMoney = BigDecimal.ZERO;    //1등 상금
    private BigDecimal allWinnersMoney = BigDecimal.ZERO;    //총 1등 당첨자들 합산금액
    private int firstNum;   //번호1
    private int secondNum;   //번호2
    private int thirdNum;   //번호3
    private int fourthNum;   //번호4
    private int fifthNum;   //번호5
    private int sixthNum;   //번호6
    private int bonusNum;   //보너스 번호

    public static LottoRound createLotto(JsonObject body){
        LottoRound entity = new LottoRound();
//{"totSellamnt":88625160000
//,"returnValue":"success"
//,"drwNoDate":"2020-03-21"
//,"firstWinamnt":1684582212
//,"drwtNo6":28
//,"drwtNo4":21
//,"firstPrzwnerCo":13
//,"drwtNo5":22
//,"bnusNo":45
//,"firstAccumamnt":21899568756
//,"drwNo":903
//,"drwtNo2":15
//,"drwtNo3":16
//,"drwtNo1":2}
        entity.setTotSell(body.get("totSellamnt").getAsBigDecimal());
        entity.setRoundDate(body.get("drwNoDate").getAsString());
        entity.setWhichRound(body.get("drwNo").getAsString());
        entity.setWinnerMoney(body.get("firstWinamnt").getAsBigDecimal());
        entity.setAllWinnersMoney(body.get("firstAccumamnt").getAsBigDecimal());
        entity.setFirstNum(body.get("drwtNo1").getAsInt());
        entity.setSecondNum(body.get("drwtNo2").getAsInt());
        entity.setThirdNum(body.get("drwtNo3").getAsInt());
        entity.setFourthNum(body.get("drwtNo4").getAsInt());
        entity.setFifthNum(body.get("drwtNo5").getAsInt());
        entity.setSixthNum(body.get("drwtNo6").getAsInt());
        entity.setBonusNum(body.get("bnusNo").getAsInt());

        return entity;
    }
}
