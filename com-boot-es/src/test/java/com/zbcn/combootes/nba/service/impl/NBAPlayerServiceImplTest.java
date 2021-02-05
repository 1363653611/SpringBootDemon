package com.zbcn.combootes.nba.service.impl;

import com.alibaba.fastjson.JSON;
import com.zbcn.combootes.nba.entity.NBAPlayer;
import com.zbcn.combootes.nba.service.NBAPlayerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
class NBAPlayerServiceImplTest {

    @Autowired
    NBAPlayerService  nbaPlayerService;

    @Test
    void addPlayer() throws IOException {
        NBAPlayer nbaPlayer = new NBAPlayer();
        nbaPlayer.setId(1);
        nbaPlayer.setAge(25);
        nbaPlayer.setBirthDay(949479477L);
        nbaPlayer.setBirthDayStr("2000-02-02 16:17:57");
        nbaPlayer.setCode("45");
        nbaPlayer.setCountry("中国");
        nbaPlayer.setCountryEn("china");
        nbaPlayer.setDisplayAffiliation("中国籍 优秀球员");
        nbaPlayer.setHeightValue(211.02d);
        nbaPlayer.setPlayYear(10);
        nbaPlayer.setAge(25);
        nbaPlayer.setPosition("江苏");
        nbaPlayer.setJerseyNo("25");
        nbaPlayer.setDisplayName("易建联");
        nbaPlayer.setSchoolType("高中");
        nbaPlayer.setWeight("85.10");
        nbaPlayer.setTeamName("火箭");
        nbaPlayer.setTeamNameEn("host rocket");
        nbaPlayerService.addPlayer(nbaPlayer, String.valueOf(nbaPlayer.getId()));
    }

    @Test
    void getPlayer() throws IOException {
        NBAPlayer player = nbaPlayerService.getPlayer("1");
        System.out.println(JSON.toJSONString(player));
    }

    @Test
    void importAll() throws IOException {
        nbaPlayerService.importAll();
    }




}