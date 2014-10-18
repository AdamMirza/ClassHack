package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

public class Application {
	private static AmazonDynamoDBClient client;
	
	public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException, IOException, JSONException
	{
		client = new AmazonDynamoDBClient(new PropertiesCredentials(new File(".aws/credentials")));
		client.setEndpoint("dynamodb.us-west-2.amazonaws.com");
		ScanRequest scanRequest = new ScanRequest("ClassData");
		ScanResult scanResult = client.scan(scanRequest);
		// System.out.println(scanResult);
		List<Map<String, AttributeValue>> items = scanResult.getItems();
		
		// Maps the Department to the Set of classes
		Map<String, Set<ClassDataManager>> data = new HashMap<String, Set<ClassDataManager>>();
			
		// For each item
		for (Map<String, AttributeValue> item : items)
		{
			String dept = "";
			// Get data from a single item
			Set<String> keys = item.keySet();
			ClassDataManager dataMgr = new ClassDataManager();
			for (String key : keys)
			{
				if (key.equalsIgnoreCase("Class Number"))
				{
					dataMgr.setCourseNumber(item.get(key).getN());
				}
				else if (key.equalsIgnoreCase("Title"))
				{
					dataMgr.setClassTitle(item.get(key).getS());
				}
				else if (key.equalsIgnoreCase("Department"))
				{
					dept = item.get(key).getS();
				}
			}
			
			if (!data.containsKey(dept))
			{
				data.put(dept, new HashSet<ClassDataManager>());
			}
			data.get(dept).add(dataMgr);
		}
		
		/*Set<JSONObject> departmentObjects = new HashSet<JSONObject>();
		
		// For every department
		for (String key : data.keySet())
		{
			JSONObject object = new JSONObject();
			JSONObject insideObject = new JSONObject();
			
			// put all of the classes in that department
			for (ClassDataManager mgr : data.get(key))
			{
				insideObject.put(mgr.getCourseNumber(), mgr.getClassTitle());
			}
			object.put(key, insideObject);
			departmentObjects.add(object);
		}
		
		System.out.println(departmentObjects);*/
		JSONObject root = new JSONObject();
		JSONArray overallList = new JSONArray();
		for (String key : data.keySet())
		{
			JSONObject dept = new JSONObject();
			JSONArray list = new JSONArray();
			
			for (ClassDataManager mgr : data.get(key))
			{
				JSONObject temp = new JSONObject();
				temp.put("course", mgr.getCourseNumber());
				temp.put("title", mgr.getClassTitle());
				list.put(temp);
			}
			dept.put(key, list);
			overallList.put(dept);
		}
		
		root.put("root", overallList);
		try {
			PrintWriter writer = new PrintWriter("/Users/adammirza/Desktop/text.json", "UTF-8");
			writer.println(root.toString());
			writer.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
