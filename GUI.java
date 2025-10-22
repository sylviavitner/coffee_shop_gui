import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;

public class GUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String NAME = "Coffee Shop";
    private static final DecimalFormat CURRENCY = new DecimalFormat("$#,##0.00");

    // Beverage state
    private Beverage currentBeverage;
    private double sizeMultiplier = 1.0;

    // GUI Components
    private final ButtonGroup baseGroup = new ButtonGroup();
    private final ButtonGroup sizeGroup = new ButtonGroup();
    private final JCheckBox[] condimentBoxes = new JCheckBox[6];
    private final JTextArea orderSummary = new JTextArea(10, 30);
    private final JToggleButton breakdownToggle = new JToggleButton("Show/Hide Breakdown");
    private final JButton clearButton = new JButton("Clear/Reset");
    private final JButton checkoutButton = new JButton("Add to Cart");
    private final JPanel helpPanel = new JPanel();

    public GUI() {
        super(NAME);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Initialize components
        initializeComponents();
        
        // Pack and center
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        // Create the three main panels
        add(createLeftPanel(), BorderLayout.WEST);
        add(createMiddlePanel(), BorderLayout.CENTER);
        add(createRightPanel(), BorderLayout.EAST);
        add(createHelpPanel(), BorderLayout.SOUTH);
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Select Base & Size"));

        // Base beverage selection
        String[] bases = {"Espresso $2.00", "House Blend $2.50", "Dark Roast $2.00", "Decaf $3.00"};
        for (String base : bases) {
            JRadioButton rb = new JRadioButton(base);
            rb.addActionListener(e -> updateOrder());
            baseGroup.add(rb);
            panel.add(rb);
        }

        panel.add(Box.createVerticalStrut(20));

        // Size selection
        String[] sizes = {"Small = x1.00", "Medium = x1.20", "Large = x1.40"};
        for (String size : sizes) {
            JRadioButton rb = new JRadioButton(size);
            rb.addActionListener(e -> {
                if (size.startsWith("Small")) sizeMultiplier = 1.0;
                else if (size.startsWith("Medium")) sizeMultiplier = 1.2;
                else sizeMultiplier = 1.4;
                updateOrder();
            });
            sizeGroup.add(rb);
            panel.add(rb);
        }

        return panel;
    }

    private JPanel createMiddlePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Customize"));

        String[] condiments = {
            "Milk $0.75",
            "Mocha $1.00",
            "Soy $1.25",
            "Whip $0.50",
            "Caramel $1.00",
            "Vanilla $1.00"
        };

        for (int i = 0; i < condiments.length; i++) {
            condimentBoxes[i] = new JCheckBox(condiments[i]);
            condimentBoxes[i].addActionListener(e -> updateOrder());
            panel.add(condimentBoxes[i]);
        }

        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Your Order"));

        // Order summary area
        orderSummary.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderSummary);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Control buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        breakdownToggle.addActionListener(e -> updateOrder());
        clearButton.addActionListener(e -> clearOrder());
        checkoutButton.addActionListener(e -> checkout());
        checkoutButton.setEnabled(false);

        buttonPanel.add(breakdownToggle);
        buttonPanel.add(clearButton);
        buttonPanel.add(checkoutButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createHelpPanel() {
        helpPanel.setBorder(BorderFactory.createTitledBorder("Help/Info"));
        helpPanel.setLayout(new BorderLayout());
        JTextArea helpText = new JTextArea(
            "How it works: The Decorator Pattern\n" +
            "Your drink is built by wrapping condiments around a base beverage.\n" +
            "1. Select your base drink\n" +
            "2. Choose your size (affects all prices)\n" +
            "3. Add condiments in any order\n" +
            "Toggle the breakdown button to see how your drink is calculated!"
        );
        helpText.setEditable(false);
        helpText.setBackground(helpPanel.getBackground());
        helpPanel.add(helpText, BorderLayout.CENTER);
        return helpPanel;
    }

    private void updateOrder() {
        String selectedBase = getSelectedButtonText(baseGroup);
        String selectedSize = getSelectedButtonText(sizeGroup);

        if (selectedBase == null || selectedSize == null) {
            orderSummary.setText("Please select both a base beverage and size");
            checkoutButton.setEnabled(false);
            return;
        }

        // Create base beverage
        currentBeverage = createBaseBeverage(selectedBase);
        if (currentBeverage == null) return;

        // Add condiments
        for (JCheckBox cb : condimentBoxes) {
            if (cb.isSelected()) {
                currentBeverage = addCondiment(currentBeverage, cb.getText());
            }
        }

        // Build order summary
        StringBuilder summary = new StringBuilder();
        if (breakdownToggle.isSelected()) {
            // Show detailed breakdown
            double basePrice = getPrice(selectedBase);
            double baseTotal = basePrice * sizeMultiplier;
            summary.append(String.format("%s × %.2f = %s%n", 
                selectedBase.split("\\$")[0].trim(),
                sizeMultiplier,
                CURRENCY.format(baseTotal)));

            // Add condiment breakdown
            for (JCheckBox cb : condimentBoxes) {
                if (cb.isSelected()) {
                    double condimentPrice = getPrice(cb.getText());
                    double condimentTotal = condimentPrice * sizeMultiplier;
                    summary.append(String.format("%s × %.2f = %s%n",
                        cb.getText().split("\\$")[0].trim(),
                        sizeMultiplier,
                        CURRENCY.format(condimentTotal)));
                }
            }
            summary.append("------------------------\n");
        } else {
            // Show simple description
            summary.append(String.format("%s (%s)", 
                selectedBase.split("\\$")[0].trim(),
                selectedSize.split("=")[0].trim()));
            
            for (JCheckBox cb : condimentBoxes) {
                if (cb.isSelected()) {
                    summary.append(", ").append(cb.getText().split("\\$")[0].trim());
                }
            }
            summary.append("\n------------------------\n");
        }

        // Add final price
        double total = currentBeverage.cost() * sizeMultiplier;
        summary.append(String.format("Total: %s", CURRENCY.format(total)));
        
        orderSummary.setText(summary.toString());
        checkoutButton.setEnabled(true);
    }

    private String getSelectedButtonText(ButtonGroup group) {
        for (java.util.Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    private double getPrice(String text) {
        try {
            return Double.parseDouble(text.split("\\$")[1].trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    private Beverage createBaseBeverage(String selectedBase) {
        if (selectedBase.startsWith("Espresso")) return new Espresso("Espresso");
        if (selectedBase.startsWith("House Blend")) return new HouseBlend("House Blend");
        if (selectedBase.startsWith("Dark Roast")) return new DarkRoast("Dark Roast");
        if (selectedBase.startsWith("Decaf")) return new Decaf("Decaf");
        return null;
    }

    private Beverage addCondiment(Beverage beverage, String condiment) {
        if (condiment.startsWith("Milk")) return new Milk(beverage);
        if (condiment.startsWith("Mocha")) return new Mocha(beverage);
        if (condiment.startsWith("Soy")) return new Soy(beverage);
        if (condiment.startsWith("Whip")) return new Whip(beverage);
        if (condiment.startsWith("Caramel")) return new Caramel(beverage);
        if (condiment.startsWith("Vanilla")) return new Vanilla(beverage);
        return beverage;
    }

    private void clearOrder() {
        baseGroup.clearSelection();
        sizeGroup.clearSelection();
        for (JCheckBox cb : condimentBoxes) {
            cb.setSelected(false);
        }
        orderSummary.setText("");
        currentBeverage = null;
        sizeMultiplier = 1.0;
        checkoutButton.setEnabled(false);
    }

    private void checkout() {
        if (currentBeverage != null) {
            JOptionPane.showMessageDialog(this,
                "Order added to cart:\n" + orderSummary.getText(),
                "Order Confirmation",
                JOptionPane.INFORMATION_MESSAGE);
            clearOrder();
        }
    }
}

