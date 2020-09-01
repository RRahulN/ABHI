package util;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class FilloUtil {
	
	
	Recordset recordset;
	Connection connection;
	
	

	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	    public static Recordset getEntityObject(Fillo fillo,Connection connection,String Path,String query) throws FilloException {
	        Recordset recordset = null;
	        fillo.getConnection(Path);
	        try {
	            recordset = connection.executeQuery(query);
	        } catch (FilloException e) {
	            e.printStackTrace();
	        }
	        return recordset;
	    }
	   

	    public String getStringValueForField(String FieldName) {
	        String testData = null;
	        try {
	            testData = recordset.getField(FieldName);
	        } catch (FilloException e) {
	            e.printStackTrace();
	        }
	        return testData;
	    }

	    public String setStringValueForField(String name, String value) {
	        return value;
	    }

	    public String getAction() {
	        return getStringValueForField("Action");
	    }
	
}
