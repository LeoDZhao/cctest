package home.jmstudios.calc.test.action;

import home.jmstudios.calc.test.TestConst;

import java.util.ArrayList;

import android.view.View;
import android.widget.EditText;

public class EnterAdvancedExpression extends Action{

	public int execute(){		
		super.execute();
		
		//Unlock the lock screen
		solo.unlockScreen();
		
		String idString = mConstantsMap.get("ID_SLIDING_DRAWER");
		View slidingDrawer = solo.getView(idString);
		idString = mConstantsMap.get("ID_HANDLE");
		View handleView = solo.getView(idString);
		
		//Bring up the slider
		int[] locationOfHandle = new int[2];
		handleView.getLocationOnScreen(locationOfHandle);
		
		float fromX = locationOfHandle[0] + handleView.getWidth()/2;
		float fromY = locationOfHandle[1] + handleView.getHeight()/2;		
		float toX = fromX;
		float toY = fromY - slidingDrawer.getHeight()/2;		
		int stepCount = Math.max((int) Math.abs(fromY-toY)/50, 1);
		
		solo.drag(fromX, toX, fromY, toY, stepCount);
		

		//Click Button according to the inputExpression
		solo.clickOnView(solo.getButton(mInputExpression));
		solo.sleep(1000);
		

		idString = mConstantsMap.get("ID_RESULT_EDITTEXT");
		String resultString = ((EditText)solo.getView(idString)).getText().toString();
		junit.framework.Assert.assertEquals("Envanced calculation failed!", mExpectedResult, resultString);
		
		//Hide the slider
		handleView.getLocationOnScreen(locationOfHandle);		
		fromX = locationOfHandle[0] + handleView.getWidth()/2;
		fromY = locationOfHandle[1] + handleView.getHeight()/2;
		toX = fromX;
		toY = fromY + slidingDrawer.getHeight()/2;
		solo.drag(fromX, toX, fromY, toY, stepCount);
		
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
