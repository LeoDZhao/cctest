package home.jmstudios.calc.test.action;

import home.jmstudios.calc.test.CCTest;

import java.util.ArrayList;
import java.util.HashMap;

import com.robotium.solo.Solo;

public class Action {
	
	protected Solo solo;
	protected String mInputExpression;
	protected String mExpectedResult;
	protected HashMap<String, String> mConstantsMap;
	
	public int execute(){
		solo = CCTest.getSolo();
		mConstantsMap = CCTest.getConstantsMap();
		return 0;
	}
	
	public void setParameter(ArrayList<String> args){
		
	}

}
