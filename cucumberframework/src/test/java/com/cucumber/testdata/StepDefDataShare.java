package com.cucumber.testdata;

import java.util.HashMap;
import java.util.Map;

public class StepDefDataShare {
	
	private Map<String,Object> dataShare;
	
	public StepDefDataShare()
	{
		dataShare = new HashMap<String,Object>();
	}
	
	public Object getData(String dataKey)
	{
		return dataShare.get(dataKey);
	}
	
	public void setData(String dataKey,Object dataValue)
	{
		dataShare.put(dataKey.toString(), dataValue);
	}
	
	public boolean isDataContains(String dataKey)
	{
		return dataShare.containsKey(dataKey.toString());
	}
	
	public void removeData(String dataKey)
	{
		dataShare.remove(dataKey.toString());
	}

}
