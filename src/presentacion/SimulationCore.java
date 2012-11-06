package presentacion;

import Negocio.*;
import processing.core.PApplet;
import controlP5.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import processing.core.PImage;

public class SimulationCore extends PApplet {

    ControlP5 cp5;
    private PImage iconCursor;
    private Accordion accordion;
    private int c = color(0, 160, 100);
    private PImage TType1;
    private PImage TType2;
    private PImage TType3;
    private PImage TType4;
    private PImage OType1;
    private PImage OType2;
    private PImage SPType1;
    private PImage SPType2;
    private PImage SPType3;
    private String TYPE_TRANSPORT;
    private int TYPE_OBSTRUCTION = 2;
    private String TYPE_SERVICEPOINT;
    private int WindowWidth = 1200;
    private int WindowHeight = 680;
    private int ModuleMappedWidth = 42;
    private int ModuleMappedHeight = 35;
    private int PanelWidth = 168;
    private int PanelHeight = 680;
    private int ClearanceWidth = 24;//La holgura surge del numero de columnas
    private int ClearanceHeight = 18;//La holgura surge del numero de filas
    private boolean pause = true;
    private boolean flag = true;
    private ProbNacimientos naci=new ProbNacimientos();
    private int k=0;

    public void setup() {
        size(WindowWidth, WindowHeight);
        smooth();
        noStroke();

        cp5 = new ControlP5(this);

        loadGraphics();
        optionCreate();
        changeIcon();
        changePointer();
        buttonsCreate();
        setUpEnviroment();
    }

    public void draw() {
        background(250);
        initMap();
        fill(c);

        if (pause == false && !BusinessSimulation.getInstance().nextStep(1)) {
            drawTransport();
        }
        drawServicePoint();
        drawObstruction();
    }

    void buttonsCreate() {
        cp5.addButton("Enviar")
                .setValue(0)
                .setPosition(60, 610)
                .setSize(50, 19);

        cp5.addButton("Detener")
                .setValue(100)
                .setPosition(60, 630)
                .setSize(50, 19);

        cp5.addButton("Defecto")
                .setPosition(60, 650)
                .setSize(50, 19)
                .setValue(0);
    }

    void changeIcon() {
        ImageIcon titlebaricon = new ImageIcon(loadBytes("/imagenes/angel-icon.png"));
        frame.setIconImage(titlebaricon.getImage());
        frame.setTitle("HERMES");
    }

    void changePointer() {
        cursor(iconCursor);
    }

    void loadGraphics() {
        iconCursor = loadImage("/imagenes/cursor.png");
        TType1 = loadImage("/imagenes/moto.png");
        TType2 = loadImage("/imagenes/amarillo.png");
        TType3 = loadImage("/imagenes/verde.png");
        TType4 = loadImage("/imagenes/rojo.png");
        OType1 = loadImage("/imagenes/cow1.png");
        OType2 = loadImage("/imagenes/cow2.png");
        SPType1 = loadImage("/imagenes/servicePointGreen.png");
        SPType2 = loadImage("/imagenes/servicePointYellow.png");
        SPType3 = loadImage("/imagenes/servicePointRed.png");
    }

