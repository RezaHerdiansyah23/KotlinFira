data class Task(val id: Int, val title: String, val isCompleted: Boolean = false)

class TaskManager(private val tasks: List<Task> = emptyList()) {
    fun addTask(title: String): TaskManager {
        val newTask = Task(getNextId(), title)
        return TaskManager(tasks + newTask)
    }

    fun completeTask(id: Int): TaskManager {
        val updatedTasks = tasks.map { if (it.id == id) it.copy(isCompleted = true) else it }
        return TaskManager(updatedTasks)
    }

    fun getPendingTasks(): List<Task> {
        return tasks.filterNot(Task::isCompleted)
    }

    fun getCompletedTasks(): List<Task> {
        return tasks.filter(Task::isCompleted)
    }

    private fun getNextId(): Int {
        return tasks.maxByOrNull(Task::id)?.id?.plus(1) ?: 1
    }
}

fun main() {
    var taskManager = TaskManager()

    while (true) {
        println("1. Tambah Tugas")
        println("2. Tandai Selesai")
        println("3. Tugas yang Belum Selesai")
        println("4. Tugas yang Sudah Selesai")
        println("5. Keluar")
        print("Pilih tindakan: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("Judul tugas: ")
                val title = readLine() ?: ""
                taskManager = taskManager.addTask(title)
                println("Tugas berhasil ditambahkan!")
            }
            2 -> {
                print("Masukkan ID tugas yang selesai: ")
                val id = readLine()?.toIntOrNull() ?: 0
                taskManager = taskManager.completeTask(id)
                println("Tugas ditandai selesai!")
            }
            3 -> {
                val pendingTasks = taskManager.getPendingTasks()
                displayTasks("Tugas yang Belum Selesai", pendingTasks)
            }
            4 -> {
                val completedTasks = taskManager.getCompletedTasks()
                displayTasks("Tugas yang Sudah Selesai", completedTasks)
            }
            5 -> {
                println("Terima kasih!")
                return
            }
            else -> println("Pilihan tidak valid.")
        }
    }
}

fun displayTasks(title: String, tasks: List<Task>) {
    if (tasks.isEmpty()) {
        println("$title: Tidak ada tugas.")
    } else {
        println("$title:")
        tasks.forEach { task ->
            val status = if (task.isCompleted) "[Selesai]" else "[Belum Selesai]"
            println("${task.id}. ${task.title} $status")
        }
    }
}
