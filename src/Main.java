import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Введите выражение:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println(calc(input));
    }

    public static String calc(String input) throws IOException {

        String[] arr = input.split(" ");

        if (arr.length == 1) {
            throw new IOException ("строка не является математической операцией");
        }

        String signs = "+,-,*,/";
        String result = "";

        if (signs.contains(arr[1])) {
            if (arr.length < 3) {
                throw new IOException ("строка не является математической операцией");
            } else if (arr.length > 3) {
                throw new IOException ("формат математической операции не удовлетворяет заданию - " +
                        "два операнда и один оператор (+, -, /, *)");
            } else {
                checkTheInput(arr);
            }

            // если ввели некорректный операнд
        } else {
            throw new IOException ("к вводу допускаются лишь такие операторы, как: +, -, /, *");
        }

        return result;
    }

    public static void checkTheInput(String[] arr) throws IOException {

        String romansUpToTen = "I, II, III, IV, V, VI, VII, VIII, IX, X";
        String arabicUpToTen = "0,1,2,3,4,5,6,7,8,9,10";

        String firstInt = arr[0];
        String secondInt = arr[2];

            // если ввели римские числа
        if (romansUpToTen.contains(firstInt) && romansUpToTen.contains(secondInt)) {
            int out = calculateResult(romanToArabic(firstInt), romanToArabic(secondInt), arr);
            System.out.println(arabiсToRoman(out));

            // если ввели арабские числа
        } else if (arabicUpToTen.contains(firstInt) && arabicUpToTen.contains(secondInt)){
            int a = Integer.parseInt(arr[0]);
            int b = Integer.parseInt(arr[2]);

            System.out.println(calculateResult(a, b,arr));

            // если ввели некорректное выражение
        } else {
            throw new IOException ("к вводу допускаются числа от 1 до 10: либо два арабских числа, либо два римских");
        }

    }

    public static int romanToArabic(String romans) {

        TreeMap <Character, Integer> romanMap = new TreeMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);

        int lastChar = romans.length() - 1;
        char[] romanArr = romans.toCharArray();
        int arabiс;
        int result = romanMap.get(romanArr[lastChar]);
        for (int i = lastChar - 1; i >= 0; i--) {
            arabiс = romanMap.get(romanArr[i]);

            if (arabiс < romanMap.get(romanArr[i + 1])) {
                result -= arabiс;
            } else {
                result += arabiс;
            }
        }

        return result;
    }

    public static int calculateResult(int a, int b, String[] arr) {

        String sign = arr[1];

        int output = 0;

        switch (sign) {

            case "+":
                output = a + b;
                break;

            case "-":
                output = a - b;
                break;

            case "*":
                output = a * b;
                break;

            case "/":
                output = a / b;
                break;
        }

        return output;
    }

    public static String arabiсToRoman(int num) {

        TreeMap <Integer, String> arabicMap = new TreeMap<>();
        arabicMap.put(100, "C");
        arabicMap.put(90, "XC");
        arabicMap.put(50, "L");
        arabicMap.put(40, "XL");
        arabicMap.put(10, "X");
        arabicMap.put(9, "IX");
        arabicMap.put(5, "V");
        arabicMap.put(4, "IV");
        arabicMap.put(1, "I");

        String result = "";
        int arabicNum;
        do {
            arabicNum = arabicMap.floorKey(num);
            result += arabicMap.get(arabicNum);
            num -= arabicNum;
        } while (num != 0);

        return result;
    }
}
