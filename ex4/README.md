## Exercise 4 - Simple Todo List

Todo list application that permits the creation and deletion of tasks (texts).

- The application must persist the tasks between executions;
- Use any storage you want: database, files, PaaS backends (Firebase, etc.).

### Solution

A command line interface was created for this exercise, and it supports the commands:

- `1`: View tasks
- `2`: Add new task
- `3`: Delete task
- `0`: Quit

When the `ToDoList` object is instantiated, a file named `tasks.txt` is created (if it doesn't exist) or read, with each line being stored in a list of strings.

Whenever this list is modified (by the `addTask()` or `deleteTask()` methods), `store()` is also called to persist the contents of the list to disk.

### Try it yourself

1. Compile: `kotlinc ex4/ex4.kt -include-runtime -d ex4/ex4.jar`;
2. Then run: `java -jar ex4/ex4.jar`.

![example](example.gif)