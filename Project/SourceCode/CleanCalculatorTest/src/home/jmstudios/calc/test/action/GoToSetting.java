package home.jmstudios.calc.test.action;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;

public class GoToSetting extends Action{

	public int execute(){		
		super.execute();
		
		//Unlock the lock screen
		solo.unlockScreen();
		
		String idString = mConstantsMap.get("ID_ACTIONBAR");
		ViewGroup actionBar = (ViewGroup)solo.getView(idString);
		View workflowButton = actionBar.getChildAt(1);
		solo.clickOnView(workflowButton);
		
		idString = mConstantsMap.get("TEXT_SETTING_BUTTON");
		solo.clickOnText(idString);
		
		idString = mConstantsMap.get("ACTIVITY_SETTINGS");
		solo.assertCurrentActivity("Go to setting failed!", idString);
		
		return 0;
	}
	
	public void setParameter(ArrayList<String> args){

	}
	
	

}
