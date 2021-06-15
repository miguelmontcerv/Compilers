import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;

public class ventana extends JFrame{
    
    private Scanner in = new Scanner(System.in);
    private Map<String, AFN> listaAutomatas = new HashMap<String, AFN>();
    private Map<String, AFN> especiales = new HashMap<String, AFN>();
    private Map<String, String[][]> afds = new HashMap<String, String[][]>();
    analizador anali = new analizador();
    
    JPanel panel1 = new JPanel(); //Izquierda
    JPanel panel2 = new JPanel(); //Central
    JPanel panel3 = new JPanel(); //Derecha
    JPanel panel4 = new JPanel(); //Superior
    
    public ventana(){
        setSize(1540,1024);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Analizador Lexico");
        iniciarComponentes();        
    }
    
    public void iniciarComponentes(){
                
        /* --------------------------------------------------------------------------------- */        
        JButton button1 = new JButton("Crear un AFN nuevo");
        button1.setFocusPainted(false);
        button1.setMargin(new Insets(0, 0, 0, 0));        
        button1.setBorderPainted(false);
        button1.setOpaque(true);
        button1.setBackground(new Color(29,35,57));
        button1.setForeground(Color.white);        
        
        JButton button2 = new JButton("Modificar un AFN existente");
        button2.setFocusPainted(false);
        button2.setMargin(new Insets(0, 0, 0, 0));        
        button2.setBorderPainted(false);
        button2.setOpaque(true);
        button2.setBackground(new Color(29,35,57));
        button2.setForeground(Color.white);
        
        JButton button3 = new JButton("Imprimir AFN");
        button3.setFocusPainted(false);
        button3.setMargin(new Insets(0, 0, 0, 0));        
        button3.setBorderPainted(false);
        button3.setOpaque(true);
        button3.setBackground(new Color(29,35,57));
        button3.setForeground(Color.white);
        
        JButton button4 = new JButton("Crear AFN especial");
        button4.setFocusPainted(false);
        button4.setMargin(new Insets(0, 0, 0, 0));        
        button4.setBorderPainted(false);
        button4.setOpaque(true);
        button4.setBackground(new Color(29,35,57));
        button4.setForeground(Color.white);
        
        JButton button5 = new JButton("Crear AFD con AFN especial");
        button5.setFocusPainted(false);
        button5.setMargin(new Insets(0, 0, 0, 0));        
        button5.setBorderPainted(false);
        button5.setOpaque(true);
        button5.setBackground(new Color(29,35,57));
        button5.setForeground(Color.white);
        
        JButton button6 = new JButton("Imprimir AFD");
        button6.setFocusPainted(false);
        button6.setMargin(new Insets(0, 0, 0, 0));        
        button6.setBorderPainted(false);
        button6.setOpaque(true);
        button6.setBackground(new Color(29,35,57));
        button6.setForeground(Color.white);
        
        JButton button7 = new JButton("Importar AFD");
        button7.setFocusPainted(false);
        button7.setMargin(new Insets(0, 0, 0, 0));        
        button7.setBorderPainted(false);
        button7.setOpaque(true);
        button7.setBackground(new Color(29,35,57));
        button7.setForeground(Color.white);
        
        JButton button8 = new JButton("Probar Analizador Lexico");
        button8.setFocusPainted(false);
        button8.setMargin(new Insets(0, 0, 0, 0));        
        button8.setBorderPainted(false);
        button8.setOpaque(true);
        button8.setBackground(new Color(29,35,57));
        button8.setForeground(Color.white);
        
        JButton button9 = new JButton("Probar Analizador Sintactico");
        button9.setFocusPainted(false);
        button9.setMargin(new Insets(0, 0, 0, 0));        
        button9.setBorderPainted(false);
        button9.setOpaque(true);
        button9.setBackground(new Color(29,35,57));
        button9.setForeground(Color.white);
        
        JButton button10 = new JButton("Evaluador de expresiones");
        button10.setFocusPainted(false);
        button10.setMargin(new Insets(0, 0, 0, 0));        
        button10.setBorderPainted(false);
        button10.setOpaque(true);
        button10.setBackground(new Color(29,35,57));
        button10.setForeground(Color.white);
        
        JButton button11 = new JButton("Convertidor a postfijo");
        button11.setFocusPainted(false);
        button11.setMargin(new Insets(0, 0, 0, 0));        
        button11.setBorderPainted(false);
        button11.setOpaque(true);
        button11.setBackground(new Color(29,35,57));
        button11.setForeground(Color.white);
        
        JButton button12 = new JButton("Crear AFN a partir de una expresion regular");
        button12.setFocusPainted(false);
        button12.setMargin(new Insets(0, 0, 0, 0));        
        button12.setBorderPainted(false);
        button12.setOpaque(true);
        button12.setBackground(new Color(29,35,57));
        button12.setForeground(Color.white);
        
        JButton button13 = new JButton("Crear Tabla LL1");
        button13.setFocusPainted(false);
        button13.setMargin(new Insets(0, 0, 0, 0));        
        button13.setBorderPainted(false);
        button13.setOpaque(true);
        button13.setBackground(new Color(29,35,57));
        button13.setForeground(Color.white);
        
        JButton buttonSALIR = new JButton("Salir");
        buttonSALIR.setFocusPainted(false);
        buttonSALIR.setMargin(new Insets(0, 0, 0, 0));        
        buttonSALIR.setBorderPainted(false);
        buttonSALIR.setOpaque(true);
        buttonSALIR.setBackground(new Color(29,35,57));
        buttonSALIR.setForeground(Color.white);
    
        /* --------------------------------------------------------------------------------- */
        JTextArea areaTexto = new JTextArea();
        areaTexto.setBounds(2,2,346, 968);
        areaTexto.setText("Bienvenido al Analizador Léxico");
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Roboto",Font.PLAIN,20));
        areaTexto.setBackground(new Color(168,218,220));
        areaTexto.setForeground(new Color(29,35,57));
        
        JTextArea areaTexto2 = new JTextArea();
        areaTexto2.setBounds(633-609,319-54,884,661);
        //areaTexto2.setText("Bienvenido al Analizador Léxico2 ");
        areaTexto2.setEditable(false);
        areaTexto2.setFont(new Font("Roboto",Font.PLAIN,14));
        //areaTexto2.setBackground(new Color(168,218,220));        
        areaTexto2.setForeground(new Color(29,35,57));
             
                
        this.setLayout(null);
        panel1.setLayout(null);
        panel2.setLayout(null);
        panel3.setLayout(null);
        panel4.setLayout(null);
        
        /*   Imagenes   */
        ImageIcon img = new ImageIcon("L1.png");
        JLabel et_imag = new JLabel();
        et_imag.setIcon(new ImageIcon(img.getImage().getScaledInstance(130,193,Image.SCALE_SMOOTH)));
        et_imag.setBounds(42,40, 130, 193);
        
        ImageIcon img2 = new ImageIcon("L2.png");
        JLabel et_imag2 = new JLabel();
        et_imag2.setIcon(new ImageIcon(img2.getImage().getScaledInstance(211,147,Image.SCALE_SMOOTH)));
        et_imag2.setBounds(695,57, 211, 147);        
        /*-----------------------------------------------------------------------------------*/
        /* Etiquetas */
        JLabel label1 = new JLabel("Menú",SwingConstants.CENTER);        
        label1.setFont(new Font("Roboto",Font.PLAIN,30));
        label1.setForeground(Color.white);

        JLabel label2 = new JLabel("A N A L I Z A D O R    L É X I C O",SwingConstants.CENTER);        
        label2.setFont(new Font("Roboto",Font.PLAIN,30));
        label2.setForeground(Color.black);
        
        JLabel label3 = new JLabel("Instituto Politécnico Nacional",SwingConstants.CENTER);        
        label3.setFont(new Font("Roboto",Font.PLAIN,30));
        label3.setForeground(Color.black);
                
        JLabel label4 = new JLabel("Escuela Superior de Cómputo",SwingConstants.CENTER);        
        label4.setFont(new Font("Roboto",Font.PLAIN,30));
        label4.setForeground(Color.black);
        
        JLabel label5 = new JLabel("3CM16 Compiladores",SwingConstants.CENTER);        
        label5.setFont(new Font("Roboto",Font.PLAIN,30));
        label5.setForeground(Color.black);
        
        panel1.setBackground(new Color(29,35,57));
        panel2.setBackground(new Color(168,218,220));
        panel3.setBackground(new Color(255,255,255));
        panel4.setBackground(new Color(45,123,157));
               
        panel1.setBounds(0, 0, 261, 1024); //x,y                
        panel2.setBounds(261, 54, 348, 970);
        panel3.setBounds(609, 54, 931, 970);
        panel4.setBounds(261, 0, 1279, 54);
        
        button1.setBounds(0,100,261,50); //Posicionx, posiciony, tamaño,tamaño        
        button2.setBounds(0,150,261,50); //Posicionx, posiciony, tamaño,tamaño                
        button3.setBounds(0,200,261,50); //Posicionx, posiciony, tamaño,tamaño
        button4.setBounds(0,250,261,50); //Posicionx, posiciony, tamaño,tamaño
        button5.setBounds(0,300,261,50); //Posicionx, posiciony, tamaño,tamaño
        button6.setBounds(0,350,261,50); //Posicionx, posiciony, tamaño,tamaño
        button7.setBounds(0,400,261,50); //Posicionx, posiciony, tamaño,tamaño
        button8.setBounds(0,450,261,50); //Posicionx, posiciony, tamaño,tamaño
        button9.setBounds(0,500,261,50); //Posicionx, posiciony, tamaño,tamaño
        button10.setBounds(0,550,261,50); //Posicionx, posiciony, tamaño,tamaño
        button11.setBounds(0,600,261,50); //Posicionx, posiciony, tamaño,tamaño
        button12.setBounds(0,650,261,50); //Posicionx, posiciony, tamaño,tamaño
        button13.setBounds(0,700,261,50); //Posicionx, posiciony, tamaño,tamaño
        buttonSALIR.setBounds(0,750,261,50); //Posicionx, posiciony, tamaño,tamaño        
        
        label1.setBounds(20,10,100,50); //Posicionx, posiciony, tamaño,tamaño        
        label2.setBounds(301,10,500,35); //Posicionx, posiciony, tamaño,tamaño        
        label3.setBounds(185,79,500,35); //Posicionx, posiciony, tamaño,tamaño        
        label4.setBounds(185,114,500,35); //Posicionx, posiciony, tamaño,tamaño        
        label5.setBounds(185,149,500,35); //Posicionx, posiciony, tamaño,tamaño        
        
        panel1.add(label1);                
        panel4.add(label2);
        panel3.add(label3);
        panel3.add(label4);
        panel3.add(label5);
        
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);
        panel1.add(button5);
        panel1.add(button6);
        panel1.add(button7);
        panel1.add(button8);
        panel1.add(button9);
        panel1.add(button10);
        panel1.add(button11);
        panel1.add(button12);
        panel1.add(button13);
        panel1.add(buttonSALIR);        
        
