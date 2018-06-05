package UtilExtra;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class propertyReader {

    static Properties properties;

    InputStream inputStream = null;

    /********************************************************
	 *Description : Constructor while create object automatically call for load all properties 
	 *Created by : Yagnesh Bhatt
	 *Created on : 6th May 2018
	 *Modified on : 6th May 2018
	 *Parameters :  No Parameters
	 *********************************************************/
    
    public propertyReader() {	
        properties = new Properties();
        loadProperties();
    }

    /********************************************************
   	 *Description : LoadProperties function load all properties using FileInputStream. 
   	 *Created by : Yagnesh Bhatt
   	 *Created on : 6th May 2018
   	 *Modified on : 6th May 2018
   	 *Parameters :  No Parameters
   	 *********************************************************/
    
    private void loadProperties() {
        try {
            inputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.print("Unable to load config.properties");
            e.printStackTrace();
        }
    }

    /********************************************************
   	 *Description : Read Property function for provide exact value of that field which we mentioned in properties file and accodingly it will provide value 
   	 *Created by : Yagnesh Bhatt
   	 *Created on : 6th May 2018
   	 *Modified on : 6th May 2018
   	 *Parameters :  No Parameters
   	 *********************************************************/
    public String readProperty(String key) {
        return properties.getProperty(key);
    }
}
