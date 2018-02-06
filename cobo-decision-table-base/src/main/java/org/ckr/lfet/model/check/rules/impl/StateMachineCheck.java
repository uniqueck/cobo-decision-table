package org.ckr.lfet.model.check.rules.impl;

import java.util.Iterator;

import org.ckr.lfet.model.Action;
import org.ckr.lfet.model.Condition;
import org.ckr.lfet.model.LFET;
import org.ckr.lfet.model.check.rules.StateMachine;
import org.ckr.lfet.model.check.rules.lfet.Check4StateMachineETLogic;
import org.ckr.lfet.model.check.rules.lfet.ICheck4StateMachine;

public class StateMachineCheck implements StateMachine, ICheck4StateMachine {

	private enum State {
		INIT, CA, CC, CHECK, EXIT
	}
	
	private LFET lfet;
	private boolean stateMachine;
	private State actualState;
	private Iterator<Condition> conditionsIt;
	private Iterator<Action> actionsIt;
	private Condition actualCondition;
	private Action actualAction;

	public StateMachineCheck() {
	}

	@Override
	public boolean isStateMachine(LFET lfet) {
		this.lfet = lfet;
		this.stateMachine = false;
		this.actualState = State.INIT;
		this.conditionsIt = this.lfet.getConditions().iterator();
		this.actionsIt = this.lfet.getActions().iterator();
		Check4StateMachineETLogic etLogic = new Check4StateMachineETLogic();
		do  {
			etLogic.execute(this);
		} while (this.actualState != State.EXIT);
		return this.stateMachine;
	}

	protected LFET getLFET() {
		return this.lfet;
	}


	

	@Override
	public void dostateMachine_Y() {
		this.stateMachine = true;
	}

	@Override
	public void dostateMachine_N() {
		this.stateMachine = false;

	}

	@Override
	public void doTrace(String dtName, String version, int rules, int rule) {

	}

	@Override
	public boolean isstate_INIT() {
		return State.INIT == actualState;
	}

	@Override
	public boolean isstate_CC() {
		return State.CC == actualState;
	}

	@Override
	public boolean isstate_CA() {
		return State.CA == actualState;
	}

	@Override
	public boolean isconditionHasOccurenceTable() {
		return actualCondition.getOccurences() != null && !actualCondition.getOccurences().isEmpty();
	}

	@Override
	public boolean isconditionTitleContainsWord_ZUSTAND() {
		return actualCondition.getTitle().getValue().toUpperCase().contains("ZUSTAND");
	}

	@Override
	public boolean isconditionTitleContainsWord_STATE() {
		return actualCondition.getTitle().getValue().toUpperCase().contains("STATE");
	}

	@Override
	public boolean ishasNextCondition() {
		return conditionsIt.hasNext();
	}

	@Override
	public boolean isactionHasOccurenceTable() {
		return !actualAction.getOccurences().isEmpty();
	}

	@Override
	public boolean isactionTitleContainsWord_ZUSTAND() {
		return actualAction.getTitle().getValue().toUpperCase().contains("ZUSTAND");
	}

	@Override
	public boolean isactionTitleContainsWord_STATE() {
		return actualAction.getTitle().getValue().toUpperCase().contains("STATE");
	}

	@Override
	public boolean ishasNextAction() {
		return actionsIt.hasNext();
	}

	@Override
	public boolean isnumberOfStateActions_0() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isnumberOfStateActions_1() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isnumberOfStateConditions_0() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isnumberOfStateConditions_1() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void docountCondition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void docountAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void donextCondition() {
		actualCondition = conditionsIt.next();
	}

	@Override
	public void donextAction() {
		actualAction = actionsIt.next();
	}

	@Override
	public void dostate_EXIT() {
		actualState = State.EXIT;
	}

	@Override
	public void dostate_CC() {
		actualState = State.CC;
	}

	@Override
	public void dostate_CHECK() {
		actualState  = State.CHECK;
	}

	@Override
	public void dostate_CA() {
		actualState = State.CA;
	}

}
