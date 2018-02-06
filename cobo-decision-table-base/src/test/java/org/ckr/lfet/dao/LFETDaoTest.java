package org.ckr.lfet.dao;

import java.io.File;

import org.ckr.lfet.exception.LFETParseRuntimeException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

public class LFETDaoTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder(new File("./target"));
	
	@Test
	public void testFromFile_FileNull_ThrowsException() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("lfetFile is null");
		LFETDao.fromFile(null);
	}
	
	@Test
	public void testFromFile_FileDoesntExist_ThrowsException() throws Exception {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("lfetFile doesn't exist");
		LFETDao.fromFile(new File(folder.getRoot(), "noExistingFile"));
	}
	
	@Test
	public void testFromFile_FileWithNoValidContent_ThrowsException() throws Exception {
		File fileWithNotValidContent = folder.newFile();
		
		expectedException.expect(LFETParseRuntimeException.class);
		expectedException.expectMessage("error on read " + fileWithNotValidContent.getAbsolutePath());
		LFETDao.fromFile(fileWithNotValidContent);
	}

}
