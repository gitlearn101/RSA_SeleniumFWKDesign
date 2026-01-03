package home.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

// It would be better to add getJsonDataToMap() to BaseTest class. It is becuase we don't have to create object in each TC to access getJsonDataToMap()

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		
		// read JSON to String
		String jsonContent = FileUtils.readFileToString(new File (System.getProperty("user.dir")+"\\src\\test\\java\\home\\data\\PurchaseOrder.json"),StandardCharsets.UTF_8);
		
		// String to Hashmap via Jackson Bind
		ObjectMapper mapper = new ObjectMapper();
		
		// data variable will have {map} {map2} {map3} and so
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
		
		
	}
	
	
}
