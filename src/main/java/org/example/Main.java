package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Main  {
    public static void main(String[] args) throws Exception {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        int x = 5;
        int y = 5;
        final char player = 'X';
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(player);

        boolean continueReadingInput = true;
        while (continueReadingInput) {

            KeyStroke keyStroke = null;
            do {
                Thread.sleep(5); // might throw InterruptedException
                keyStroke = terminal.pollInput();
            } while (keyStroke == null);


            KeyType type = keyStroke.getKeyType();
            Character c = keyStroke.getCharacter(); // used Character instead of char because it might be null

            System.out.println("keyStroke.getKeyType(): " + type
                    + " keyStroke.getCharacter(): " + c);

            if (c == Character.valueOf('q')) {
                continueReadingInput = false;
                terminal.close();
                System.out.println("quit");
            }

            int oldX = x; // save old position x
            int oldY = y; // save old position y
            switch (keyStroke.getKeyType()) {
                case ArrowDown:
                    y += 1;
                    break;
                case ArrowUp:
                    y -= 1;
                    break;
                case ArrowRight:
                    x += 1;
                    break;
                case ArrowLeft:
                    x -= 1;
                    break;
            }
            terminal.setCursorPosition(oldX, oldY); // move cursor to old position
            terminal.putCharacter(' '); // clean up by printing space on old position
            terminal.setCursorPosition(x, y);
            terminal.putCharacter(player);
        }

    }
}