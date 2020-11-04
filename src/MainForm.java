import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.util.Random;

public class MainForm extends JFrame {
    private JPanel panel;
    private JCheckBox checkBox;
    Logic tacLogic;
    private boolean fieldEmpty;
    JButton[] buttons;
    private boolean missed;

    public MainForm() {

        setTitle("Krestiki i Noliki");
        setSize(500,500);

        setLayout(new BorderLayout());

        checkBox = new JCheckBox("press for change side", false);
        checkBox.addItemListener(new SelectSide());
        add(checkBox, BorderLayout.NORTH);
        panel = new JPanel();
        fieldEmpty = true;
        missed = false;

        add(panel,BorderLayout.CENTER);

        panel.setLayout(new GridLayout(3, 3));
        buttons = new JButton[9];


        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setPreferredSize(new Dimension(45, 45));
            buttons[i].setBackground(Color.WHITE);
            buttons[i].addMouseListener(new CellClicked());
            buttons[i].setFont(new Font("LucidaSans", Font.BOLD, 40));
            panel.add(buttons[i]);
        }
        tacLogic = new Logic(buttons);
    }

    class CellClicked implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            checkBox.setEnabled(false);
            missed = false;
            JButton button =(JButton)e.getSource();
            if (button.getText().equals("") && !button.getText().equals(tacLogic.getComputerSide())) {
                button.setText(tacLogic.getHumanSide());
                missed = true;
            }

            if (tacLogic.checkWin(tacLogic.getHumanSide())) {
                JOptionPane.showMessageDialog(MainForm.this, "YOU WON");
                checkBox.setEnabled(true);
                tacLogic.clearField();
            }
            if (tacLogic.isTableFull()) {
                JOptionPane.showMessageDialog(MainForm.this, "DRAW");
                checkBox.setEnabled(true);
                tacLogic.clearField();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (missed) {
                Random r = new Random();
                while (true) {
                    int i = r.nextInt(9);
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setText(tacLogic.getComputerSide());
                        break;
                    }
                }
                if (tacLogic.checkWin(tacLogic.getComputerSide())) {
                    JOptionPane.showMessageDialog(MainForm.this, "YOU LOST");
                    checkBox.setEnabled(true);
                    tacLogic.clearField();
                }
                if (tacLogic.isTableFull()) {
                    JOptionPane.showMessageDialog(MainForm.this, "DRAW");
                    checkBox.setEnabled(true);
                    tacLogic.clearField();
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class SelectSide implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            tacLogic.changeSides();
            if (e.getStateChange() == ItemEvent.SELECTED) {
                checkBox.setText("press for change side");

            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                checkBox.setText("press for change side");
            }

        }
    }
}