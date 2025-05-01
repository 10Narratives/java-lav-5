package task8;

import java.util.concurrent.locks.ReentrantLock;
import java.io.Closeable;

public class LockUtil {
    public static AutoCloseable lock(ReentrantLock lock) {
        lock.lock();
        return lock::unlock;
    }
}