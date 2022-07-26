import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        SavingsAccount savingsAccount = new SavingsAccount();
        List<Thread> threads = new ArrayList<>();

        long[] deposits = { 1000, 1000, 1000, 1000, 1000 };
        long[] withdraws = { 100000, -1, 0, 500 };
        Arrays.stream(deposits).forEach(deposit -> threads.add(new Thread(() -> savingsAccount.deposit(deposit))));
        Arrays.stream(withdraws).forEach(withdraw -> threads.add(new Thread(() -> savingsAccount.withdraw(withdraw))));
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(savingsAccount.getTotal());
        return;
    }
}
