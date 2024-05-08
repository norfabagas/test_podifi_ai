import java.util.ArrayList;
import java.util.List;

class Test {
    static String VISA = "Visa";
    static String AMEX = "American Experss";
    static String DINERSCLUB = "Diners Club";
    static String MASTERCARD = "MasterCard"; 
    public Test() {

    }

    public static void runTest() {
        List<List<String>> cases = new ArrayList<>();
        cases.add(List.of("341234567890123", AMEX));
        cases.add(List.of("371234567890123", AMEX));
        cases.add(List.of("38123456789012", DINERSCLUB));
        cases.add(List.of("4123456789012", VISA));
        cases.add(List.of("4123456789012345", VISA));
        cases.add(List.of("4123456789012345678", VISA));
        cases.add(List.of("5112345678901234", MASTERCARD));
        cases.add(List.of("5212345678901234", MASTERCARD));
        cases.add(List.of("5312345678901234", MASTERCARD));
        cases.add(List.of("5412345678901234", MASTERCARD));
        cases.add(List.of("5512345678901234", MASTERCARD));

        for (int i = 0; i < cases.size(); i++) {
            List<String> aCase = cases.get(i);

            if (identifyCard(aCase.get(0)).equals(aCase.get(1))) {
                System.out.println("Case " + (i + 1) + " True");
            } else {
                System.out.println("Case " + (i + 1) + " False");
            }
        }
    }

    public static String identifyCard(String cardNumber) {
        String cardBrand = "";
        Integer cardLength = cardNumber.length();
        switch (cardLength) {
            case 13:
                cardBrand = checkVisa(cardNumber);
                break;
            case 14:
                cardBrand = checkDinersClub(cardNumber);
                break;
            case 15:
                cardBrand = checkAmex(cardNumber);
                break;
            case 16:
                cardBrand = checkVisa(cardNumber);
                if (cardBrand.length() < 1) {
                    cardBrand = checkMasterCard(cardNumber);
                }
                break;
            case 19:
                cardBrand = checkVisa(cardNumber);
                break;
        }
        return cardBrand;
    }

    public static String checkVisa(String cardNum) {
        return cardNum.substring(0, 1).equals("4") ? VISA : "";
    }
    
    public static String checkMasterCard(String cardNum) {
        Integer cardLen = Integer.parseInt(cardNum.substring(0, 2));
        if (cardLen >= 51 && cardLen <= 55) {
            return MASTERCARD;
        } else {
            return "";
        }
    }
    
    public static String checkAmex(String cardNum) {
        Integer cardLen = Integer.parseInt(cardNum.substring(0, 2));
        if (cardLen == 34 || cardLen == 37) {
            return AMEX;
        } else {
            return "";
        }
    }

    public static String checkDinersClub(String cardNum) {
        Integer cardLen = Integer.parseInt(cardNum.substring(0, 2));
        if (cardLen >= 38 && cardLen <= 39) {
            return DINERSCLUB;
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        runTest();
    }
}