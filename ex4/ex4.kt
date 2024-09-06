package ex4

import java.io.File

internal class ToDoList(filePath: String) {
    private val file = File(filePath.trim())
    private val tasks = read().toMutableList()

    fun tasksSize(): Int = tasks.size

    fun printTasks() {
        if (tasks.size > 0)
            for (i in tasks.indices)
                println("${i + 1}. ${tasks[i]}")
        else
            println("You don't have any tasks. Type '2' to create your first")
    }

    fun addTask() {
        print("Type your task: ")
        val task = readln().trim()
        tasks.add(task)
        store()
    }

    fun deleteTask() {
        print("Type the task index: ")
        when (val index = readln().trim().toIntOrNull()) {
            null -> println("[!] Invalid number. Type '1' to view your tasks")
            !in 1..tasks.size -> println("[!] Invalid range. Type '1' to view your tasks")
            else -> {
                tasks.removeAt(index - 1)
                store()
            }
        }
    }

    private fun read(): List<String> {
        if (!file.exists()) {
            file.createNewFile()
            return listOf()
        }
        return file.readLines()
    }

    private fun store() {
        file.printWriter().use { out -> tasks.forEach { task -> out.println(task) } }
    }
}

fun main() {
    var command: Int?
    val toDoList = ToDoList("tasks.txt")

    println("ToDoApp - You have ${toDoList.tasksSize()} task(s)")
    println("1. View your tasks")
    println("2. Add new task")
    println("3. Delete task")
    println("0. Quit")

    do {
        do {
            print("Type your command: ")
            command = readln().trim().toIntOrNull()
            if (command == null)
                println("[!] Invalid command. Type '0' to exit")
        } while (command == null)

        when (command) {
            1 -> toDoList.printTasks()
            2 -> toDoList.addTask()
            3 -> toDoList.deleteTask()
            0 -> continue
            else -> println("[!] Unknown command. Type '0' to exit")
        }
    } while (command != 0)
}