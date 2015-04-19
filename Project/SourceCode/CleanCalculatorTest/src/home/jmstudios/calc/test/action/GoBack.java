package home.jmstudios.calc.test.action;

import java.util.ArrayList;

public class GoBack extends Action{

	public int execute(){		
		super.execute();
		
		//Unlock the lock screen
		solo.goBack();
		
		return 0;
	}
	
	public void setParameter(ArrayList<String> args){
		
	}
	
	

}
