import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SavingsAccount {

	private final Lock lock = new ReentrantLock();

	private long total = 0;

	public boolean withdraw(long amount) {
		try {
			lock.lock();
			if (amount > total || amount < 0)
				return false;
			total = total - amount;
			return true;
		} finally {
			lock.unlock();
		}
	}

	public void deposit(long amount) {
		try {
			lock.lock();
			total = total + amount;
		} finally {
			lock.unlock();
		}
	}

	public long getTotal() {
		try {
			lock.lock();
			return total;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		SavingsAccount sa = new SavingsAccount();
		sa.deposit(1);
		System.out.println(sa.withdraw(2));
		System.out.println(sa.withdraw(-1));
		System.out.println(sa.withdraw(1));

	}
}