import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Calculate calculate = new Calculate();
        Scanner in = new Scanner(System.in);

        System.out.println("Введите систему счисления (Доступны значения - 2, 8, 10, 16): ");
        int base = in.nextInt();
        System.out.println("Введите первое число: ");
        String num1 = in.next();
        System.out.println("Введите второе число: ");
        String num2 = in.next();
        System.out.println("Введите арифметическую операцию (Доступны значения +, -, *, /): ");
        String operation = in.next();

        switch (operation){
            case "+":
                System.out.println(calculate.plus(num1, num2, base));
                break;
            case "-":
                System.out.println(calculate.minus(num1, num2, base));
                break;
            case "*":
                System.out.println(calculate.multiply(num1, num2, base));
                break;
            case "/":
                System.out.println(calculate.divide(num1, num2, base));
            default:
                throw new IllegalStateException("Данная операция не найдена - " + operation);
        }
    }
}
