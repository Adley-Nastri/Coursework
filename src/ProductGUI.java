
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class ProductGUI extends JFrame implements ActionListener, MenuListener {

    Product p1 = new Product("Product 1", 50, 122.47);
    Product p2 = new Product("Product 2", 20, 150.99);
    Product p3 = new Product("Product 3", 30, 89.00);
    Product[] prodArr = {p1,p2,p3};
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    JTextArea text;
    JMenuBar menuBar;
    JMenu productsList;
    JMenuItem menuProd1, menuProd2, menuProd3;
    JButton btn1 ,btn2, btn3 , btn4;
    JTextField priceInp, productName, stockAm, productTotMoney;
    JLabel prodLbl, priceLbl, stockLbl, productTotMoneyLbl;

    int pPos =1, pTot =3;
    private JPanel topPanel = new JPanel();
    private JPanel midPanel = new JPanel();
    private JPanel botPanel = new JPanel();

    public static void main(String[] args)
    {
        ProductGUI pg = new ProductGUI();
        pg.setVisible(true);

    }

    public ProductGUI()
    {
        productArrayList.add(p1);
        productArrayList.add(p2);
        productArrayList.add(p3);

        setTitle("U1803005 ProductGUI");
        setSize(700, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);


        priceInp = new JTextField(10);
        stockAm = new JTextField(6);

        productName = new JTextField(10);
        productTotMoney = new JTextField(10);

        priceInp.addActionListener(this);
        stockAm.addActionListener(this);

        String pricing = Double.toString(productArrayList.get(pPos-1).getPrice());
        String prodName = productArrayList.get(pPos-1).getName();
        String theStock = Integer.toString(productArrayList.get(pPos-1).getStockLevel());

        productName.setText(prodName);
        productName.setEditable(false);

        productTotMoney.setText("0.0");
        productTotMoney.setEditable(false);


        prodLbl = new JLabel("Product");
        priceLbl = new JLabel("Price");
        stockLbl = new JLabel("Stock");
        productTotMoneyLbl = new JLabel("Product earnings");

        priceInp.setText(pricing);
        stockAm.setText(theStock);

        btn1 = new JButton("Set Price");
        btn1.addActionListener(this);


        btn2 = new JButton("Add New Product");
        btn2.addActionListener(this);

        btn3 = new JButton("Restock");
        btn3.addActionListener(this);

        btn4 = new JButton("Sell");
        btn4.addActionListener(this);

        topPanel.add(prodLbl);
        topPanel.add(productName);
        topPanel.add(priceLbl);
        topPanel.add(priceInp);
        topPanel.add(stockLbl);
        topPanel.add(stockAm);
        topPanel.add(productTotMoneyLbl);
        topPanel.add(productTotMoney);



        botPanel.add(btn1);
        botPanel.add(btn2);
        botPanel.add(btn3);
        botPanel.add(btn4);




        add(BorderLayout.NORTH, topPanel);
        add(BorderLayout.SOUTH, botPanel);

        
        createMenuBar();

    }
    private void createMenuBar() {

        menuBar = new JMenuBar();

        productsList = new JMenu("Products");

        menuProd1 = new JMenuItem(p1.getName());
        menuProd2 = new JMenuItem(p2.getName());
        menuProd3 = new JMenuItem(p3.getName());


        menuProd1.addActionListener(this);
        menuProd2.addActionListener(this);
        menuProd3.addActionListener(this);

        productsList.add(menuProd1);
        productsList.add(menuProd2);
        productsList.add(menuProd3);


        menuBar.add(productsList);

        setJMenuBar(menuBar);
    }


    public void menuSelected(MenuEvent me)
    {

    }

    public void menuDeselected(MenuEvent me)
    {

    }
    public void menuCanceled(MenuEvent me)
    {

    }

    public void actionPerformed(ActionEvent e)

    {
        if (e.getSource() instanceof JMenuItem) {
            JMenuItem source = (JMenuItem) (e.getSource());
            try {

                JMenuItem menuItem = (JMenuItem) e.getSource();
                JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();
                Component invoker = popupMenu.getInvoker();
                pPos = popupMenu.getComponentZOrder(menuItem)+1;
                pTot = popupMenu.getComponentCount();
                productName.setText(productArrayList.get(pPos-1).getName());
                String price = Double.toString(productArrayList.get(pPos-1).getPrice());
                String prodName = productArrayList.get(pPos-1).getName();
                String theStock = Integer.toString(productArrayList.get(pPos-1).getStockLevel());
                String totMon = Double.toString(productArrayList.get(pPos-1).getTotalMoney());
                priceInp.setText(price);
                stockAm.setText(theStock);
                productName.setText(prodName);
                productTotMoney.setText(totMon);
                System.out.println(pPos + "/" + popupMenu.getComponentCount());
            } catch (Exception ex) {

            }

        }
            if (e.getSource() == btn1)
             {
            try {
                if (priceInp.getText().length() == 0) {
                   throw new PriceException("Price input is empty");
                }
                Double.parseDouble(priceInp.getText());
                priceInp.setText(String.format("%.2f",Double.parseDouble(priceInp.getText())));
                productArrayList.get(pPos-1).setPrice(Double.parseDouble(priceInp.getText()));
                String str = ""+productArrayList.get(pPos-1).getName()+" (Product "+pPos+")  has been set to the price of "+Double.parseDouble(priceInp.getText())+ "";
                JOptionPane.showMessageDialog(null,str, "Products" , JOptionPane.INFORMATION_MESSAGE);
            }
            catch (PriceException ex){
                JOptionPane.showMessageDialog(null, ex, "Exception"  , JOptionPane.ERROR_MESSAGE);

            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "String is not in double format", "Exception" , JOptionPane.ERROR_MESSAGE);

            }
             }

        if (e.getSource() == btn2) {
            JTextField newProdName = new JTextField();
            JTextField newProdPrice = new JTextField();
            JTextField newProdStock = new JTextField();
            final JComponent[] inputs = new JComponent[] {
                    new JLabel("Product name"),
                    newProdName,
                    new JLabel("Price"),
                    newProdPrice,
                    new JLabel("Stock"),
                    newProdStock

            };


            try {
                int n = JOptionPane.showConfirmDialog(null, inputs, "New product", JOptionPane.OK_CANCEL_OPTION);
                if (n == JOptionPane.CANCEL_OPTION) {
                    return;
                }
                else {
                   try {
                       if (Integer.parseInt(newProdStock.getText()) <0)
                       {
                           throw new StockException("Stock value is less than 0");
                       }
                       if (newProdName.getText().length() ==0)
                       {
                           throw new Exception("Product name is empty");
                       }
                   }
                   catch (StockException se)
                   {
                       JOptionPane.showMessageDialog(null, se, "Error" , JOptionPane.ERROR_MESSAGE);
                       return;
                   }
                    newProdPrice.setText(String.format("%.2f",Double.parseDouble(newProdPrice.getText())));
                    productArrayList.add(new Product(newProdName.getText(), Integer.parseInt(newProdStock.getText()), Double.parseDouble(newProdPrice.getText())));
                    String str = "There are now " + productArrayList.size() + " Products";
                    JOptionPane.showMessageDialog(null, str, "Products ", JOptionPane.INFORMATION_MESSAGE);
                    productsList.add(new JMenuItem(""+newProdName.getText()+ " (Product "+productArrayList.size()+")")).addActionListener(this);
                }

            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Values are not in correct format", "Exception" , JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex, "Exception" , JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btn3) {
            try {

                Integer stockInput =Integer.parseInt(stockAm.getText());

                productArrayList.get(pPos-1).reStock(stockInput);
                String str = ""+productArrayList.get(pPos-1).getName()+" (Product "+pPos+") Stock level has been set to "+stockInput+ "";
                JOptionPane.showMessageDialog(null,str, "Products" , JOptionPane.INFORMATION_MESSAGE);

            }
            catch (StockException ex){
                JOptionPane.showMessageDialog(null, ex, "Exception" , JOptionPane.ERROR_MESSAGE);
            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, ex, "Exception" , JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == btn4) {
            JTextField sellAmount = new JTextField();
            final JComponent[] inp = new JComponent[]{
                    new JLabel("Sell"),
                    sellAmount
            };
            try {
                int ne = JOptionPane.showConfirmDialog(null, inp, "Sell product", JOptionPane.OK_CANCEL_OPTION);
                if (ne == JOptionPane.CANCEL_OPTION) {
                    return;
                } else {
                    try {
                        if ((Integer.parseInt(sellAmount.getText()) <=0)) {
                            throw new SellAmountException("Cannot sell "+sellAmount.getText()+" item(s)");
                        }
                        productArrayList.get(pPos - 1).sell(Integer.parseInt(sellAmount.getText()));
                        double orderTotal = productArrayList.get(pPos - 1).getPrice() * Integer.parseInt(sellAmount.getText());
                        JOptionPane.showMessageDialog(null, ""+Integer.parseInt(sellAmount.getText())+" item(s) Sold for "+orderTotal+"", "Sold", JOptionPane.INFORMATION_MESSAGE);
                        String totMon = Double.toString(productArrayList.get(pPos-1).getTotalMoney());
                        productTotMoney.setText(totMon);
                        String theStock = Integer.toString(productArrayList.get(pPos-1).getStockLevel());
                        stockAm.setText(theStock);
                        productTotMoney.setText(String.format("%.2f",Double.parseDouble(productTotMoney.getText())));

                    } catch (SellAmountException se) {
                        JOptionPane.showMessageDialog(null, se, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,"NumberFormatException : Input '"+sellAmount.getText()+"' is not a valid format", "Exception", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}