/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TaskManager;

/**
 *
 * @author Acer
 */
public class Task {
    
    int task_ID;
    String name;
    String description;
    String submission_time;

    public Task(String name, String description, String submission_time)
    {
        this.name = name;
        this.description = description;
        this.submission_time = submission_time;
        this.task_ID = getRandomInteger(100000000, 1000);
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getSubmission_time() {
        return submission_time;
    }

    public int getTask_ID() {
        return task_ID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubmission_time(String submission_time) {
        this.submission_time = submission_time;
    }

    public void setTask_ID(int task_ID) {
        this.task_ID = task_ID;
    }
    
    public Object getByName(String attributeName) 
    {
        switch (attributeName) {
	    case "Task_ID": return task_ID;
	    case "Name": return name;
            case "Description": return description;
            case "Submission_Time": return submission_time;
	    default: return null;
	}
    }
    
    
    public static int getRandomInteger(int maximum, int minimum)
    {
        return ((int) (Math.random()*(maximum - minimum))) + minimum; 
    }
    
}
