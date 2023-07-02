package org.example;
import javax.swing.*;
import java.awt.*;

public class ExcelFormulaGenerator extends JFrame {

    private final JTextField numberOfIfText;
    private final JTextArea formulaTextArea;

    public ExcelFormulaGenerator() {
        setTitle("Excel Formula Generator");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel ifLabel = new JLabel("Number of IF statements:");
        numberOfIfText = new JTextField(10);
        JButton generateButton = new JButton("Generate");
        formulaTextArea = new JTextArea(10, 30);

        add(ifLabel);
        add(numberOfIfText);
        add(generateButton);
        add(formulaTextArea);

        generateButton.addActionListener(e -> {
            int numberOfIf = Integer.parseInt(numberOfIfText.getText());
            String formula = generateFormula(numberOfIf);
            formulaTextArea.setText(formula);
        });
    }

    private String generateFormula(int numberOfIf) {
        StringBuilder formulaBuilder = new StringBuilder("=");

        String closingParentheses = "";
        for (int i = 1; i <= numberOfIf; i++) {
            String logicalTest = JOptionPane.showInputDialog("Enter logical test for IF statement " + i);
            if (logicalTest == null) {
                // If the user clicks "Cancel," exit the method
                return "";
            }

            String valueIfTrue = JOptionPane.showInputDialog("Enter value if true for IF statement " + i);
            if (valueIfTrue == null) {
                // If the user clicks "Cancel," exit the method
                return "";
            }

            formulaBuilder.append("IF(");
            formulaBuilder.append(logicalTest);
            formulaBuilder.append(", ");
            formulaBuilder.append(valueIfTrue);
            closingParentheses += ")";
        }

        formulaBuilder.append("\"\"");
        formulaBuilder.append(closingParentheses);
        formulaBuilder.append(")");
        int endingIndexToDelete = formulaBuilder.length() - numberOfIf;
        int startingIndexToDelete = endingIndexToDelete - numberOfIf;
        formulaBuilder.delete(startingIndexToDelete, endingIndexToDelete);
        formulaBuilder.deleteCharAt(formulaBuilder.length() - 4);
        formulaBuilder.deleteCharAt(formulaBuilder.length() - 4);

        return formulaBuilder.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ExcelFormulaGenerator().setVisible(true);
            }
        });
    }
}
