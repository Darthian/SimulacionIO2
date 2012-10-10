package presentacion;

import processing.core.PApplet;
import controlP5.*;
import javax.swing.ImageIcon;
import processing.core.PImage;

public class SimulacionCore extends PApplet {

    ControlP5 cp5;
    int myColor = color(255);
    int c1, c2;
    float n, n1;
    RadioButton r;
    private PImage iconCursor;
    int mapa[][] = new int[25][20];

    public void setup() {
        size(1200, 640);
        noStroke();
        cp5 = new ControlP5(this);
        ImageIcon titlebaricon = new ImageIcon(loadBytes("/imagenes/iconClock.png"));
        frame.setIconImage(titlebaricon.getImage());
        frame.setTitle("TimePower");
        iconCursor = loadImage("/imagenes/cursor.png");
        cursor(iconCursor);

        r = cp5.addRadioButton("radioButton")
                .setPosition(30, 100)
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
                .setPosition(10, 300)
                .setSize(100, 19);

        cp5.addButton("Detener")
                .setValue(100)
                .setPosition(10, 320)
                .setSize(100, 19);

        cp5.addButton("Defecto")
                .setPosition(10, 340)
                .setSize(100, 19)
                .setValue(0);
    }

    public void draw() {
        background(250);
        inicializarMapa();
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

    public void inicializarMapa() {
        fill(lerpColor(color(0, 150, 150), color(0, 150, 150), 80));
        rect(0, 0, 160, 650);
        stroke(200);
        for (int i = 200; i < 1200; i++) {
            if (i % 40 == 0) {
                line(i, 0, i, 650);
            }
        }
        for (int i = 200; i < 1200; i++) {
            if (i % 40 == 0) {
                line(i, 0, i, 650);
            }
        }
        for (int j = 0; j < 640; j++) {
            if (j % 32 == 0) {
                line(160, j, 1200, j);
            }
        }
    }
}