    void optionCreate() {
        // group number 1, contains 2 bangs
        Group g1 = cp5.addGroup("Detalles Mercancia")
                .setBackgroundColor(color(0, 64))
                .setBackgroundHeight(680);
        cp5.addTextfield("Nombre Mercancia")
                .setPosition(10, 12)
                .setSize(120, 25)
                .moveTo(g1);
        cp5.addCheckBox("Perecedero")
                .setPosition(10, 55)
                .setItemWidth(20)
                .setItemHeight(20)
                .addItem("Perecedero", 0)
                .setColorLabel(color(255))
                .activate(2)
                .moveTo(g1);
        cp5.addTextlabel("Dimension de la mercancia")
                .setPosition(10, 90)
                .setSize(120, 25)
                .setText("DIMENSION DE LA MERCANCIA")
                .moveTo(g1);
        cp5.addSlider("Ancho")
                .setPosition(10, 105)
                .setSize(20, 20)
                .setRange(0, 100)
                .setValue(100)
                .moveTo(g1);

        cp5.addSlider("Largo")
                .setPosition(60, 105)
                .setSize(20, 20)
                .setRange(0, 100)
                .setValue(100)
                .moveTo(g1);

        cp5.addSlider("Alto")
                .setPosition(110, 105)
                .setSize(20, 20)
                .setRange(0, 100)
                .setValue(100)
                .moveTo(g1);
        cp5.addNumberbox("CANTIDAD A ENVIAR")
                .setPosition(10, 145)
                .setSize(130, 14)
                .setRange(1, 60)
                .setDirection(Controller.HORIZONTAL) // cambiar la dirección de control hacia la izquierda / derecha
                .setValue(100)
                .setDecimalPrecision(0)
                .moveTo(g1);
        cp5.addTextarea("Punto de Origen label")
                .setPosition(20, 185)
                .setSize(160, 65)
                .setText("\t\t\t\tSELECCIONE:\nEL PUNTO DE ORIGEN\nMARCADO POR P.S.O Y\nEL PUNTO DE DESTINO\nMARCADO POR P.S.D ")
                .moveTo(g1);
        cp5.addRadioButton("Punto de Origen")
                .setPosition(10, 240)
                .setItemWidth(20)
                .setItemHeight(20)
                .addItem("P.S.O 1", 0)
                .addItem("P.S.O 2", 1)
                .addItem("P.S.O 3", 2)
                .addItem("P.S.O 4", 3)
                .addItem("P.S.O 5", 4)
                .addItem("P.S.O 6", 5)
                .setColorLabel(color(255))
                .activate(2)
                .moveTo(g1);
        cp5.addRadioButton("Punto de Destino")
                .setPosition(80, 240)
                .setItemWidth(20)
                .setItemHeight(20)
                .addItem("P.S.D 1", 0)
                .addItem("P.S.D 2", 1)
                .addItem("P.S.D 3", 2)
                .addItem("P.S.D 4", 3)
                .addItem("P.S.D 5", 4)
                .addItem("P.S.D 6", 5)
                .setColorLabel(color(255))
                .activate(3)
                .moveTo(g1);
        accordion = cp5.addAccordion("acc")
                .setPosition(5, 20)
                .setWidth(150)
                .addItem(g1);
//                .addItem(g2)
//                .addItem(g3);
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
                cp5.remove("Detalles Mercancia");
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

    public void Enviar(int theValue) {
        setUpEnviroment();
        pause = false;
        int s1 = (int) cp5.getController("CANTIDAD A ENVIAR").getValue();     
        int r=s1; 
        int contador = 0, paq=20-s1;

        if(s1<20){
        cp5.addTextarea("Probabilidad1")
                .setPosition(10, 400)
                .setSize(150, 65)
                .setText("PARA COMPLETAR UN ENVIO DEBE ESPERAR HASTA QUE LLEGUEN "+String.valueOf(paq)+" PAQUETES MAS. SELECIONE EL TIEMPO QUE DESEA, EN QUE LLEGUEN ESTOS "+String.valueOf(paq)+" PAQUETES :");
        cp5.addSlider("   ")
                .setPosition(20, 470)
                .setSize(120, 20)
                .setRange(1,18)
                .setValue(100)
                .setDecimalPrecision(0)
                .setSliderMode(Slider.FLEXIBLE);               
        cp5.addTextarea("Tiempo Llegada")
                .setPosition(20, 490)
                .setSize(160, 65)
                .setText("TIEMPO LLEGADA DE LOS PROXIMOS "+String.valueOf(paq)+" PAQUETES");
        cp5.addButton("Aceptar")
                .setPosition(50, 520)
                .setSize(50, 19)
                .setValue(0);
             }
        if(s1>20){
        while(s1>20){
                s1=s1-20;
                k=s1;
                contador=contador+1;
                }
        if(k!=0&&k<20){
            cp5.addTextarea("Probabilidad1")
                    .setPosition(10, 400)
                    .setSize(150, 65)
                    .setText("LOS PRIMEROS "+String.valueOf(contador*20)+" PAQUETES SERAN ENVIADOS SATISFACTORIAMENTE. PARA LOS OTROS "+
                    String.valueOf(k)+ " PAQUETES, SE DEBE ESPERAR HASTA QUE SE COMPLETE EL ENVIO. "
                    + "PARA COMPLETAR EL ENVIO SE NECESITAN "+String.valueOf(20-k)+" PAQUETES MAS. SELECIONE EL TIEMPO QUE DESEA, EN QUE LLEGUEN ESTOS "+String.valueOf(20-k)+" PAQUETES :");
            cp5.addSlider("   ")
                    .setPosition(20, 470)
                    .setSize(120, 20)
                    .setRange(1, 18)
                    .setValue(100)
                    .setDecimalPrecision(0)
                    .setSliderMode(Slider.FLEXIBLE);
            
            cp5.addTextarea("Tiempo Llegada")
                    .setPosition(20, 490)
                    .setSize(160, 65)
                    .setText("TIEMPO LLEGADA DE LOS PROXIMOS "+String.valueOf(20-k)+" PAQUETES");
            cp5.addButton("Aceptar")
                    .setPosition(50, 520)
                    .setSize(50, 19)
                    .setValue(0);
        }
      }
      if(s1==60||s1==40||s1==20){
          while(s1>=20){
                s1=s1-20;
                k=s1;
                contador=contador+1;
                }

          cp5.addTextarea("Probabilidad1")
                    .setPosition(10, 400)
                    .setSize(150, 65)
                    .setText("LOS "+String.valueOf(r)+" PAQUETES SERAN ENVIADOS SATISFACTORIAMENTE EN "+String.valueOf(contador) +" ENVIOS.");      
      }  
    }

    public void Detener(int theValue) {
        BusinessSimulation.getInstance().reset();
        pause = true;
        flag = false;
    }

    public void Aceptar(int theValue) {
        int s2 = (int) cp5.getController("   ").getValue();
        int s1 = (int) cp5.getController("CANTIDAD A ENVIAR").getValue();
        int paq=20-s1;
        if(paq>0){
        cp5.addTextarea("Probabilidad22")
                .setPosition(20, 550)
                .setSize(150, 65)
                .setText(naci.prob_num_paquetes(s2,paq));           
        }else{
        cp5.addTextarea("Probabilidad22")
                .setPosition(20, 550)
                .setSize(150, 65)
                .setText(naci.prob_num_paquetes(s2,20-k));                           
        }    
    }
    
    public void Defecto(int theValue) {
        setUpEnviroment();
        flag = true;        
    }

    public void shuffle() {
        c = color(random(255), random(255), random(255), random(128, 255));
    }

    public void initMap() {

        fill(lerpColor(color(0, 150, 150), color(0, 150, 150), 80));
        rect(0, 0, PanelWidth, PanelHeight);
        stroke(200);

        for (int i = PanelWidth; i <= WindowWidth - ClearanceWidth; i++) {
            if (i % ModuleMappedWidth == 0) {
                line(i, 0, i, WindowHeight);
            }
        }
        for (int j = 0; j <= WindowHeight; j++) {
            if (j % ModuleMappedHeight == 0) {
                line(PanelWidth, j, WindowWidth - ClearanceWidth, j);
            }
        }
    }

    public void drawTransport() {
        for (Transport u : BusinessSimulation.getInstance().getTransports()) {
            switch (u.getType()) {
                case 1:
                    TYPE_TRANSPORT = "tipo1";
                    break;
                case 2:
                    TYPE_TRANSPORT = "tipo2";
                    break;
                case 3:
                    TYPE_TRANSPORT = "tipo3";
                    break;
                case 4:
                    TYPE_TRANSPORT = "tipo4";
                    break;
            }
            loadTransportGraphic((u.getPosX()) * ModuleMappedWidth + PanelWidth, (u.getPosY()) * ModuleMappedHeight);
        }
    }

    public void loadTransportGraphic(int coordX, int coordY) {
        switch (TYPE_TRANSPORT) {
            case "tipo1":
                image(TType1, coordX, coordY, 40, 40);
                break;
            case "tipo2":
                image(TType2, coordX, coordY, 40, 40);
                break;
            case "tipo3":
                image(TType3, coordX, coordY, 40, 40);
                break;
            case "tipo4":
                image(TType4, coordX, coordY, 40, 40);
                break;
        }
    }

    public void drawObstruction() {
        for (Obstruction u : BusinessSimulation.getInstance().getObstructions()) {
            switch (u.getType()) {
                case 1:
                    TYPE_OBSTRUCTION = 1;
                    break;
                case 2:
                    TYPE_OBSTRUCTION = 2;
                    break;
            }
            loadObstructionGraphic(((u.getPosX()) * ModuleMappedWidth) + PanelWidth, (u.getPosY()) * ModuleMappedHeight);
        }
    }

    public void loadObstructionGraphic(int coordX, int coordY) {
        switch (TYPE_OBSTRUCTION) {
            case 1:
                image(OType1, coordX, coordY, 40, 40);
                break;
            case 2:
                image(OType2, coordX, coordY, 40, 40);
                break;
        }
    }

    public void drawServicePoint() {
        for (ServicePoint u : BusinessSimulation.getInstance().getServicePoints()) {
            switch (u.getType()) {
                case 1:
                    TYPE_SERVICEPOINT = "tipo1";
                    break;
                case 2:
                    TYPE_SERVICEPOINT = "tipo2";
                    break;
                case 3:
                    TYPE_SERVICEPOINT = "tipo3";
                    break;
            }
            loadServicePointGraphic((u.getPosX()) * ModuleMappedWidth + PanelWidth, (u.getPosY()) * ModuleMappedHeight);
        }

    }

    public void loadServicePointGraphic(int coordX, int coordY) {
        switch (TYPE_SERVICEPOINT) {
            case "tipo1":
                image(SPType1, coordX, coordY, 40, 40);
                break;
            case "tipo2":
                image(SPType2, coordX, coordY, 40, 40);
                break;
            case "tipo3":
                image(SPType3, coordX, coordY, 40, 40);
                break;
        }
    }

    private void setUpEnviroment() {
        //orden de los parametros: PacksInStock, posX, posY, Type
        BusinessSimulation.getInstance().newServicePoint(3, 5, 3, 1);
        BusinessSimulation.getInstance().newServicePoint(9, 1, 17, 1);
        BusinessSimulation.getInstance().newServicePoint(8, 17, 15, 1);
        BusinessSimulation.getInstance().newServicePoint(2, 23, 0, 1);
        BusinessSimulation.getInstance().newServicePoint(8, 18, 5, 1);
        BusinessSimulation.getInstance().newServicePoint(7, 7, 10, 1);
    }

    public void mouseClicked() {
        if (mouseButton == RIGHT) {
            if (((mouseX - PanelWidth) / ModuleMappedWidth) >= 0 && ((mouseX - PanelWidth) / ModuleMappedWidth) <= 23
                    && (mouseY / ModuleMappedHeight) >= 0 && (mouseY / ModuleMappedHeight) <= 17) {
                BusinessSimulation.getInstance().newObstruction(((mouseX - PanelWidth) / ModuleMappedWidth),
                        (mouseY / ModuleMappedHeight), TYPE_OBSTRUCTION, 1);
            }

        }
    }
}
