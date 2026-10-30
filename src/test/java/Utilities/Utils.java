package Utilities;

public class Utils {

    public long parseINRCurrency(String price) {
        String cleanPrice = price.replace("₹", "")
                .replace(",", "")
                .trim();

        return Long.parseLong(cleanPrice);
    }

    public int parseINRCurrencyInt(String price) {
        String cleanPrice = price.replace("₹", "")
                .replace(",", "")
                .trim();

        return Integer.parseInt(cleanPrice);
    }

}
