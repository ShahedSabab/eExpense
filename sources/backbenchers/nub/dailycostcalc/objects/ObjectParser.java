package backbencers.nub.dailycostcalc.objects;

import android.util.Log;
import backbencers.nub.dailycostcalc.model.Debit;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObjectParser {
    private String category;
    private String text;

    public ObjectParser(String category2, String text2) {
        setText(text2.trim());
        this.category = category2;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text2) {
        this.text = text2;
    }

    public Debit parse() {
        String[] tokens = this.text.toUpperCase().split("\n");
        String description = "";
        String total = "0.00";
        int len = tokens.length - 1;
        String str = "";
        boolean isTotal = false;
        double amount = 0.0d;
        int index = 0;
        String str2 = "[^\\d.?\\d]+";
        int length = tokens.length;
        for (int i = 0; i < length; i++) {
            String token = tokens[i];
            if (token.equals("TOTAL") || index == len) {
                Log.d("ObjectParser", "parse Total: " + token);
                String tempTotalToken = token;
                String[] tTokens = token.trim().split(" ");
                total = extractDecimal(tTokens[tTokens.length - 1]);
                try {
                    if (Double.parseDouble(total.trim()) == amount) {
                        isTotal = true;
                    } else {
                        isTotal = false;
                    }
                } catch (Exception e) {
                    isTotal = false;
                }
            } else {
                Log.d("ObjectParser", "parse: " + token);
                description = description + token + "\n";
                String[] tTokens2 = token.trim().split(" ");
                try {
                    amount += Double.parseDouble(extractDecimal(tTokens2[tTokens2.length - 1]).trim());
                } catch (Exception e2) {
                }
            }
            index++;
        }
        String date = new SimpleDateFormat("MMM dd, yyyy").format(new Date());
        if (isTotal) {
            return new Debit(date, this.category, description, Double.valueOf(Double.parseDouble(total)));
        }
        Log.d("ObjectParser", "parse: " + description);
        double tt = 0.0d;
        try {
            String total2 = extractDecimal(total);
            Log.d("ObjectParser", "parse: " + total2);
            if (total2.trim().length() <= 0) {
                total2 = "0.0";
            }
            tt = amount + Double.parseDouble(total2);
        } catch (Exception e3) {
        }
        return new Debit(date, this.category, this.text.toUpperCase(), Double.valueOf(tt));
    }

    private String extractDecimal(String str) {
        char[] charArray;
        StringBuilder sb = new StringBuilder();
        int length = str.trim().length();
        boolean isPoint = false;
        for (char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else if (ch == '.' && !isPoint) {
                sb.append(ch);
                isPoint = true;
            }
        }
        return sb.toString();
    }
}
