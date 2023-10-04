
public class Calculate {
// Класс калькулятор, вызывает 4 метода +, - , *, \
    public String plus(String number1, String number2, int base) { // на входе в этот метод поступает два числа со стринг значением, так как в 16-й системе существуют буквы
        switch (base){ // на основе системы считсления переходим в нужную систему счисления
            case 2:
                Integer decimal1 = Integer.parseInt(number1, 2);
                Integer decimal2 = Integer.parseInt(number2, 2);
                int sum1 = decimal1 + decimal2;
                return Integer.toBinaryString(sum1); //перевод в двоичную систему
            case 8:
                Integer decimal3 = Integer.parseInt(number1, 8);
                Integer decimal4 = Integer.parseInt(number2, 8);
                int sum2 = decimal3 + decimal4;
                return Integer.toOctalString(sum2); //перевод в 8ю систему
            case 10:
                Integer decimal5 = Integer.parseInt(number1, 10);
                Integer decimal6 = Integer.parseInt(number2, 10);
                int sum3 = decimal5 + decimal6;
                return String.valueOf(sum3); //
            case 16:
                Integer decimal7 = Integer.parseInt(number1, 16);
                Integer decimal8 = Integer.parseInt(number2, 16);
                int sum4 = decimal7 + decimal8;
                return Integer.toHexString(sum4).toUpperCase();// перевод букв в заглавные
            default:
                throw new IllegalStateException("Система счисления не найдена - " + base);
        }
    }

    public String minus(String number1, String number2, int base) {
        switch (base){
            case 2:
                Integer decimal1 = Integer.parseInt(number1, 2);
                Integer decimal2 = Integer.parseInt(number2, 2);
                int sum1 = decimal1 - decimal2;
                return Integer.toBinaryString(sum1);
            case 8:
                Integer decimal3 = Integer.parseInt(number1, 8);
                Integer decimal4 = Integer.parseInt(number2, 8);
                int sum2 = decimal3 - decimal4;
                return Integer.toOctalString(sum2);
            case 10:
                Integer decimal5 = Integer.parseInt(number1, 10);
                Integer decimal6 = Integer.parseInt(number2, 10);
                int sum3 = decimal5 - decimal6;
                return String.valueOf(sum3);
            case 16:
                Integer decimal7 = Integer.parseInt(number1, 16);
                Integer decimal8 = Integer.parseInt(number2, 16);
                int sum4 = decimal7 - decimal8;
                return Integer.toHexString(sum4).toUpperCase();
            default:
                throw new IllegalStateException("Система счисления не найдена - " + base);
        }
    }

    public String multiply(String number1, String number2, int base) {
        switch (base){
            case 2:
                Integer decimal1 = Integer.parseInt(number1, 2);
                Integer decimal2 = Integer.parseInt(number2, 2);
                int sum1 = decimal1 * decimal2;
                return Integer.toBinaryString(sum1);
            case 8:
                Integer decimal3 = Integer.parseInt(number1, 8);
                Integer decimal4 = Integer.parseInt(number2, 8);
                int sum2 = decimal3 * decimal4;
                return Integer.toOctalString(sum2);
            case 10:
                Integer decimal5 = Integer.parseInt(number1, 10);
                Integer decimal6 = Integer.parseInt(number2, 10);
                int sum3 = decimal5 * decimal6;
                return String.valueOf(sum3);
            case 16:
                Integer decimal7 = Integer.parseInt(number1, 16);
                Integer decimal8 = Integer.parseInt(number2, 16);
                int sum4 = decimal7 * decimal8;
                return Integer.toHexString(sum4).toUpperCase();
            default:
                throw new IllegalStateException("Система счисления не найдена - " + base);
        }
    }

    public String divide(String number1, String number2, int base) {
        switch (base){
            case 2:
                Integer decimal1 = Integer.parseInt(number1, 2);
                Integer decimal2 = Integer.parseInt(number2, 2);
                int sum1 = decimal1 / decimal2;
                return Integer.toBinaryString(sum1);
            case 8:
                Integer decimal3 = Integer.parseInt(number1, 8);
                Integer decimal4 = Integer.parseInt(number2, 8);
                int sum2 = decimal3 / decimal4;
                return Integer.toOctalString(sum2);
            case 10:
                Integer decimal5 = Integer.parseInt(number1, 10);
                Integer decimal6 = Integer.parseInt(number2, 10);
                int sum3 = decimal5 / decimal6;
                return String.valueOf(sum3);
            case 16:
                Integer decimal7 = Integer.parseInt(number1, 16);
                Integer decimal8 = Integer.parseInt(number2, 16);
                int sum4 = decimal7 / decimal8;
                return Integer.toHexString(sum4).toUpperCase();
            default:
                throw new IllegalStateException("Система счисления не найдена - " + base);
        }
    }
}
