class DataProcessor(private val numberOfWorkers: Int) {
    private val taskQueue = TaskQueue<() -> Unit>()
    private val workers = mutableListOf<WorkerThread>()

    init {
        repeat(numberOfWorkers) {
            val worker = WorkerThread(taskQueue)
            workers.add(worker)
            worker.start()
        }
    }
    fun addTask(task: () -> Unit) {
        taskQueue.addTask(task)
        println("Задание добавлено ${Thread.currentThread().name}")
    }

    fun stopAllWorkers() {
        workers.forEach { it.stopWorker() }
        println("Все работники остановлены")
    }
}
