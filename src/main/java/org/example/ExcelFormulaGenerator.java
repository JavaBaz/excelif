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

        numberOfIfText = new JTextField(10);
        JButton generateButton = new JButton("Generate");
        formulaTextArea = new JTextArea(10, 30);

        add(new JLabel("Number of IF statements:"));
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

        StringBuilder closingParentheses = new StringBuilder();
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
            closingParentheses.append(")");
        }


        appendEndNessaryParts(formulaBuilder, closingParentheses);
        deleteUnnecessaryParts(formulaBuilder, numberOfIf);

        return formulaBuilder.toString();
    }

    private void appendEndNessaryParts(StringBuilder formulaBuilder, StringBuilder closingParentheses) {
        formulaBuilder.append("\"\"");
        formulaBuilder.append(closingParentheses);
        formulaBuilder.append(")");
    }

    private void deleteUnnecessaryParts(StringBuilder formulaBuilder, int numberOfIf) {
        int endingIndexToDelete = formulaBuilder.length() - numberOfIf;
        int startingIndexToDelete = endingIndexToDelete - numberOfIf;
        formulaBuilder.delete(startingIndexToDelete, endingIndexToDelete);
        formulaBuilder.deleteCharAt(formulaBuilder.length() - 4);
        formulaBuilder.deleteCharAt(formulaBuilder.length() - 4);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExcelFormulaGenerator().setVisible(true));
    }
}