        panel3.add(et_imag);
        panel3.add(et_imag2);
        
        panel2.add(areaTexto);        
        panel3.add(areaTexto2);
        
        ActionListener oyente = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JButton btn1 = new JButton(); btn1 = (JButton) e.getSource();
                if(btn1.getText() == "Crear un AFN nuevo"){
                    button1.setBackground(new Color(78,98,125));
                    button2.setBackground(new Color(29,35,57));
                    button3.setBackground(new Color(29,35,57));
                    button4.setBackground(new Color(29,35,57));
                    button5.setBackground(new Color(29,35,57));
                    button6.setBackground(new Color(29,35,57));
                    button7.setBackground(new Color(29,35,57));
                    button8.setBackground(new Color(29,35,57));
                    button9.setBackground(new Color(29,35,57));
                    button10.setBackground(new Color(29,35,57));
                    button11.setBackground(new Color(29,35,57));
                    buttonSALIR.setBackground(new Color(29,35,57));                    
                    areaTexto.append("\n"+btn1.getText());
                    anali.menu(1);
                }
                if(btn1.getText() == "Modificar un AFN existente"){
                    button1.setBackground(new Color(29,35,57));
                    button2.setBackground(new Color(78,98,125));                    
                    button3.setBackground(new Color(29,35,57));
                    button4.setBackground(new Color(29,35,57));
                    button5.setBackground(new Color(29,35,57));
                    button6.setBackground(new Color(29,35,57));
                    button7.setBackground(new Color(29,35,57));
                    button8.setBackground(new Color(29,35,57));
                    button9.setBackground(new Color(29,35,57));
                    button10.setBackground(new Color(29,35,57));
                    button11.setBackground(new Color(29,35,57));
                    buttonSALIR.setBackground(new Color(29,35,57));                    
                    areaTexto.append("\n"+btn1.getText());
                    anali.menu(2);
                }
                if(btn1.getText() == "Imprimir AFN"){
                    areaTexto2.setText("");
                    button1.setBackground(new Color(29,35,57));                    
                    button2.setBackground(new Color(29,35,57));
                    button3.setBackground(new Color(78,98,125));                    
                    button4.setBackground(new Color(29,35,57));
                    button5.setBackground(new Color(29,35,57));
                    button6.setBackground(new Color(29,35,57));
                    button7.setBackground(new Color(29,35,57));
                    button8.setBackground(new Color(29,35,57));
                    button9.setBackground(new Color(29,35,57));
                    button10.setBackground(new Color(29,35,57));
                    button11.setBackground(new Color(29,35,57));
                    buttonSALIR.setBackground(new Color(29,35,57));                    
                    areaTexto.append("\n"+btn1.getText());
                    anali.menu(3);
                    
                    String palabra = anali.publicar();
                    areaTexto2.append("\n"+palabra);
                    
                }
                if(btn1.getText() == "Crear AFN especial"){
                    button1.setBackground(new Color(29,35,57));                    
                    button2.setBackground(new Color(29,35,57));
                    button3.setBackground(new Color(29,35,57));
                    button4.setBackground(new Color(78,98,125));                                        
                    button5.setBackground(new Color(29,35,57));
                    button6.setBackground(new Color(29,35,57));
                    button7.setBackground(new Color(29,35,57));
                    button8.setBackground(new Color(29,35,57));  
                    button9.setBackground(new Color(29,35,57));
                    button10.setBackground(new Color(29,35,57));
                    button11.setBackground(new Color(29,35,57));
                    buttonSALIR.setBackground(new Color(29,35,57));                    
                    areaTexto.append("\n"+btn1.getText());
                    anali.menu(4);
                }
                if(btn1.getText() == "Crear AFD con AFN especial"){
                    areaTexto2.setText("");
                    button1.setBackground(new Color(29,35,57));                    
                    button2.setBackground(new Color(29,35,57));
                    button3.setBackground(new Color(29,35,57));
                    button4.setBackground(new Color(29,35,57));
                    button5.setBackground(new Color(78,98,125));                                                            
                    button6.setBackground(new Color(29,35,57));
                    button7.setBackground(new Color(29,35,57));
                    button8.setBackground(new Color(29,35,57));
                    button9.setBackground(new Color(29,35,57));
                    button10.setBackground(new Color(29,35,57));
                    button11.setBackground(new Color(29,35,57));
                    buttonSALIR.setBackground(new Color(29,35,57));                    
                    areaTexto.append("\n"+btn1.getText());
                    anali.menu(5);
                    
                    String palabra = anali.publicar();
                    areaTexto2.append("\n"+palabra);
                }
                if(btn1.getText() == "Imprimir AFD"){                                        
                    areaTexto2.setText("");
                    button1.setBackground(new Color(29,35,57));                    
                    button2.setBackground(new Color(29,35,57));
                    button3.setBackground(new Color(29,35,57));
                    button4.setBackground(new Color(29,35,57));
                    button5.setBackground(new Color(29,35,57));
                    button6.setBackground(new Color(78,98,125));                                                                                
                    button7.setBackground(new Color(29,35,57));
                    button8.setBackground(new Color(29,35,57));
                    button9.setBackground(new Color(29,35,57));
                    button10.setBackground(new Color(29,35,57));
                    button11.setBackground(new Color(29,35,57));
                    buttonSALIR.setBackground(new Color(29,35,57));                    
                    areaTexto.append("\n"+btn1.getText());
                    anali.menu(6);
                    
                    String palabra = anali.publicar();
                    areaTexto2.append("\n"+palabra);
                }
                if(btn1.getText() == "Importar AFD"){
                    button1.setBackground(new Color(29,35,57));                    
                    button2.setBackground(new Color(29,35,57));
                    button3.setBackground(new Color(29,35,57));
                    button4.setBackground(new Color(29,35,57));
                    button5.setBackground(new Color(29,35,57));                                                                                                    
                    button6.setBackground(new Color(29,35,57));
                    button7.setBackground(new Color(78,98,125));
                    button8.setBackground(new Color(29,35,57));
                    button9.setBackground(new Color(29,35,57));
                    button10.setBackground(new Color(29,35,57));
                    button11.setBackground(new Color(29,35,57));
                    buttonSALIR.setBackground(new Color(29,35,57));                    
                    areaTexto.append("\n"+btn1.getText());
                    anali.menu(8);
                }
                if(btn1.getText() == "Salir"){
                    button1.setBackground(new Color(29,35,57));                    
                    button2.setBackground(new Color(29,35,57));
                    button3.setBackground(new Color(29,35,57));
                    button4.setBackground(new Color(29,35,57));
                    button5.setBackground(new Color(29,35,57));                                                                                                    
                    button6.setBackground(new Color(29,35,57));
                    button7.setBackground(new Color(29,35,57));                    
                    button8.setBackground(new Color(29,35,57));   
                    button9.setBackground(new Color(29,35,57));
                    button10.setBackground(new Color(29,35,57));
                    button11.setBackground(new Color(29,35,57));
                    buttonSALIR.setBackground(new Color(78,98,125)); 
                    areaTexto.append("\n"+btn1.getText());
                    System.exit(0);
                    anali.menu(14);
                }
                if(btn1.getText() == "Probar Analizador Lexico"){
                    areaTexto2.setText("");
                    button1.setBackground(new Color(29,35,57));                    
                    button2.setBackground(new Color(29,35,57));
                    button3.setBackground(new Color(29,35,57));
                    button4.setBackground(new Color(29,35,57));
                    button5.setBackground(new Color(29,35,57));                                                                                                    
                    button6.setBackground(new Color(29,35,57));
                    button7.setBackground(new Color(29,35,57));
                    button8.setBackground(new Color(78,98,125));
                    button9.setBackground(new Color(29,35,57));
                    button10.setBackground(new Color(29,35,57));
                    button11.setBackground(new Color(29,35,57));
                    buttonSALIR.setBackground(new Color(29,35,57));                                        
                    areaTexto.append("\n"+btn1.getText());                    
                    anali.menu(9);
                    
                    String palabra = anali.publicar();
                    areaTexto2.append("\n"+palabra);
                }
                
