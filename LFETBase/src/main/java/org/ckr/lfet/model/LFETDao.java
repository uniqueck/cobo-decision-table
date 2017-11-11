package org.ckr.lfet.model;

import java.io.File;

import org.ckr.lfet.exception.LFETParseRuntimeException;
import org.simpleframework.xml.core.Persister;

public class LFETDao {
	
	private LFETDao() {
	}

	public static LFET fromFile(File lfetFile) {
		try {
			Persister persister = new Persister();
			return persister.read(LFET.class, lfetFile);
		} catch (Exception e) {
			throw new LFETParseRuntimeException(e);
		}
	}

}
