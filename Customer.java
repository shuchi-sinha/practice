public class Customer {
private int balance;
private String name;

public Customer(int balance, String name) {
    this.balance = balance;
    this.name = name;
}

@Override
public String toString() {
    return "Customer [balance=" + balance + ", name=" + name + "]";
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public int getBalance() {
    return balance;
}

public void setBalance(int balance) {
    this.balance = balance;
}

}