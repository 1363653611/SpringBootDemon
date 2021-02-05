package com.zbcn.combootes.nba.service;

import com.zbcn.combootes.nba.entity.NBAPlayer;

import java.io.IOException;
import java.util.List;

public interface NBAPlayerService {
    boolean addPlayer(NBAPlayer player, String id) throws IOException;

    /**
     * 获取球员信息
     * @param id
     * @return
     */
    NBAPlayer getPlayer(String id) throws IOException;

    /**
     * 导入全部数据库中的数据
     * @return
     * @throws IOException
     */
    boolean importAll()throws IOException;

    /**
     * 更新nba 球员信息
     * @param nbaPlayer
     * @param id
     * @return
     */
    boolean updatePlayer(NBAPlayer nbaPlayer,String id) throws IOException;

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deletePlayer(String id) throws IOException;

    /**
     * 全部删除
     * @return
     */
    boolean deleteAllPlayer() throws IOException;

    /**
     * 获取匹配的值
     * @param key
     * @param value
     * @return
     */
    List<NBAPlayer> searchMatch(String key, String value) throws IOException;

    /**
     * 通过国家或者球队查找球员
     * @param key
     * @param value
     * @return
     */
    List<NBAPlayer> searchTerm(String key, String value) throws IOException;

}
