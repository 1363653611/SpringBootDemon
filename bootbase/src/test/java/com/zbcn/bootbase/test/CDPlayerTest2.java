package com.zbcn.bootbase.test;

import com.zbcn.bootbase.config.SoundSystemConfig;
import com.zbcn.bootbase.dao.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoundSystemConfig.class)
public class CDPlayerTest2 {

	@Autowired
	@Qualifier("cDPlayer")
	private CompactDisc cd;

	@Autowired
	@Qualifier("compactDisc")
	private CompactDisc cd1;

	/**
	 * 测试@ImportResource
	 */
	@Test
	public void cdShouldNotBeNull() {
        assertNotNull(cd);
		cd.play();
	}

	@Test
	public void testBlankDisc() {
		cd1.play();
	}

}
