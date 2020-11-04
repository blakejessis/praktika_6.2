import javax.swing.*;
import java.util.HashMap;

public class Logic {
    private String computerSide;
    private String humanSide;
    private JButton[] buttons;

    public Logic(JButton[] buttons) {
        this.computerSide = "x";
        this.humanSide = "o";
        this.buttons = buttons;
    }

    public void changeSides() {
        String temp = this.computerSide;
        this.computerSide = this.humanSide;
        this.humanSide = temp;
    }

    public String getHumanSide() {
        return humanSide;
    }

    public String getComputerSide() {
        return computerSide;
    }

    public boolean checkWin(String s) {
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(s) && buttons[i+3].getText().equals(s) && buttons[i+6].getText().equals(s)) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (buttons[i*3].getText().equals(s) && buttons[i*3+1].getText().equals(s) && buttons[i*3+2].getText().equals(s)) {
                return true;
            }
        }
        if (buttons[0].getText().equals(s) && buttons[4].getText().equals(s) && buttons[8].getText().equals(s)) {
            return true;
        }

        if (buttons[2].getText().equals(s) && buttons[4].getText().equals(s) && buttons[6].getText().equals(s)) {
            return true;
        }
        return false;
    }

    public boolean isTableFull() {
        int k = 0;
        for (int i = 0; i < 9; i++) {
            if (!buttons[i].getText().equals(""))
                k++;
        }
        return k == 9;
    }

    public void clearField() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
        }
    }
}