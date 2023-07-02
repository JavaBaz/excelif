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
            // If the user clicks "Cancel," exit the method
            if (logicalTest == null)
                return "";

            String valueIfTrue = JOptionPane.showInputDialog("Enter value if true for IF statement " + i);
            // If the user clicks "Cancel," exit the method
            if (valueIfTrue == null)
                return "";

            appendNecessaryParts(formulaBuilder, logicalTest, valueIfTrue);
            closingParentheses.append(")");
        }
        appendEndNecessaryParts(formulaBuilder, closingParentheses);
        deleteUnnecessaryParts(formulaBuilder, numberOfIf);
        return formulaBuilder.toString();
    }

    private void appendNecessaryParts(StringBuilder formulaBuilder, String logicalTest, String valueIfTrue) {
        formulaBuilder.append("IF(");
        formulaBuilder.append(logicalTest);
        formulaBuilder.append(", ");
        formulaBuilder.append("\"");
        formulaBuilder.append(valueIfTrue);
        formulaBuilder.append("\", ");
    }

    private void appendEndNecessaryParts(StringBuilder formulaBuilder, StringBuilder closingParentheses) {
        formulaBuilder.append(closingParentheses);
    }


    private void deleteUnnecessaryParts(StringBuilder sb, int numberOfIf){
        int lastIndex = sb.length() - 2;
        int positionLastUnwantedCharacter = lastIndex - numberOfIf;
        sb.deleteCharAt(positionLastUnwantedCharacter);
        sb.deleteCharAt(positionLastUnwantedCharacter);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExcelFormulaGenerator().setVisible(true));
    }
}
