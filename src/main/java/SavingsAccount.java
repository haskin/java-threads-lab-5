import java.util.concurrent.locks.ReentrantLock;

public class SavingsAccount {

	private final ReentrantLock lock = new ReentrantLock();

	private long total = 0;

	public boolean withdraw(long amount) {
		while (lock.isLocked()) {
		}
		lock.lock();
		try {
			if (amount > total || amount < 0)
				return false;

			total = total - amount;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			lock.unlock();
		}
	}

	public void deposit(long amount) {
		while (lock.isLocked()) {
		}
		lock.lock();
		try {
			total = total + amount;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public long getTotal() {
		return total;
	}

	public static void main(String[] args) {
		SavingsAccount sa = new SavingsAccount();
		sa.deposit(1);
		System.out.println(sa.withdraw(2));
		System.out.println(sa.withdraw(-1));
		System.out.println(sa.withdraw(1));

	}
}