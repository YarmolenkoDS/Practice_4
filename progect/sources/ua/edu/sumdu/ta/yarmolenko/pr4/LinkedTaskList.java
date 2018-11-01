/*
 * Classname: LinkedTaskList
 *
 * Date: 2018/10/28
 *
 * Author: Dmitrij Yarmolenko
 * E-mail: dsyarmolenko@gmail.com
 *
 */

package ua.edu.sumdu.ta.yarmolenko.pr4;

import ua.edu.sumdu.ta.yarmolenko.pr4.*;

/**
 * Class LinkedTaskList describes the LinkedTaskList data type
 */
public class LinkedTaskList extends AbstractTaskList {

    private LinkedListNode firstElementOfList;

    /**
     * Constructor for creating a task list object
     */
    public LinkedTaskList() {
        super();
        this.firstElementOfList = new LinkedListNode(null);
    }

    /**
     * Method for adding non-unique tasks
     *
     * @param task is an object of type task added to task list
     */	
    public void add(Task task) {
        if (task != null) {   
            task.setTitle(START_OF_TASK_TITLE + task.getTitle());
            LinkedListNode lastElementOfList = new LinkedListNode(task);
            LinkedListNode currentElementOfList = firstElementOfList;
            while (currentElementOfList.next != null){
                currentElementOfList = currentElementOfList.next;
            }
            currentElementOfList.next = lastElementOfList;
            counterOfTasksInList++;  
        } else {
            System.out.println("Adding empty tasks is prohibited");
        }
    }

    /**
     * Method to delete all tasks equal input
     *
     * @param task is an object of type task to be deleted in the task list
     */	
    public void remove(Task task) {
        if (task != null) {
            LinkedListNode currentElementOfList = firstElementOfList;
            while (currentElementOfList.next != null) {
                if (currentElementOfList.next.listItemData.equals(task)) {
                    currentElementOfList.next = currentElementOfList.next.next;
                    currentElementOfList = currentElementOfList.next;
                    counterOfTasksInList--;
                } else {
                    currentElementOfList = currentElementOfList.next;
                }
                
            }            
        } else {
            System.out.println("Deletion empty tasks is prohibited"); 
        }
    }

    /**
     * Method to get the task with the specified number
     *
     * @param index is the task number in the list which should be returned (starting from zero)
     * @return the task whose index in the list is equal to the input
     */	
    public Task getTask(int index) {
        if ((index >= size()) || (index < 0)) {
			System.out.println("The index must be >= zero and" 
                                + "less than the number of tasks in the current list (" + size() + ")");
            return null;
		} else {
            int tempIndex = 0;
            LinkedListNode currentElementOfList = firstElementOfList.next;
            while (tempIndex != index){
                currentElementOfList = currentElementOfList.next;
                tempIndex++;
            }
            return currentElementOfList.listItemData;
        }
    }

    /**
     * The method returns an array of tasks from the list whose notification time 
     *  is between from (exclusively) and to (inclusive)
     *
     * @param from the beginning of the time span
     * @param to the ending of the time span
     * @return an array of tasks from the list whose notification time 
     *  is between from (exclusively) and to (inclusive)
     */	
    public Task[] incoming(int from, int to) {
        Task[] taskListFromTo = new Task[size()];
        int indexFromTo = 0;
        LinkedListNode currentElementOfList = firstElementOfList;
        while (currentElementOfList.next != null){
            if ((currentElementOfList.next.listItemData.isActive() == true) 
                    && (currentElementOfList.next.listItemData.nextTimeAfter(from) <= to)
                    && (currentElementOfList.next.listItemData.nextTimeAfter(from) != -1)) {
                taskListFromTo[indexFromTo] = currentElementOfList.next.listItemData;
                indexFromTo ++;
            }
            currentElementOfList = currentElementOfList.next;
        }
        Task[] tempTaskListFromTo = new Task[indexFromTo];
        for (int i = 0; i < indexFromTo; i++) {
            tempTaskListFromTo[i] = taskListFromTo[i];
        }
        taskListFromTo = tempTaskListFromTo;
        return taskListFromTo;
    }
}