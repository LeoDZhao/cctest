package home.jmstudios.calc.test.testcase;

import home.jmstudios.calc.test.action.Action;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.TestCase;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CCTestCase extends TestCase{

	final private static String TAG_ACTION = "Action";
	final private static String ATTR_CLASSPATH = "classpath";
	
	private ArrayList<Action> mActions;
	
	public CCTestCase(String filePath) throws Exception {
		mActions = new ArrayList<Action>();
				
		//Process TestCase xml file		
		File f=new File(filePath); 
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder=factory.newDocumentBuilder(); 
		Document doc = builder.parse(f); 
		NodeList actionList = doc.getElementsByTagName(TAG_ACTION); 
		
		for (int i = 0; i < actionList.getLength(); i++) {
			Node actionNode = actionList.item(i);			
			String className = actionNode.getAttributes().getNamedItem(ATTR_CLASSPATH).getNodeValue();
			
			NodeList childList = actionNode.getChildNodes();			
			ArrayList<String> argsList = new ArrayList<String>();	
			
			//Get parameters
			for (int j = 0; j < childList.getLength(); j++) {
				Node paramNode = childList.item(j);
				String nodeNmae = paramNode.getNodeName();
				if (nodeNmae.equals("Parameter")) {
					String valueString = paramNode.getFirstChild().getNodeValue();
					argsList.add(valueString);
				}
			}
			
			//Create action instance
			Action action = (Action) Class.forName(className).newInstance();
			action.setParameter(argsList);
			mActions.add(action);
		}
		
	}
	
	@Test
	public int execute() {
		
		for (int i = 0; i < mActions.size(); i++) {
			mActions.get(i).execute();
		}
		
		return 0;
	}
	
	
	
}
