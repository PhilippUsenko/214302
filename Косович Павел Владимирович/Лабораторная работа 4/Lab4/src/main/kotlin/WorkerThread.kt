class WorkerThread(private val taskQueue: TaskQueue<() -> Unit>) : Thread() {

    @Volatile
    private var isRunning = true

    override fun run() {
        while (isRunning) {
            try {
                val task = taskQueue.getTask()
                task?.let {
                    println("Задача выполняется в ${Thread.currentThread().name}")
                    it.invoke()
                }
            } catch (e: InterruptedException) {
                println("${Thread.currentThread().name} закончил выполнение.")
                if (!isRunning) {
                    break
                }
            }
        }
    }

    fun stopWorker() {
        isRunning = false
        this.interrupt()
    }
}
