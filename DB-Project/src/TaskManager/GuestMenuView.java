package TaskManager;

import java.util.*;

class GuessMenuView implements ViewInterface {

	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {

		Integer choice;
		do {
			System.out.println("1. Show all tasks");
			System.out.println("2. Show tasks");
			System.out.println("3. Quit");
			System.out.println();

			choice = getInteger("Enter your choice : ", false);
		} 
		while (choice == null || choice < 1 || choice > 3);
		
		
		Map<String, Object> userInput = new HashMap<>();
		userInput.put("guessMenuChoice", choice);
		
		switch (choice.intValue()) {
		case 1: operationName = "select"; break;
		case 2: operationName = "select.gui";	break;
		default: return new ViewData("MainMenu", "");
		}
		
                
                ModelViewControllerConsole.menu = "guest";
		return new ViewData("Task", operationName, new HashMap<>());
	}

	@Override
	public String toString() {
		return "Guess Menu View";
	}		
}

