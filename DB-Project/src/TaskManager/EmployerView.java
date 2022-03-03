package TaskManager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class EmployerView implements ViewInterface {

	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		
		switch(operationName) {
		case "select": return selectOperation(modelData);	
		case "insert": return insertOperation(modelData);	
		case "update": return updateOperation(modelData);	
		case "delete": return deleteOperation(modelData);	
		case "select.gui": return selectGUI(modelData);
		case "insert.gui": return insertGUI(modelData);
		case "update.gui": return updateGUI(modelData);
		case "delete.gui": return deleteGUI(modelData);
		}

                            return new ViewData("EmployerMenu", "");

	}
	
	ViewData selectOperation(ModelData modelData) throws Exception {
		ResultSet resultSet = modelData.resultSet;
		
		if (resultSet != null) {
			while (resultSet.next()) {
				// Retrieve by column name
				String name = resultSet.getString("Name");
				String surname = resultSet.getString("Surname");
				
				// Display values
				System.out.print(name + "\t");
				System.out.print(surname + "\t");
                                System.out.println();
                                
                                if(name.equals(" ") && surname.equals(" "))
                                {
                                    System.out.println("The employer doesn't exist!");
                                    return new ViewData("Employer", "select.gui", new HashMap<>()); 
                                }                               
                                
                                else
                                    return new ViewData("EmployerMenu", "");
                                    
 
			}
                        
			resultSet.close();
		}
                return new ViewData("Employer", "select.gui", new HashMap<>());
		
                    
	}
	
	ViewData insertOperation(ModelData modelData) throws Exception {
		System.out.println("Number of inserted rows is " + modelData.recordCount);
		
		return new ViewData("EmployerMenu", "");
	}

	ViewData updateOperation(ModelData modelData) throws Exception {
		System.out.println("Number of updated rows is " + modelData.recordCount);
		
		return new ViewData("MainMenu", "");
	}
	
	ViewData deleteOperation(ModelData modelData) throws Exception {
		System.out.println("Number of deleted rows is " + modelData.recordCount);
		
		return new ViewData("MainMenu", "");
	}	
	
	Map<String, Object> getWhereParameters() throws Exception {
		System.out.println("Filter conditions:");
		String name = getString("Name : ", true);
		String surname = getString("Surname : ", true);
		
		Map<String, Object> whereParameters = new HashMap<>();
		if (name != null) whereParameters.put("Name", name);
		if (surname != null) whereParameters.put("Surname", surname);
		
		return whereParameters;
	}
	
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
                Map<String, Object> whereParameters = (Map<String, Object>)parameters.get("whereParameters");
                if(whereParameters.get("Name") != null && whereParameters.get("Surname") != null)
                    return new ViewData("Employer", "select", parameters);
                else
                    return new ViewData("MainMenu", "");
	}

	ViewData insertGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fieldNames", "Name, Surname");

		List<Object> rows = new ArrayList<>();
		
		String name, surname;
		do
		{
			System.out.println("Fields to register: ");
			name = getString("Name : ", true);
			surname = getString("Surname : ", true);
			System.out.println();
					
			if (name != null && surname != null) {
				rows.add(new Employer(name, surname));
			}
		}
		while (name != null && surname != null);
		
		parameters.put("rows", rows);
		
		return new ViewData("Employer", "insert", parameters);
	}

	ViewData updateGUI(ModelData modelData) throws Exception {
		System.out.println("Fields to update:");
		String name = getString("Name : ", true);
		String surname = getString("Surname : ", true);
		System.out.println();
		
		Map<String, Object> updateParameters = new HashMap<>();
		if (name != null) updateParameters.put("Name", name);
		if (surname != null) updateParameters.put("Surname", surname);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("updateParameters", updateParameters);
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Employer", "update", parameters);
	}

	ViewData deleteGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Employer", "delete", parameters);
	}

	@Override
	public String toString() {
		return "Employer View";
	}		
}
