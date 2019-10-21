package com.zbcn.bootbase.test;

import com.zbcn.bootbase.config.CDPlayerConfig;
import com.zbcn.bootbase.dao.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDPlayerTest1 {

	@Autowired
	@Qualifier("cDPlayer")
	private CompactDisc cd;

	/**
	 * 测试@Import
	 */
	@Test
	public void cdShouldNotBeNull() {
//        assertNotNull(cd);
		cd.play();
	}
}
