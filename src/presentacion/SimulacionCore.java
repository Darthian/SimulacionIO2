package presentacion;

import processing.core.PApplet;
import controlP5.*;
import javax.swing.ImageIcon;
import processing.core.PImage;

public class SimulacionCore extends PApplet {

    ControlP5 cp5;
    private PImage iconCursor;
    private int mapa[][] = new int[25][20];
    private Accordion accordion;
    private int c = color(0, 160, 100);
    private PImage TTipo1;
    private PImage TTipo2;
    private PImage TTipo3;
    private PImage TTipo4;
    private String TIPO_TRANSPORTE = "tipo1";

    public void setup() {
        size(1200, 640);
        smooth();
        noStroke();

        cp5 = new ControlP5(this);

        cargaImagenes();
        crearOpciones();
        cambiarIcono();
        cambiarCursor();
        crearBotones();
    }

    public void draw() {
        background(250);
        inicializarMapa();
        fill(c);

        float s1 = cp5.getController("hello").getValue();
        ellipse(850, 300, s1, s1);

        float s2 = cp5.getController("world").getValue();
        ellipse(450, 300, s2, s2);
        pintarTransporte((int) Math.round((Math.random()*25))  * 40 + 160, (int) Math.round((Math.random()*20))  * 32);

    }

    void crearBotones() {
        cp5.addButton("Iniciar")
                .setValue(0)
                .setPosition(10, 550)
                .setSize(100, 19);

        cp5.addButton("Detener")
                .setValue(100)
                .setPosition(10, 570)
                .setSize(100, 19);

        cp5.addButton("Defecto")
                .setPosition(10, 590)
                .setSize(100, 19)
                .setValue(0);
    }

    void cambiarIcono() {
        ImageIcon titlebaricon = new ImageIcon(loadBytes("/imagenes/iconClock.png"));
        frame.setIconImage(titlebaricon.getImage());
        frame.setTitle("TimePower");
    }

    void cambiarCursor() {
        cursor(iconCursor);
    }

    void cargaImagenes() {
        iconCursor = loadImage("/imagenes/cursor.png");
        TTipo1 = loadImage("/imagenes/moto.png");
        TTipo2 = loadImage("/imagenes/amarillo.png");
        TTipo3 = loadImage("/imagenes/verde.png");
        TTipo4 = loadImage("/imagenes/rojo.png");
    }

    void crearOpciones() {
        // group number 1, contains 2 bangs
        Group g1 = cp5.addGroup("myGroup1")
                .setBackgroundColor(color(0, 64))
                .setBackgroundHeight(150);


        cp5.addBang("bang")
                .setPosition(10, 20)
                .setSize(100, 100)
                .moveTo(g1)
                .plugTo(this, "shuffle");
        ;

        // group number 2, contains a radiobutton
        Group g2 = cp5.addGroup("myGroup2")
                .setBackgroundColor(color(0, 64))
                .setBackgroundHeight(150);

        cp5.addRadioButton("radio")
                .setPosition(10, 20)
                .setItemWidth(20)
                .setItemHeight(20)
                .addItem("black", 0)
                .addItem("red", 1)
                .addItem("green", 2)
                .addItem("blue", 3)
                .addItem("grey", 4)
                .setColorLabel(color(255))
                .activate(2)
                .moveTo(g2);

        // group number 3, contains a bang and a slider
        Group g3 = cp5.addGroup("myGroup3")
                .setBackgroundColor(color(0, 64))
                .setBackgroundHeight(150);

        cp5.addBang("shuffle")
                .setPosition(50, 80)
                .setSize(40, 50)
                .moveTo(g3);

        cp5.addSlider("hello")
                .setPosition(15, 20)
                .setSize(100, 20)
                .setRange(100, 500)
                .setValue(100)
                .moveTo(g3);

        cp5.addSlider("world")
                .setPosition(15, 50)
                .setSize(100, 20)
                .setRange(100, 500)
                .setValue(200)
                .moveTo(g3);

        // create a new accordion
        // add g1, g2, and g3 to the accordion.
        accordion = cp5.addAccordion("acc")
                .setPosition(5, 20)
                .setWidth(150)
                .addItem(g1)
                .addItem(g2)
                .addItem(g3);

        cp5.mapKeyFor(new ControlKey() {
            public void keyEvent() {
                accordion.open(0, 1, 2);
            }
        }, 'o');
        cp5.mapKeyFor(new ControlKey() {
            public void keyEvent() {
                accordion.close(0, 1, 2);
            }
        }, 'c');
        cp5.mapKeyFor(new ControlKey() {
            public void keyEvent() {
                accordion.setWidth(100);
            }
        }, '1');
        cp5.mapKeyFor(new ControlKey() {
            public void keyEvent() {
                accordion.setPosition(0, 0);
                accordion.setItemHeight(190);
            }
        }, '2');
        cp5.mapKeyFor(new ControlKey() {
            public void keyEvent() {
                accordion.setCollapseMode(ControlP5.ALL);
            }
        }, '3');
        cp5.mapKeyFor(new ControlKey() {
            public void keyEvent() {
                accordion.setCollapseMode(ControlP5.SINGLE);
            }
        }, '4');
        cp5.mapKeyFor(new ControlKey() {
            public void keyEvent() {
                cp5.remove("myGroup1");
            }
        }, '0');

        accordion.open(0, 1, 2);

        // use Accordion.MULTI to allow multiple group 
        // to be open at a time.
        accordion.setCollapseMode(Accordion.MULTI);

        // when in SINGLE mode, only 1 accordion  
        // group can be open at a time.  
        // accordion.setCollapseMode(Accordion.SINGLE);
    }

    public void Iniciar(int theValue) {
        println("a button event from colorA: " + theValue);
    }

    public void Detener(int theValue) {
        println("a button event from colorB: " + theValue);
    }

    public void Defecto(int theValue) {
        println("a button event from colorC: " + theValue);
    }

    public void radio(int theC) {
        switch (theC) {
            case (0):
                c = color(0, 200);
                println("a radio Button event: " + c);
                break;
            case (1):
                c = color(255, 0, 0, 200);
                println("a radio Button event: " + c);
                break;
            case (2):
                c = color(0, 200, 140, 200);
                println("a radio Button event: " + c);
                break;
            case (3):
                c = color(0, 128, 255, 200);
                println("a radio Button event: " + c);
                break;
            case (4):
                c = color(50, 128);
                println("a radio Button event: " + c);
                break;
        }
    }

    public void shuffle() {
        c = color(random(255), random(255), random(255), random(128, 255));
    }

    public void inicializarMapa() {

        fill(lerpColor(color(0, 150, 150), color(0, 150, 150), 80));
        rect(0, 0, 160, 650);
        stroke(200);

        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 20; j++) {
                mapa[i][j] = 0;
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

    public void pintarTransporte(int coordX, int coordY) {
        switch (TIPO_TRANSPORTE) {
            case "tipo1":
                image(TTipo1, coordX, coordY, 45, 45);
                break;
            case "tipo2":
                image(TTipo2, coordX, coordY, 45, 45);
                break;
            case "tipo3":
                image(TTipo3, coordX, coordY, 45, 45);
                break;
            case "tipo4":
                image(TTipo4, coordX, coordY, 45, 45);
                break;
        }
    }
}
