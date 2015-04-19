package home.jmstudios.calc.test;

import home.jmstudios.calc.Main;
import home.jmstudios.calc.test.testcase.CCTestCase;

import java.util.ArrayList;
import java.util.HashMap;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

public class CCTest extends ActivityInstrumentationTestCase2<Main> {
	
	private static HashMap<String, String> constantsMap;
	private static Solo solo;	
	private static int caseIndex=0;

	@SuppressWarnings("deprecation")
	//android:minSdkVersion="7", 
	//however API 7 does not support method super(activityClass),
	//So use the deprecated method super(pkg, activityClass)
	public CCTest() {
		super("home.jmstudios.calc", Main.class);
	}
	
	
	@Override
	public void setUp() throws Exception {
		//setUp() is run before a test case is started. 
		//This is where the solo object is created.
		solo = new Solo(getInstrumentation(), getActivity());
		constantsMap = MyTestSuite.getConstantMap();
	}
	
	public void testMain() throws Exception {
		
		
		ArrayList<CCTestCase> cases = MyTestSuite.getCCSuite().getTestCases();
		cases.get(caseIndex++).execute();


	}
	
	

	
	
	@Override
	public void tearDown() throws Exception {
		//tearDown() is run after a test case has finished. 
		//finishOpenedActivities() will finish all the activities that have been opened during the test execution.
		solo.finishOpenedActivities();
		
	}
	
	static public Solo getSolo(){
		return solo;
	}
	
	static public HashMap<String, String> getConstantsMap(){
		return constantsMap;
	}

/*	public void testUnit() throws Exception {	
		Action resetAction = new Reset();
		resetAction.execute();
		
		
		ArrayList<String> argsArrayList = new ArrayList<String>();
		argsArrayList.add("7*8=+3=");
		argsArrayList.add("59");		
		Action basicAction = new EnterBasicExpression();
		basicAction.setParameter(argsArrayList);
		basicAction.execute();
		
		
		Action goToSetting = new GoToSetting();
		goToSetting.setParameter(null);
		goToSetting.execute();
		
		argsArrayList.clear();
		argsArrayList.add("5");
		argsArrayList.add("5");
		Action changePrecision = new ChangePrecision();
		changePrecision.setParameter(argsArrayList);
		changePrecision.execute();
		
		
		Action goBack = new GoBack();
		goBack.execute();
		
		
		resetAction.execute();
		
		
		argsArrayList.clear();
		argsArrayList.add("1/3=");
		argsArrayList.add("0.33333");		
		basicAction.setParameter(argsArrayList);
		basicAction.execute();
		
		
		resetAction.execute();
		
		
		argsArrayList.clear();
		argsArrayList.add("sin");
		argsArrayList.add("sin()");
		Action advanceAction = new EnterAdvancedExpression();
		advanceAction.setParameter(argsArrayList);
		advanceAction.execute();
		
		argsArrayList.clear();
		argsArrayList.add("90=");
		argsArrayList.add("1");		
		basicAction.setParameter(argsArrayList);
		basicAction.execute();		

}*/

}
