
public class Product
{
    private String name;
    private int stockLevel;
    private double price, totalMoney;

    public Product(String nameIn, int stockIn, Double priceIn)
    {
        name = nameIn;
        stockLevel = stockIn;
        price = priceIn;
        totalMoney = 0;

    }

    public void reStock(int newStock) throws StockException
    {
        if (newStock <0) {

            throw new StockException("Stock value '"+newStock+"' is less than 0.");
        }
        stockLevel = newStock;
    }

    public double sell(int sellAmount) throws SellAmountException
    {
        if (sellAmount <= stockLevel)
        {
            stockLevel -= sellAmount;
            double orderTotal = price * sellAmount;
            String str = String.format("%s Item(s) sold for a total of £%.2f", sellAmount,orderTotal);
            System.out.println(str);
            totalMoney += orderTotal;
            return orderTotal;
        }
        else
            {
                throw new SellAmountException("Sell amount '"+sellAmount+"' is greater than the stock level.");
            }
    }

    public void setPrice(double newPrice) throws PriceException
    {
        if (newPrice > 0) {
            price = newPrice;
        }
        if (newPrice <= 0) {
            throw new PriceException("Price must be greater than 0");
        }
    }

    public String getName()
    {
        return name;
    }

    public int getStockLevel()
    {
        return stockLevel;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public double getPrice()
    {
        return price;
    }
}