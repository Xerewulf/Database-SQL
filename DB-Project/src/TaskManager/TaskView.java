package TaskManager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class TaskView implements ViewInterface {

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
		
                        if(ModelViewControllerConsole.menu.equals("employer"))
                            return new ViewData("EmployerMenu", "");
                        else
                            return new ViewData("GuestMenu", "");
	}
	
	ViewData selectOperation(ModelData modelData) throws Exception {
		ResultSet resultSet = modelData.resultSet;
		
		if (resultSet != null) {
			while (resultSet.next()) {
				// Retrieve by column name
				String name = resultSet.getString("Name");
                                String description = resultSet.getString("Description");
				String submission_time = resultSet.getString("Submission_Time");
				
				// Display values
				System.out.print(name + "\t");
				System.out.print(description + "\t");
                                System.out.print(submission_time + "\t");
                                System.out.println();
			}
			resultSet.close();
                        if(ModelViewControllerConsole.menu.equals("employer"))
                            return new ViewData("EmployerMenu", "");
                        else
                            return new ViewData("GuestMenu", "");
		}
                
                else
                    return new ViewData("Task", "select.gui", new HashMap<>());
		
                    
	}
	
	ViewData insertOperation(ModelData modelData) throws Exception {
		System.out.println("Number of inserted rows is " + modelData.recordCount);
		
		if(ModelViewControllerConsole.menu.equals("employer"))
                    return new ViewData("EmployerMenu", "");
                else
                    return new ViewData("GuestMenu", "");
                    
	}

	ViewData updateOperation(ModelData modelData) throws Exception {
		System.out.println("Number of updated rows is " + modelData.recordCount);
		
		if(ModelViewControllerConsole.menu.equals("employer"))
                    return new ViewData("EmployerMenu", "");
                else
                    return new ViewData("GuestMenu", "");
	}
	
	ViewData deleteOperation(ModelData modelData) throws Exception {
		System.out.println("Number of deleted rows is " + modelData.recordCount);
		
		if(ModelViewControllerConsole.menu.equals("employer"))
                    return new ViewData("EmployerMenu", "");
                else
                    return new ViewData("GuestMenu", "");
	}	
	
	Map<String, Object> getWhereParameters() throws Exception {
		System.out.println("Filter conditions:");
		String name = getString("Name : ", true);
		
		Map<String, Object> whereParameters = new HashMap<>();
		if (name != null) whereParameters.put("Name", name);
		
		return whereParameters;
	}
	
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
                
                return new ViewData("Task", "select", parameters);
	}

	ViewData insertGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fieldNames", "Name, Description, Submission_Time");

		List<Object> rows = new ArrayList<>();
		
		String name, description, submission_time;
		do
		{
			System.out.println("Fields to register: ");
			name = getString("Name : ", true);
			description = getString("Description : ", true);
                        submission_time = getString("Submission Time : ", true);
			System.out.println();
					
			if (name != null && description != null && submission_time != null) {
				rows.add(new Task(name, description, submission_time));
			}
		}
		while (name != null && description != null && submission_time != null);
		
		parameters.put("rows", rows);
		
		return new ViewData("Task", "insert", parameters);
	}

	ViewData updateGUI(ModelData modelData) throws Exception {
		System.out.println("Fields to update:");
		String name = getString("Name : ", true);
                String description = getString("Description : ", true);
                String submission_time = getString("Submission Time : ", true);
		System.out.println();
		
		Map<String, Object> updateParameters = new HashMap<>();
		if (name != null) updateParameters.put("Name", name);
                if (description != null) updateParameters.put("Description", description);
                if (submission_time != null) updateParameters.put("Submission_Time", submission_time);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("updateParameters", updateParameters);
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Task", "update", parameters);
	}

	ViewData deleteGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Task", "delete", parameters);
	}

	@Override
	public String toString() {
		return "Department View";
	}		
}
