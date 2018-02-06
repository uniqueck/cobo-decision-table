package org.ckr.lfet.model.check.rules.impl;

import static org.junit.Assert.assertFalse;

import java.io.File;

import org.ckr.lfet.dao.LFETDao;
import org.junit.Test;

public class StateMachineCheckTest {

	@Test
	public void testIsStateMachine() throws Exception {
		assertFalse("is a statemachine, but should not", new StateMachineCheck()
				.isStateMachine(LFETDao.fromFile(new File("src/test/resources/StateMachine_1_Eng.lfet"))));
	}

}
