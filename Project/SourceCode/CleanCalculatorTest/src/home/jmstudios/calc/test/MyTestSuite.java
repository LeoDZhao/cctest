package home.jmstudios.calc.test;

import home.jmstudios.calc.test.testcase.CCTestCase;
import home.jmstudios.calc.test.testcase.CCTestSuite;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.os.Environment;

import junit.framework.Test;
import junit.framework.TestSuite;

public class MyTestSuite {
	
	private static CCTestSuite mCCSuite;	
	private static HashMap<String, String> mConstantsMap = new HashMap<String, String>();

	public static CCTestSuite getCCSuite() {
		return mCCSuite;
	}
	
	public static HashMap<String, String> getConstantMap() {
		return mConstantsMap;
	}
	
	public static Test suite() throws Exception {
		
		TestSuite suite = new TestSuite("All Tests"); 
		
		String externalStore = Environment.getExternalStorageDirectory().getPath();
		
		//Read Constants.xml
		String constPath = externalStore +"/CCTest/Constants.xml"; 
		genConstantsMap(constPath);
		
		//Read TestSuite.xml		
		String suitePath = externalStore +"/CCTest/TestSuite.xml";
		mCCSuite = new CCTestSuite(suitePath);		
		ArrayList<CCTestCase> myCases = mCCSuite.getTestCases();
	      
		for (int i = 0; i < myCases.size(); i++) {
			suite.addTestSuite(CCTest.class);  
		}

		return suite;	
    }
	
	private static void genConstantsMap(String file) throws Exception{
		//Process Constants.xml file		
		File f=new File(file); 
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder=factory.newDocumentBuilder(); 
		Document doc = builder.parse(f); 
		NodeList itemList = doc.getElementsByTagName("Item"); 
		
		for (int i = 0; i < itemList.getLength(); i++) {
			Node itemNode = itemList.item(i);
			Node keyAttr = itemNode.getAttributes().getNamedItem("key");
			String keyString = " ";
			if (keyAttr!=null) {
				keyString = keyAttr.getNodeValue();
			}
			
			Node textNode = itemNode.getFirstChild();
			String valueString =" ";
			if (textNode!=null) {
				valueString = textNode.getNodeValue();
			}
			
			mConstantsMap.put(keyString, valueString);	
		}
	}
	
	public static void main(String args[]) throws Exception {
        junit.textui.TestRunner.run(suite());
    }


	


}
