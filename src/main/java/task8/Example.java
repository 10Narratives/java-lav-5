package task8;

import java.util.concurrent.locks.ReentrantLock;

public class Example {
    private final ReentrantLock myLock = new ReentrantLock();

    public void performTask() {
        try (AutoCloseable ignored = LockUtil.lock(myLock)) {
            System.out.println("Задача выполняется под блокировкой");
        } catch (Exception _) {
        }
    }
}