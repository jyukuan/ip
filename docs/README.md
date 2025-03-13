# DeepSeeker User Guide
*Organize tasks effortlessly with a chatbot that understands you!*

**DeepSeeker** is an intelligent conversational task manager designed for efficiency-driven users. Through natural language commands, you can effortlessly create todos, set deadlines, schedule events, and its minimalist CLI delivers operations for marking/unmarking tasks, keyword searches, and batch management, while all data is backed up via local storage – fully functional offline.


---

##  **Quick Start Guide**

1. **Download**: Clone this repository to your local machine.
2. **Run**: Execute `Duke.java` to launch DeepSeeker.
3. **Interact**: Type commands to add, manage, or track tasks.
4. **Auto-Save**: All tasks are saved in `data/tasks.txt` and reloaded on restart.

---

##  **Core Features**
## Add Tasks

You can add different types of tasks using the following command formats:

### 1. Todo Task
- **The description of tasks should not be empty.**
- **Format:** `todo <description>`
- **Example:** `todo Buy groceries`
  

### 2. Deadline Task
- **Deadline tasks require a by <time> clause.**
- **Format:** `deadline <description> /by <time>`
- **Example:** `deadline Submit report /by 2023-12-31`
  


### 3. Event Task
- **Event tasks must have both /from <start> and /to <end> clauses**
- **Format:** `event <description> /from <start> /to <end>`
- **Example:** `event Conference /from 9am /to 5pm`


***Use these commands to efficiently add, manage, and track your tasks.***

---

###  **View All Tasks**
- **List all tasks**:\
  ***Displays all tasks in order.***
  ```bash
  list
  1. [T][ ] Buy groceries
  2. [D][ ] Submit report (by: 2023-12-31)
  3. [E][ ] Conference (from: 9am to: 5pm)
  ```
- **Mark/Unmark Tasks**:
  1. ***Mark as done***： 
      ```bash
      mark <task-number>
      ```
     Example: mark 2 → Marks task 2 as completed

  2. ***Unmark***：
     ```bash
     unmark <task-number>
     ```
     Example: unmark 2 → Marks task 2 as completed

- **Delete Tasks**:\
  ***Removes a specific task by index.***
     ```bash
     delete <task-number>
     ```
    Example: delete 3 → Deletes the third task

- **Search Tasks**:\
  ***Search for tasks containing a keyword***
     ```bash
     find <keyword>
     ```
  Example: find groceries → Lists all tasks containing "groceries"

- **Exit the Chatbot**:
     ```bash
     bye
     ```
# Important notes

## Task Indexing
- Tasks are numbered sequentially starting from **1**.  
  **Example**:  
  Use `mark 1` to target the first task, `delete 3` for the third task.

---

## **Error Handling**

DeepSeeker provides detailed error messages to guide users in entering commands correctly and fixing errors quickly.

### **Common Error Messages**
| Error Type | Displayed Error Message |
|------------|------------------------------------------------|
| **Missing Todo Description** | `Error: The description of a todo cannot be empty.` |
| **Missing Deadline Time** | `Error: Invalid deadline format. Use: deadline <desc> /by <time>` |
| **Missing Event Time Range** | `Error: Invalid event format. Use: event <desc> /from <start> /to <end>` |
| **Invalid Task Index** | `Error: Task index out of bounds.` |
| **Task Index Format Error** | `Error: Invalid task index. Please provide a valid number.` |
| **Deleting Task Without Specifying Index** | `Error: Please specify the task number to delete.` |
| **Finding Tasks Without a Keyword** | `Error: Please specify a keyword to search for.` |
| **Unknown Command** | `Error: I'm sorry, but I don't know what that means.` |

---

## Auto-Save
- All tasks are **automatically saved** after every change.
- No manual save command is required.
- Data is preserved even if the app crashes or closes unexpectedly.

  
