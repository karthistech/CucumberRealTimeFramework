package com.cucumber.testrunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apiguardian.api.API;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cucumber.context.TestContext;

import io.cucumber.messages.Messages.Pickle.PickleTag;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;


@API(status = API.Status.STABLE)
public class CustomAbstractTestNGCucumberTests {
	  private TestNGCucumberRunner testNGCucumberRunner;

	    @BeforeClass(alwaysRun = true)
	    public void setUpClass() {
	        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	    }

	    @SuppressWarnings("unused")
	    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
	        // the 'featureWrapper' parameter solely exists to display the feature
	        // file in a test report
	        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
	    }

	    /**
	     * Returns two dimensional array of {@link PickleWrapper}s with their
	     * associated {@link FeatureWrapper}s.
	     *
	     * @return a two dimensional array of scenarios features.
	     */
	    @DataProvider
	    public Iterator<Object[]> scenarios() {
	    	
	    	ArrayList<Object[]> modifiedTagList = new ArrayList<Object[]>();
	    	
	        if (testNGCucumberRunner == null) {
	            return modifiedTagList.iterator();
	        }
	        
	        Object data[][] = testNGCucumberRunner.provideScenarios();
	        /*
			 * PickleWrapper
			 * pickleWrapper = (PickleWrapper)data[0][0]; FeatureWrapper featureWrapper
			 * =(FeatureWrapper) data[0][1];
			 */
	        //return testNGCucumberRunner.provideScenarios();
	        modifiedTagList = fileterTheFeature(data);
	        return modifiedTagList.iterator();
	    }
	   
	    private ArrayList<Object[]> fileterTheFeature(Object[][] data)
	    {
	    	TestContext testContext = new TestContext();
	    	List<String> tagFromExcel = testContext.getTestDataReaderManager().getTestDataProvider().getFinalScenarioToTest();
	    	
	    	tagFromExcel = addAtToTheTag(tagFromExcel);
	    	if (tagFromExcel.isEmpty())
	    	{
	    		return getFeatureList(data);
	    	}
	    	
	    	ArrayList<Object[]> modifiedTagList = new ArrayList<Object[]>();
	    	
	    	if (data != null)
	    	{
	    		for(int i=0;i<data.length;i++)
	    		{
	    			PickleWrapper pickleWrapper = (PickleWrapper)data[i][0];
	    			 for (String atag : tagFromExcel)
	    			 {
	    				 if (isTagPresent(atag,pickleWrapper.getPickle().getTags()))
	    				 {
	    					 modifiedTagList.add(data[i]);
	    				 }
	    			 }
	    		}
	    	}
	    	return modifiedTagList;
	    }
	    
	    private boolean isTagPresent(String aTag,List<String> tagList)
	    {
	    	for (String pickletag : tagList)
	    	{
	    		if (aTag.equalsIgnoreCase(pickletag))
	    		{
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    
	    private ArrayList<Object[]> getFeatureList(Object data[][])
	    {
	    	ArrayList<Object[]> modifiedTagList = new ArrayList<Object[]>();
	    	if (data != null)
	    	{
	    		for(int i=0;i<data.length;i++)
	    		{
	    			modifiedTagList.add(data[i]);
	    		}
	    	}
			return modifiedTagList;
	    	
	    }
	    
	    public List<String> addAtToTheTag(List<String> tagFromExcel)
	    {
	    	List<String> tagFromExcel1 = new ArrayList<String>();
	    	tagFromExcel1.addAll(tagFromExcel);
	    	
			for(int i=0;i<tagFromExcel1.size();i++)
	    	{
				tagFromExcel1.set(i, "@".concat(tagFromExcel1.get(i)));
	    	}
			
			return tagFromExcel1;
	    }
	    
	    @AfterClass(alwaysRun = true)
	    public void tearDownClass() {
	        if (testNGCucumberRunner == null) {
	            return;
	        }
	        testNGCucumberRunner.finish();
	    }


}
