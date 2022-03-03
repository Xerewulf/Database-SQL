package TaskManager;

import java.util.*;

class MainMenuView implements ViewInterface {

	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

		Integer choice;
		do {
			System.out.println("1. Login as the employer");
                        System.out.println("2. Register as an employer");
                        System.out.println("3. Login as the guest");
                        System.out.println("4. Update an employer");
                        System.out.println("5. Delete an employer");
			System.out.println("6. Quit");
			System.out.println();

			choice = getInteger("Enter your choice : ", false);
		} 
		while (choice == null || choice < 1 || choice > 6);
		
		
		Map<String, Object> userInput = new HashMap<>();
		userInput.put("mainMenuChoice", choice);
		
		switch (choice.intValue()) {
		case 1: return new ViewData("Employer", "select.gui", new HashMap<>());
		case 2: return new ViewData("Employer", "insert.gui", new HashMap<>());
                case 3: return new ViewData("GuestMenu", "");
		case 4: return new ViewData("Employer", "update.gui", new HashMap<>());
		case 5: return new ViewData("Employer", "delete.gui", new HashMap<>());
		default: return new ViewData(null, null);
		}
	
	}

	@Override
	public String toString() {
		return "Main Menu View";
	}		
}
