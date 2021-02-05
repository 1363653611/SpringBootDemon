package com.zbcn.combootes.nba.entity;

import lombok.Data;

@Data
public class NBAPlayer {
    /**
     * id
     */
    private Integer id;
    /**
     * 城市 en
     */
    private String countryEn;
    /**
     * 城市
     */
    private String country;
    /**
     * 城市 代码
     */
    private String code;
    /**
     * 球员联盟
     */
    private String displayAffiliation;
    /**
     * 球员名称
     */
    private String displayName;
    private Integer draft;
    private String schoolType;
    private String weight;
    private Integer playYear;
    private String jerseyNo;
    private Long birthDay;
    private String birthDayStr;
    private String displayNameEn;
    private String position;
    private Double heightValue;
    private String playerId;
    private String teamCity;
    private String teamCityEn;
    private String teamName;
    private String teamNameEn;
    private String teamConference;
    private String teamConferenceEn;
    private Integer age;
}
