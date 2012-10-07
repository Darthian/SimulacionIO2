package presentacion;

import processing.core.PApplet;
import controlP5.*;

public class SimulacionCore extends PApplet {

    ControlP5 cp5;
    int myColor = color(255);
    int c1, c2;
    float n, n1;
    RadioButton r;

    public void setup() {
        size(1000, 650);
        noStroke();
        cp5 = new ControlP5(this);

        r = cp5.addRadioButton("radioButton")
                .setPosition(50, 100)
                .setSize(40, 20)
                .setColorForeground(color(120))
                .setColorActive(color(255))
                .setColorLabel(color(255))
                .setItemsPerRow(1)
                .setSpacingColumn(50)
                .addItem("50", 1)
                .addItem("100", 2)
                .addItem("150", 3)
                .addItem("200", 4)
                .addItem("250", 5);

        for (Toggle t : r.getItems()) {
            t.captionLabel().setColorBackground(color(255, 80));
            t.captionLabel().style().moveMargin(-7, 0, 0, -3);
            t.captionLabel().style().movePadding(7, 0, 0, 3);
            t.captionLabel().style().backgroundWidth = 45;
            t.captionLabel().style().backgroundHeight = 13;
        }
        
        cp5.addButton("Iniciar")
                .setValue(0)
                .setPosition(100, 300)
                .setSize(100, 19);

        cp5.addButton("Detener")
                .setValue(100)
                .setPosition(100, 320)
                .setSize(100, 19);

        cp5.addButton("Defecto")
                .setPosition(100, 340)
                .setSize(100, 19)
                .setValue(0);
    }

    public void draw() {
        background(myColor);
        myColor = lerpColor(c1, c2, n);
        n += (1 - n) * 0.1;
    }

    public void Iniciar(int theValue) {
        println("a button event from colorA: " + theValue);
        c1 = c2;
        c2 = color(0, 160, 100);
    }

    public void Detener(int theValue) {
        println("a button event from colorB: " + theValue);
        c1 = c2;
        c2 = color(150, 0, 0);
    }

    public void Defecto(int theValue) {
        println("a button event from colorC: " + theValue);
        c1 = c2;
        c2 = color(0, 150, 150);
    }

    public void keyPressed() {
        switch (key) {
            case ('0'):
                r.deactivateAll();
                break;
            case ('1'):
                r.activate(0);
                break;
            case ('2'):
                r.activate(1);
                break;
            case ('3'):
                r.activate(2);
                break;
            case ('4'):
                r.activate(3);
                break;
            case ('5'):
                r.activate(4);
                break;
        }
    }

    public void radioButton(int a) {
        println("a radio Button event: " + a);
    }
}
