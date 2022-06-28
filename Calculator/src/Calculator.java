import java.util.ArrayList;
import java.util.Scanner;

class Calculator {
    public static void calculate(ArrayList<String> numbs_signs) {
        while (numbs_signs.size() > 1) {
            int l;

            if (numbs_signs.contains("^")) {
                l = numbs_signs.indexOf("^");
                numbs_signs.set(l, Double.toString(Math.pow(Double.parseDouble(numbs_signs.get(l - 1)), Double.parseDouble(numbs_signs.get(l + 1)))));
                numbs_signs.remove(l - 1);
                numbs_signs.remove(l);

            }

            else if (numbs_signs.contains("/") || numbs_signs.contains("*")) {
                if (numbs_signs.contains("*")) {
                    l = numbs_signs.indexOf("*");
                    numbs_signs.set(l, Double.toString(Double.parseDouble(numbs_signs.get(l - 1)) * Double.parseDouble(numbs_signs.get(l + 1))));
                    numbs_signs.remove(l - 1);
                    numbs_signs.remove(l);
                }
                else if (numbs_signs.contains("/")) {
                    l = numbs_signs.indexOf("/");
                    numbs_signs.set(l, Double.toString(Double.parseDouble(numbs_signs.get(l - 1)) / Double.parseDouble(numbs_signs.get(l + 1))));
                    numbs_signs.remove(l - 1);
                    numbs_signs.remove(l);
                }
            }

            else {
                for (int i = 1; i < numbs_signs.size(); i += 2) {
                    String sign = numbs_signs.get(i);
                    switch (sign) {
                        case "+" -> {
                            numbs_signs.set(i, Double.toString(Double.parseDouble(numbs_signs.get(i - 1)) + Double.parseDouble(numbs_signs.get(i + 1))));
                            numbs_signs.remove(i - 1);
                            numbs_signs.remove(i);
                        }
                        case "-" -> {
                            numbs_signs.set(i, Double.toString(Double.parseDouble(numbs_signs.get(i - 1)) - Double.parseDouble(numbs_signs.get(i + 1))));
                            numbs_signs.remove(i - 1);
                            numbs_signs.remove(i);
                        }
                    }
                }
            }
        }
    }
    public static void input(ArrayList<String> numbs_signs, String expression) {
        StringBuilder numb = new StringBuilder();

        for (int i = 0; i < expression.length() - 1; i++) {
            if (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.') {
                if (i == 0)
                    numb = new StringBuilder(Character.toString(expression.charAt(i)));

                numb.append(expression.charAt(i + 1));
            }

            else if (expression.charAt(i + 1) == ' ') {
                continue;
            }

            else {
                if (!Character.isDigit(expression.charAt(i + 1))) {
                    if (i == 1)
                        numb = new StringBuilder(Character.toString(expression.charAt(i - 1)));
                    if (i == 0)
                        numb = new StringBuilder(Character.toString(expression.charAt(i)));
                }

                numbs_signs.add(numb.toString());
                numbs_signs.add(Character.toString(expression.charAt(i + 1)));
                numb = new StringBuilder();
            }
        }

        numbs_signs.add(numb.toString());

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String expression = scan.nextLine();
        ArrayList<String> numbs_signs = new ArrayList<>();

        int count_parenthesis = Integer.parseInt(Long.toString(expression.chars().filter(s -> s == '(').count()));

        for (int x = 1; x <= count_parenthesis; x++) {
            ArrayList<String> numbs_signs_parenthesis = new ArrayList<>();
            String parenthesis = expression.substring(expression.indexOf("(") + 1, expression.indexOf(")"));
            input(numbs_signs_parenthesis, parenthesis);
            calculate(numbs_signs_parenthesis);
            expression = expression.substring(0, expression.indexOf("(")) + numbs_signs_parenthesis.get(0) + expression.substring(expression.indexOf(")") + 1);
        }

        input(numbs_signs, expression);

        calculate(numbs_signs);

        System.out.print(numbs_signs.get(0));
    }
}