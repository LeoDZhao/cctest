package home.jmstudios.calc.test.action;

import home.jmstudios.calc.test.TestConst;

import java.util.ArrayList;

import android.view.View;
import android.widget.EditText;

public class ChangePrecision extends Action{

	public int execute(){		
		super.execute();
		
		//Unlock the lock screen
		solo.unlockScreen();
		String text = mConstantsMap.get("TEXT_SETTING_ITEM_PRECISION");
		solo.clickOnText(text);
		
		EditText precisionEditText = solo.getEditText(0);
		solo.clearEditText(precisionEditText);
		solo.enterText(precisionEditText, mInputExpression);
		
		String resultString = precisionEditText.getText().toString();
		junit.framework.Assert.assertEquals("Change Precision failed!", mExpectedResult, resultString);
		
		String idString = mConstantsMap.get("ID_OK_BUTTON");
		View okButton = solo.getView(idString);
		solo.clickOnView(okButton);
		
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
