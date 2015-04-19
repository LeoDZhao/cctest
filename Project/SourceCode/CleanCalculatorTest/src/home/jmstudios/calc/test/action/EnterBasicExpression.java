package home.jmstudios.calc.test.action;

import home.jmstudios.calc.test.TestConst;

import java.util.ArrayList;

import android.widget.EditText;

public class EnterBasicExpression extends Action{

	public int execute(){		
		super.execute();
		
		//Unlock the lock screen
		solo.unlockScreen();

		//Click Button according to the inputExpression
		for (int i = 0; i < mInputExpression.length(); i++) {
			char tempChar = mInputExpression.charAt(i);
			String buttonText = String.valueOf(tempChar);
			solo.clickOnView(solo.getButton(buttonText));
		}

		solo.sleep(1000);
		String idString = mConstantsMap.get("ID_RESULT_EDITTEXT");
		String resultString = ((EditText)solo.getView(idString)).getText().toString();
		junit.framework.Assert.assertEquals("Basic calculation failed!", mExpectedResult, resultString);
		
		return 0;
	}
	
	public void setParameter(ArrayList<String> args){
		
		if (args==null || args.size()<1) {
			junit.framework.Assert.assertEquals(TestConst.Message.NO_PARAMETER, true, false);
		}else {
			mInputExpression = args.get(0);
			mExpectedResult = args.get(1);
		}
		
	}
	
	

}
