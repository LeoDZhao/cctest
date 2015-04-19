package home.jmstudios.calc.test.action;

import home.jmstudios.calc.test.TestConst;

import java.util.ArrayList;

import android.view.View;
import android.widget.EditText;

public class Reset extends Action{

	
	public int execute(){		
		super.execute();
		
		//Unlock the lock screen
		solo.unlockScreen();

		String idString = mConstantsMap.get("ID_RESET_BUTTON");
		View resetButton  = solo.getView(idString);
		solo.clickOnView(resetButton);
		solo.sleep(TestConst.Time.WAIT_SHORT);
		
		idString = mConstantsMap.get("ID_RESULT_EDITTEXT");
		String resultString = ((EditText)solo.getView(idString)).getText().toString();
		String expectedString = mConstantsMap.get("TEXT_VALUE_AFTER_RESET");
		junit.framework.Assert.assertEquals("Rest failed!", expectedString, resultString);
		
		return 0;
	}
	
	public void setParameter(ArrayList<String> args){
	}
	
	

}
