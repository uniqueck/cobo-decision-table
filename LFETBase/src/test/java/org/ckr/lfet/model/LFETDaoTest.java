package org.ckr.lfet.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LFETDaoTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testFromFile_FileNull_ThrowsException() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("lfetFile is null");
		LFETDao.fromFile(null);
	}

}
