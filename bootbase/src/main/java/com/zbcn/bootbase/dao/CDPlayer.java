package com.zbcn.bootbase.dao;

import org.springframework.beans.factory.annotation.Autowired;

public class CDPlayer implements CompactDisc {

	private CompactDisc cd;

	@Autowired   //构造函数注入
	public CDPlayer(CompactDisc cd) {
		this.cd = cd;
	}
	@Override
	public void play() {
		cd.play();
	}
}
