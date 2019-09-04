import java.math.BigDecimal;

public final class Int extends java.lang.Number implements Comparable<Int> {

    private Integer value = 0;

    public void addition(Integer integer)
    {
        value = value + integer;
    }

    public void substract(Integer integer)
    {
        value = value - integer;
    }

    public void division(Integer integer) {
        value = value / integer;
    }

    public void multiplication(Integer integer) {
        value = value * integer;
    }

    public BigDecimal bigDecimalValue()
    {
        return new BigDecimal(value);
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public int compareTo(Int o) {
        return compare(this.value, o.value);
    }

    public static int compare(int x, int y) {
        return Integer.compare(x, y);
    }

}
