package org.ckr.lfet.dao;

import java.io.File;

import org.ckr.lfet.exception.LFETParseRuntimeException;
import org.ckr.lfet.model.LFET;
import org.simpleframework.xml.core.Persister;

public class LFETDao {
	
	private LFETDao() {
	}

	public static LFET fromFile(File lfetFile) {
		if (lfetFile == null) {
			throw new IllegalArgumentException("lfetFile is null");
		} else if (!lfetFile.exists()) {
			throw new IllegalArgumentException("lfetFile doesn't exist");
		}
		
		try {
			Persister persister = new Persister();
			return persister.read(LFET.class, lfetFile);
		} catch (Exception e) {
			throw new LFETParseRuntimeException("error on read " + lfetFile.getAbsolutePath(),e);
		}
	}

}