                if(btn1.getText() == "Probar Analizador Sintactico"){
                    //areaTexto2.setText("");
                    JOptionPane.showMessageDialog(null,"Se probará el analizador sintactico para las expresiones numericas!");
                    button1.setBackground(new Color(29,35,57));                    
                    button2.setBackground(new Color(29,35,57));
                    button3.setBackground(new Color(29,35,57));
                    button4.setBackground(new Color(29,35,57));
                    button5.setBackground(new Color(29,35,57));                                                                                                    
                    button6.setBackground(new Color(29,35,57));
                    button7.setBackground(new Color(29,35,57));                    
                    button8.setBackground(new Color(29,35,57));
                    button9.setBackground(new Color(78,98,125));
                    button10.setBackground(new Color(29,35,57));
                    button11.setBackground(new Color(29,35,57));
                    buttonSALIR.setBackground(new Color(29,35,57));                                        
                    areaTexto.append("\n"+btn1.getText());                    
                    anali.menu(10);
                    
                    //String palabra = anali.publicar();
                    //areaTexto2.append("\n"+palabra);
                }
                if(btn1.getText() == "Evaluador de expresiones"){
                    //areaTexto2.setText("");
                    JOptionPane.showMessageDialog(null,"Se evaluaran las expresiones numericas!");
                    button1.setBackground(new Color(29,35,57));                    
                    button2.setBackground(new Color(29,35,57));
                    button3.setBackground(new Color(29,35,57));
                    button4.setBackground(new Color(29,35,57));
                    button5.setBackground(new Color(29,35,57));                                                                                                    
                    button6.setBackground(new Color(29,35,57));
                    button7.setBackground(new Color(29,35,57));                    
                    button8.setBackground(new Color(29,35,57));                    
                    button9.setBackground(new Color(29,35,57));
                    button10.setBackground(new Color(78,98,125));
                    button11.setBackground(new Color(29,35,57));
                    buttonSALIR.setBackground(new Color(29,35,57));                                        
                    areaTexto.append("\n"+btn1.getText());                    
                    anali.menu(11);
                    
                    //String palabra = anali.publicar();
                    //areaTexto2.append("\n"+palabra);
                }
                if(btn1.getText() == "Convertidor a postfijo"){
                    //areaTexto2.setText("");
                    JOptionPane.showMessageDialog(null,"Es un convertidor a postfijo de expresiones numericas!");
                    button1.setBackground(new Color(29,35,57));                    
                    button2.setBackground(new Color(29,35,57));
                    button3.setBackground(new Color(29,35,57));
                    button4.setBackground(new Color(29,35,57));
                    button5.setBackground(new Color(29,35,57));                                                                                                    
                    button6.setBackground(new Color(29,35,57));
                    button7.setBackground(new Color(29,35,57));                    
                    button8.setBackground(new Color(29,35,57));                    
                    button9.setBackground(new Color(29,35,57));                    
                    button10.setBackground(new Color(29,35,57));
                    button11.setBackground(new Color(78,98,125));
                    buttonSALIR.setBackground(new Color(29,35,57));                                        
                    areaTexto.append("\n"+btn1.getText());                    
                    anali.menu(12);
                    
                    //String palabra = anali.publicar();
                    //areaTexto2.append("\n"+palabra);
                }
                if(btn1.getText() == "Crear AFN a partir de una expresion regular"){
                    //areaTexto2.setText("");
                    button1.setBackground(new Color(29,35,57));                    
                    button2.setBackground(new Color(29,35,57));
                    button3.setBackground(new Color(29,35,57));
                    button4.setBackground(new Color(29,35,57));
                    button5.setBackground(new Color(29,35,57));                                                                                                    
                    button6.setBackground(new Color(29,35,57));
                    button7.setBackground(new Color(29,35,57));                    
                    button8.setBackground(new Color(29,35,57));                    
                    button9.setBackground(new Color(29,35,57));                    
                    button10.setBackground(new Color(29,35,57));
                    button11.setBackground(new Color(29,35,57));
                    button12.setBackground(new Color(78,98,125));
                    buttonSALIR.setBackground(new Color(29,35,57));                                        
                    areaTexto.append("\n"+btn1.getText());                    
                    anali.menu(13);
                    
                    //String palabra = anali.publicar();
                    //areaTexto2.append("\n"+palabra);
                }
                if(btn1.getText() == "Crear Tabla LL1"){
                    //areaTexto2.setText("");
                    button1.setBackground(new Color(29,35,57));                    
                    button2.setBackground(new Color(29,35,57));
                    button3.setBackground(new Color(29,35,57));
                    button4.setBackground(new Color(29,35,57));
                    button5.setBackground(new Color(29,35,57));                                                                                                    
                    button6.setBackground(new Color(29,35,57));
                    button7.setBackground(new Color(29,35,57));                    
                    button8.setBackground(new Color(29,35,57));                    
                    button9.setBackground(new Color(29,35,57));                    
                    button10.setBackground(new Color(29,35,57));
                    button11.setBackground(new Color(29,35,57));
                    button12.setBackground(new Color(29,35,57));
                    button13.setBackground(new Color(78,98,125));
                    buttonSALIR.setBackground(new Color(29,35,57));                                        
                    areaTexto.append("\n"+btn1.getText());                    
                    anali.menu(14);
                    
                    //String palabra = anali.publicar();
                    //areaTexto2.append("\n"+palabra);
                }
            }
        };
        
        button1.addActionListener(oyente);
        button2.addActionListener(oyente);
        button3.addActionListener(oyente);
        button4.addActionListener(oyente);
        button5.addActionListener(oyente);
        button6.addActionListener(oyente);
        button7.addActionListener(oyente);
        button8.addActionListener(oyente);
        button9.addActionListener(oyente);
        button10.addActionListener(oyente);
        button11.addActionListener(oyente);
        button12.addActionListener(oyente);
        button13.addActionListener(oyente);
        buttonSALIR.addActionListener(oyente);        
        
        this.getContentPane().add(panel1);
        this.getContentPane().add(panel2);
        this.getContentPane().add(panel3);
        this.getContentPane().add(panel4);
    }
    
    public void escribir(String palabras){
        
        
        
    }
        
}

