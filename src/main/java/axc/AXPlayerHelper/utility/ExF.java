package axc.AXPlayerHelper.utility;

public class ExF {
    public static String double_to_string_selective_decimal(double value, int decimalPoint)
    {
        return String.format("%."+decimalPoint+"f", value);
    }
}
