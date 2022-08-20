package lab13.Jewels;

public class RedJewel extends Jewel{
    public RedJewel(int x, int y) {
        super(JewelType.Red, x, y);
    }

    @Override
    public String getSymbol() {
        return "JR";
    }
}