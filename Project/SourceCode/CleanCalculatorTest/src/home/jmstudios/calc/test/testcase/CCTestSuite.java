package home.jmstudios.calc.test.testcase;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.TestSuite;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.os.Environment;

public class CCTestSuite extends TestSuite{

	final private static String TAG_TESTCASE = "TestCase";
	final private static String ATTR_NAME = "name";
	private ArrayList<CCTestCase> mTestCases;
	
	public CCTestSuite(){
		super();
	}
	
	public ArrayList<CCTestCase> getTestCases(){
		return mTestCases;
	}
	
	public CCTestSuite(String filePath) throws Exception{
		mTestCases = new ArrayList<CCTestCase>();
		
		//Process TestCase xml file		
		File f=new File(filePath); 
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder=factory.newDocumentBuilder(); 
		Document doc = builder.parse(f); 
		NodeList caseList = doc.getElementsByTagName(TAG_TESTCASE); 
		
		String externalStorage = Environment.getExternalStorageDirectory().getPath();
		String prePath = externalStorage + "/CCTest//TestCase/";
		
		for (int i = 0; i < caseList.getLength(); i++) {
			Node caseNode = caseList.item(i);
			Node nameAttr = caseNode.getAttributes().getNamedItem(ATTR_NAME);
			String name = nameAttr.getNodeValue();
			
			CCTestCase testCase = new CCTestCase(prePath + name);
			mTestCases.add(testCase);
		}
		
	}
	

}
