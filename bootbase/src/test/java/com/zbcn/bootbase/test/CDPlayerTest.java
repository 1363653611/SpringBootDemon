package com.zbcn.bootbase.test;

import com.zbcn.bootbase.config.CDConfig;
import com.zbcn.bootbase.dao.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDConfig.class)
public class CDPlayerTest {

	@Autowired
	private CompactDisc cd;

	@Test
	public void cdShouldNotBeNull() {
//        assertNotNull(cd);
		cd.play();
	}
}
