import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class TaskQueue<T> {
    private val queue: BlockingQueue<T> = LinkedBlockingQueue()

    fun addTask(task: T) {
        queue.put(task)
    }

    fun getTask(): T? {
        return queue.take()
    }

}
