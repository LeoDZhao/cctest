package com.leo.android.automation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class Controller {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		//1. Push files to device
		System.out.println("Step1: push test files to device");
		
		String projectFolder = ".";
		if (args.length>0) {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-p")) {
					projectFolder = args[i+1];
					if (projectFolder.isEmpty()) {
						projectFolder = ".";
					}
				}
			}
		}
		projectFolder = projectFolder.replace('\\', '/');
		String ccTestFolder = projectFolder + "/CCTest";
		String ccTestDeviceFolder = "/sdcard/CCTest";
		
		
		
		String cmd = "adb push " + ccTestFolder + " " + ccTestDeviceFolder;
		System.out.println(cmd);
		Process process= Runtime.getRuntime().exec(cmd);
		int result  = process.waitFor();
		System.out.println("result: " + result);
		
		//2. Uninstall apks
		System.out.println("Step2: uninstall apks");
		
		String targetPackage = "home.jmstudios.calc";
		String testPackageString = "home.jmstudios.calc.test";
		String[] packages = {targetPackage, testPackageString};
		for (int i = 0; i < packages.length; i++) {
			process = Runtime.getRuntime().exec("adb uninstall " + packages[i]);
			result  = process.waitFor();
			System.out.println("uninstall result for " + packages[i] + " : " + result);
		}
		
		//3. Resign and install apks 
		System.out.println("Step3: resign and install apks");
		
		String apkFolder = projectFolder + "/APK";
		String targetApk = apkFolder + "/CleanCalculator.apk";
		String testApk = apkFolder +"/CleanCalculatorTest.apk";
		File targetApkFile = new File(targetApk);
		File testApkFile = new File(testApk);
		
		String[] f = {"META-INF"};
		try {			
			ZipUtils.deleteZipEntry(targetApkFile, f);
			ZipUtils.deleteZipEntry(testApkFile, f);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		try {
			// Write content of debug.keystore to a temporary file
			String keystoreFile = apkFolder + "/debug.keystore";
			InputStream is = new FileInputStream(keystoreFile);
			BufferedInputStream  br = new BufferedInputStream (is);  
			File tempKeyStoreFile = File.createTempFile("debug", ".keystore");
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tempKeyStoreFile));  
			int c;
			while ((c = br.read()) != -1) {
				bos.write(c);
			}
			bos.flush();
			bos.close();
			br.close();
			
			// Execute jarsigner to re-sign apk file
			String[] apks = {targetApk, testApk};
			for (int i = 0; i < apks.length; i++) {
				process = Runtime.getRuntime().exec("jarsigner -digestalg SHA1 -sigalg MD5withRSA -keystore \"" + tempKeyStoreFile.getAbsolutePath() + "\" -storepass android -keypass android \"" + apks[i] + "\" android");
				result  = process.waitFor();
				System.out.println("resign result for " + apks[i] + " : " + result);
				
				process = Runtime.getRuntime().exec("adb install " + apks[i]);
				result  = process.waitFor();
				System.out.println("install result for " + apks[i] + " : " + result);
				
			}
			
			
			// Delete temp file
			tempKeyStoreFile.delete();
			tempKeyStoreFile.deleteOnExit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//4. Start Testing
		System.out.println("Step4: start instrumentation testing");
		process = Runtime.getRuntime().exec("adb shell am instrument -w -e class home.jmstudios.calc.test.MyTestSuite home.jmstudios.calc.test/com.zutubi.android.junitreport.JUnitReportTestRunner");
		System.out.println("Waiting for testing done...");
		result  = process.waitFor();
		System.out.println("Instrumentation testing result: " + result);
		
		//5. Pull back result file
		System.out.println("Step5: pull back result file");
		process = Runtime.getRuntime().exec("adb pull /data/data/home.jmstudios.calc/files/junit-report.xml " + projectFolder + "/TestResult.xml");
		result  = process.waitFor();
		System.out.println("Instrumentation testing result: " + result);
		
		
		
	}	
	

}

 

