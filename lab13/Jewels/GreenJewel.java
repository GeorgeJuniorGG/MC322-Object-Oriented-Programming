package lab13.Jewels;

public class GreenJewel extends Jewel{
    public GreenJewel(int x, int y) {
        super(JewelType.Green, x, y);
    }
    
    @Override
    public String getSymbol() {
        return "JG";
    }
}