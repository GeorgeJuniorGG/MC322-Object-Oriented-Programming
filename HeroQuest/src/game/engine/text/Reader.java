package game.engine.text;

import java.util.InputMismatchException;
import java.util.Scanner;

class Reader {
    private Scanner keyboard;
    private TextRender render;

    Reader(TextRender render) {
        keyboard = new Scanner(System.in);
        this.render = render;
    }

    private void clearBuffer(){
        if(keyboard.hasNextLine()){
            keyboard.nextLine();
        }
    }

    int readInt() {

        boolean success = false;
        int read = 1;

        while(!success){
            try {
               read = keyboard.nextInt();
               success = true; 
            } catch (InputMismatchException e) {
                clearBuffer();
                render.show("Erro ao ler o comando. Tente novamente!");
            }
        }

        clearBuffer();
        return read;
    }

    String readString(){
        String read = keyboard.nextLine();
        return read;
    }

    void close(){
        keyboard.close();
    }

}