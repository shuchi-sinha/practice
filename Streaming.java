import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Streaming {

    public static int[] reverseArray(int[] a) {
        int temp = 0;
        int size = a.length - 1;
        for (int i = 0; i < size / 2; i++) {
            temp = a[i];
            a[i] = a[size - i];
            a[size - i] = temp;
        }
        return a;

    }

    // customers with total score>500,000
    List<Customer> highBalance(List<Customer> customers) {
        if (customers == null || customers.size() <= 0)
            return null;
        return customers.stream().filter(customer -> customer.getBalance() > 4000).collect(Collectors.toList());

    }

    // 100 customers with highest balance
    List<Customer> highest2(List<Customer> customers) {

        return customers.parallelStream().sorted(Comparator.comparing(Customer::getBalance, Comparator.reverseOrder()))
                .limit(2).toList();
    }

    // get customers having similar balance
    List<Customer> similarBalance(List<Customer> customers) {
        customers.sort(Comparator.comparing(Customer::getBalance));
        List<Customer> sb = new ArrayList<Customer>(customers.size());
        customers.stream().collect(Collectors.groupingBy(Customer::getBalance))
                .forEach((bal, names) -> {
                    if (names.size() > 1) {
                        sb.addAll(names);
                    }
                });

        Map<Integer, List<String>> customerNamesByBalance = customers.stream()
                .collect(Collectors.groupingBy(Customer::getBalance,
                        Collectors.mapping(Customer::getName, Collectors.toList())));

        customerNamesByBalance.forEach((balance, customerNames) -> {
            if (customerNames.size() > 1) {
                System.out.println("Customers with balance " + balance + ": " + customerNames);
            }
        });
        return sb;
    }

    // get add fees or waive fees customers
    void feesCustomers(List<Customer> customers) {
        System.out.println("Waive fees");
        System.out.println("-----------");
        customers.parallelStream().filter(Objects::nonNull).filter(c -> c.getBalance() >= 1000).filter(Objects::nonNull)
                .forEach(c -> System.out.print(c.toString() + " "));
        System.out.println("Add fees");
        System.out.println("-----------");
        customers.parallelStream().filter(Objects::nonNull).filter(c -> c.getBalance() < 1000).filter(Objects::nonNull)
                .forEach(c -> System.out.println(c.toString() + " "));
        Map<Boolean, List<Customer>> passed = customers.parallelStream()
                .collect(Collectors.partitioningBy(c -> c.getBalance() > 1000));
        System.out.println(passed.toString());
        // ((Collection<Customer>) passed).stream().forEach(System.out::println);

    }

    // customer with longest name
    Optional<String> longestNameCustomer(List<Customer> customers) {
        return customers.stream().map(Customer::getName)
                .reduce((name1, name2) -> name1.length() > name2.length() ? name1 : name2);
    }

    public static void main(String args[]) {
        int[] a = { 2, 3, 4, 6, 1, 0, 23, 19 };// Arrays.asList
        int[] ra = reverseArray(a);
        Arrays.stream(ra).forEach(it -> System.out.print(it + " "));
        System.out.println(" square of numbers > 25");
        Function<Integer, Integer> TO_SQUARE = num -> num * num;
        Predicate<Integer> filter_G25 = num -> num > 25;
        Integer[] as = { 2, 3, 4, 6, 1, 0, 23, 19 };
        Arrays.stream(as).map(TO_SQUARE).filter(filter_G25).collect(Collectors.toList())
                .forEach(x -> System.out.print(x + " "));
        Customer c1 = new Customer(10000, "s1");
        Customer c2 = new Customer(19, "s22");
        Customer c3 = new Customer(2000, "s3");
        Customer c4 = new Customer(19, "s4");
        Streaming sm = new Streaming();
        List<Customer> input = Arrays.asList(c1, c2, c3, c4);
        System.out.print("Customer with balance >4000");
        sm.highBalance(input).stream().map(c -> c + " ").forEach(System.out::print);
        System.out.println("2 Customers with highest balance :");
        sm.highest2(input).stream().forEach(c -> System.out.print(c + " "));
        System.out.println("Customers with similar balance :");
        System.out.println(sm.similarBalance(input).toString());
        System.out.print("Customer having longest name ");
        System.out.println(sm.longestNameCustomer(input));

    }
}