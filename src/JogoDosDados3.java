import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class JogoDosDados3 extends JFrame {
	
	private static int t = 0;
	private static int times = 0;
	private static int maxTimes = 13;
	private static int auxMax1=0, auxMax2=0, auxMax3=0, auxMax4=0, auxMax5=0, auxMax6=0;
	public static int total = 0;
	private static int n = 0;
	private static boolean seq4=false, seq5=false, full=true, same3=false, same4=false, fullen=false;
	private static int count=0, same=0, chance=0, somarMaxs=0;
	private static boolean stop1 = false, stop2 = false, stop3 = false, stop4 = false, stop5 = false, stop6 = false;
	private static Dices dice;
	private static int[] saveDices = new int[5];
	private static int[] saveDices2 = new int[5];
	private static String[] guardaDados = new String[13];
	private static String[] guardaInfo = new String[14]; //tirar isto
	private int record = 0 ;
	public static String name = "", namePlayer = "Anonimos";
	public String recordBase, nameBase;
	protected static JPanel panel;
	private JButton buttonRoll5, buttonRoll2;
	private JLabel labelTimes, labelResult, labelMaxs, labelRecord, labelMyRecord;
	private JToggleButton buttonMax1, buttonMax2, buttonMax3,buttonMax4, buttonMax5, buttonMax6,
		buttonSame5, buttonSequence4, buttonSequence5, buttonSame3, buttonSame4,buttonFullen,
		buttonChance, buttonDice1, buttonDice2, buttonDice3, buttonDice4, buttonDice5;
	private JTextField caixa = new JTextField(20);
	
	private boolean[] saveAchiev = new boolean[7];
	private ImageIcon icon = new ImageIcon("taca_1.png");
	private ImageIcon medal = new ImageIcon("medal.png");
	private ImageIcon medals2 = new ImageIcon("medals0.jpg");
	private ImageIcon medals1 = new ImageIcon("medals1.jpg");
	private ImageIcon medals0 = new ImageIcon("medals2.jpg");
	private ImageIcon medals3 = new ImageIcon("rsz_medals3.png");
	private ImageIcon medals4 = new ImageIcon("medals4.jpg");
	private ImageIcon rules = new ImageIcon("rsz_rules.png");
	private ImageIcon firstPlace = new ImageIcon("rsz_firstplace.jpg");
	private ImageIcon recordPessoal = new ImageIcon("rsz_recordpessoal.jpg");
	
	public JogoDosDados3() {
		
		dice = new Dices();
		saveDicesOfRound();
		
		setLocationByPlatform(true);
	    setSize(100, 500);
	    setMinimumSize(new Dimension(1350,400));
	    setMaximumSize(new Dimension(1350, 400)); 
	    setTitle("Jogo Dos Dados");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setVisible(false);
	}
	
	
	public void creatView() {
		setVisible(true);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(10,10,10,10));
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);
		panel.setVisible(true);
		System.out.println("Passou aqui");
		
		
//North #######################################################################################################################
		JPanel panelNorth = new JPanel(new BorderLayout());
		panel.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout( new BorderLayout() );
		panelNorth.setBorder(new EmptyBorder(0, 30, 0, 30)); 
	
		JPanel panelNorth2 = new JPanel();
		
		JButton buttonRoll = new JButton("Rules");
		panelNorth2.add(buttonRoll);
		JLabel labelTextIntro = new JLabel("<html><body>Não vale fazer batota!!"
				+ "<br><font size=13>Como jogar:</font><br>"
				+ "<br><font size=5>->Rodar os dados durante 3 vezes e fixar algum dado"
				+ "<br> se necessário durante essas 3 vezes"
				+ "<br>->Escolher uma opção em baixo"
				+ "<br>->Rodar os dados...(Repetir este processo durante 13 vezes)"
				+ "<br></font>"
				+ "<br><font size=13>Points:</font>"
				+ "<br><font size=5>->Max's    = vezes que o numero aprece * numero"
				+ "<br>->3 iguias = 15"
				+ "<br>->4 iguias = 25"
				+ "<br>->5 iguias = 50"
				+ "<br>->Sequencia de 4 = 30"
				+ "<br>->Sequencia de 5 = 40"
				+ "<br>->Fullen = 25 (são 2 dados iguais + 3 dados iguais)"
				+ "<br>->Chance = os pontos é a soma os 5 dados na mesa"
				+ "<br>->Bonus = 35 (só se obtiveres mais/igual 65 pontos nos Max's) "
				+ "<br></font></body></html>");
		buttonRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getContentPane(), labelTextIntro,
						 "RULES ", JOptionPane.INFORMATION_MESSAGE, rules);
			}
		});
		
		JButton buttonachievements = new JButton("Achievements");
		panelNorth2.add(buttonachievements);
		
		panelNorth.add(panelNorth2, BorderLayout.WEST);
		buttonachievements.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JPanel p = new JPanel(new GridLayout(0, 1));
				JLabel labelTextWhite = new JLabel("");
				JLabel labelTextWhite1 = new JLabel("");
				 
	    		JLabel labelText0 = new JLabel("-> Play 10 Times.");
	    		labelText0.setForeground(new Color(0,0,0));
	    		labelText0.setFont(new Font("Jokerman", Font.CENTER_BASELINE, 18));
	    		if(Login.arrayAchiev[0] == '1') {
	    			labelText0.setForeground(new Color(110,170,94));
	    			labelText0.setFont(new Font("Jokerman", Font.CENTER_BASELINE, 18));
	    			labelText0.setText("Play 10 Times.");
	    			if(Login.arrayAchiev2[0] == '0') labelText0.setFont(new Font("Serif", Font.TRUETYPE_FONT, 21));
	    		}
	    		p.add(labelText0);
	            
	            JLabel labelText1 = new JLabel("-> Play 30 Times.");
	            labelText1.setForeground(new Color(0,0,0));
	    		labelText1.setFont(new Font("Jokerman", Font.CENTER_BASELINE, 18));
	            if(Login.arrayAchiev[1] == '1') {
	            	labelText1.setForeground(new Color(110,170,94)); 
	            	labelText1.setFont(new Font("Jokerman", Font.CENTER_BASELINE, 16));
	            	labelText1.setText("Play 10 Times.");
	            	if(Login.arrayAchiev2[1] == '0') labelText1.setFont(new Font("Serif", Font.TRUETYPE_FONT, 21));
	            }
	            p.add(labelText1);
	            
	            JLabel labelText2 = new JLabel("-> Play 100 Times.");
	            labelText2.setForeground(new Color(0,0,0));
	    		labelText2.setFont(new Font("Jokerman", Font.CENTER_BASELINE, 18));
	            if(Login.arrayAchiev[2] == '1') {
	            	labelText2.setForeground(new Color(110,170,94)); 
	            	labelText2.setFont(new Font("Jokerman", Font.CENTER_BASELINE, 18));
	            	labelText2.setText("Play 100 Times.");
	            	if(Login.arrayAchiev2[2] == '0') labelText2.setFont(new Font("Serif", Font.TRUETYPE_FONT, 21));
	            }
	            p.add(labelText2);
	            p.add(labelTextWhite);
	            
	            JLabel labelText3 = new JLabel("-> Fazer 3iguais, 4iguais e 5iguais no mesmo jogo.");
	            labelText3.setForeground(new Color(0,0,0));
	    		labelText3.setFont(new Font("Jokerman", Font.CENTER_BASELINE, 18));
	            if(Login.arrayAchiev[3] == '1') {
	            	labelText3.setForeground(new Color(110,170,94));
	            	labelText3.setText("Fazer 3iguais, 4iguais e 5iguais no mesmo jogo.");
	            	if(Login.arrayAchiev2[3] == '0') labelText3.setFont(new Font("Serif", Font.TRUETYPE_FONT, 21));
	            }
	            p.add(labelText3);
	            
	            JLabel labelText4 = new JLabel("-> Fazer Seq4 e Seq5 no mesmo jogo.");
	            labelText4.setForeground(new Color(0,0,0));
	    		labelText4.setFont(new Font("Jokerman", Font.CENTER_BASELINE, 18));
	            if(Login.arrayAchiev[4] == '1') {
	            	labelText4.setForeground(new Color(110,170,94));
	            	labelText4.setText("Fazer Seq4 e Seq5 no mesmo jogo.");
	            	if(Login.arrayAchiev2[4] == '0') labelText4.setFont(new Font("Serif", Font.TRUETYPE_FONT, 21));
	            }
	            p.add(labelText4);
	            
	            JLabel labelText5 = new JLabel("-> Fazer 3iguais, 4iguais, 5iguais, Seq4 e Seq5 no mesmo jogo.");
	            labelText5.setForeground(new Color(0,0,0));
	    		labelText5.setFont(new Font("Jokerman", Font.CENTER_BASELINE, 18));
	            if(Login.arrayAchiev[5] == '1') {
	            	labelText5.setForeground(new Color(110,170,94));
	            	labelText5.setText("Fazer 3iguais, 4iguais, 5iguais, Seq4 e Seq5 no mesmo jogo.");
	            	if(Login.arrayAchiev2[5] == '0') labelText5.setFont(new Font("Serif", Font.TRUETYPE_FONT, 21));
	            }
	            p.add(labelText5);
	            p.add(labelTextWhite1);
	            
	            JLabel labelText6 = new JLabel("-> Ativar o bonus 1 vez.");
	            labelText6.setForeground(new Color(0,0,0));
	    		labelText6.setFont(new Font("Jokerman", Font.CENTER_BASELINE, 18));
	            if(Login.arrayAchiev[6] == '1') {
	            	labelText6.setForeground(new Color(110,170,94));
	            	labelText6.setText("Ativar o bonus 1 vez.");
	            	if(Login.arrayAchiev2[6] == '0') labelText6.setFont(new Font("Serif", Font.TRUETYPE_FONT, 21));
	            }
	            p.add(labelText6);
	            
	            JLabel labelText7 = new JLabel("-> Ativar o bonus com mais de 80 Max's points.");
	            labelText7.setForeground(new Color(0,0,0));
	    		labelText7.setFont(new Font("Jokerman", Font.CENTER_BASELINE, 18));
	            if(Login.arrayAchiev[7] == '1') {
	            	labelText7.setForeground(new Color(110,170,94));
	            	labelText7.setText("Ativar o bonus com mais de 80 Max's points.");
	            	if(Login.arrayAchiev2[7] == '0') labelText7.setFont(new Font("Serif", Font.TRUETYPE_FONT, 21));
	            }
	            p.add(labelText7);
	            
	            JLabel labelText8 = new JLabel("-> Ativar o bonus com mais de 85 Max's points.");
	            labelText8.setForeground(new Color(0,0,0));
	    		labelText8.setFont(new Font("Jokerman", Font.CENTER_BASELINE, 18));
	            if(Login.arrayAchiev[8] == '1') {
	            	labelText8.setForeground(new Color(110,170,94));
	            	labelText8.setText("Ativar o bonus com mais de 85 Max's points.");
	            	if(Login.arrayAchiev2[8] == '0') labelText8.setFont(new Font("Serif", Font.TRUETYPE_FONT, 21));
	            }
	            p.add(labelText8);

	            JOptionPane.showMessageDialog(null, p,
						 "ACHIEVEMENTS ", JOptionPane.INFORMATION_MESSAGE, medals3);
			}
		});
		
		
		labelRecord = new JLabel("RECORD:  " + Login.first + " ");
		labelRecord.setFont(new Font("Tahoma" , 2, 20));
		panelNorth.add(labelRecord, BorderLayout.EAST);
	
		
//Center #######################################################################################################################
		JPanel panelCenter = new JPanel(new BorderLayout());
		panel.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setBorder(new EmptyBorder(100, 10, 100, 10)); 
		panelCenter.setLayout(new GridLayout(1,7));
		panelCenter.setSize(50, 50);
		
		buttonRoll5 = new JButton(t + " of 3");
		panelCenter.add(buttonRoll5, BorderLayout.CENTER);
		buttonRoll5.setEnabled(false);
		
		JLabel label0 = new JLabel();
		panelCenter.add(label0, BorderLayout.CENTER);
		
		buttonDice1 = new JToggleButton("Dice");
		buttonDice1.setEnabled(false);
		panelCenter.add(buttonDice1, BorderLayout.CENTER);
		
		buttonDice2 = new JToggleButton("Dice");
		buttonDice2.setEnabled(false);
		panelCenter.add(buttonDice2, BorderLayout.CENTER);
		
		buttonDice3 = new JToggleButton("Dice");
		buttonDice3.setEnabled(false);
		panelCenter.add(buttonDice3, BorderLayout.CENTER);
		
		buttonDice4 = new JToggleButton("Dice");
		buttonDice4.setEnabled(false);
		panelCenter.add(buttonDice4, BorderLayout.CENTER);
		
		buttonDice5 = new JToggleButton("Dice");
		buttonDice5.setEnabled(false);
		panelCenter.add(buttonDice5, BorderLayout.CENTER);
		

		buttonDice1.addItemListener(new ItemListener() {
			 public void itemStateChanged(ItemEvent ev) {
			      if(ev.getStateChange()==ItemEvent.SELECTED){
			    	  saveDices2[0] = saveDices[0];
			      }
			  }
		});
		
		buttonDice2.addItemListener(new ItemListener() {
			 public void itemStateChanged(ItemEvent ev) {
			      if(ev.getStateChange()==ItemEvent.SELECTED){
			    	  saveDices2[1] = saveDices[1];
			      }
			  }
		});
		
		buttonDice3.addItemListener(new ItemListener() {
			 public void itemStateChanged(ItemEvent ev) {
			      if(ev.getStateChange()==ItemEvent.SELECTED){
			    	  saveDices2[2] = saveDices[2];
			      }
			  }
		});
		
		buttonDice4.addItemListener(new ItemListener() {
			 public void itemStateChanged(ItemEvent ev) {
			      if(ev.getStateChange()==ItemEvent.SELECTED){
			    	  saveDices2[3] = saveDices[3];
			      }
			  }
		});
		
		buttonDice5.addItemListener(new ItemListener() {
			 public void itemStateChanged(ItemEvent ev) {
			      if(ev.getStateChange()==ItemEvent.SELECTED){
			    	  saveDices2[4] = saveDices[4];
			      }
			  }
		});
		
		buttonRoll5.setFont(new Font("Arial" , 1, 20));
		buttonDice1.setFont(new Font("Arial" , 1, 35));
		buttonDice2.setFont(new Font("Arial" , 1, 35));
		buttonDice3.setFont(new Font("Arial" , 1, 35));
		buttonDice4.setFont(new Font("Arial" , 1, 35));
		buttonDice5.setFont(new Font("Arial" , 1, 35));

		//set button size
	    Dimension d = new Dimension(10,10);

	    buttonDice1.setSize(d);
	    buttonDice2.setSize(d);
	    buttonDice3.setSize(d); 
	    buttonDice4.setSize(d);
	    buttonDice5.setSize(d);
		

//West #######################################################################################################################
		JPanel panelWest = new JPanel(new BorderLayout());
		panel.add(panelWest, BorderLayout.WEST);
		
		panelWest.setBorder(new EmptyBorder(30, 30, 30, 30)); 
		panelWest.setLayout(new GridLayout(3,1));
		
		labelTimes = new JLabel("Times Roled: "+ times);
		buttonRoll2 = new JButton("Roll the Dices");
		labelMaxs = new JLabel("<html><body><br>Max1 = 0 &emsp;&emsp;&emsp; Max4 = 0"+
							   "<br>Max2 = 0 &emsp;&emsp;&emsp; Max5 = 0"+
							   "<br>Max3 = 0 &emsp;&emsp;&emsp; Max6 = 0"+
							   " &emsp;&emsp; Bonus not active</body></html>");
		
		panelWest.add(labelTimes, BorderLayout.NORTH);
		panelWest.add(buttonRoll2);
		panelWest.add(labelMaxs);
		
		
		labelTimes.setFont(new Font("Arial" , 1, 30));
		buttonRoll2.setFont(new Font("Sansserif", Font.PLAIN, 24));
		
		buttonRoll2.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				if(buttonRoll2.isEnabled()) {	
					dice = new Dices();
					saveDicesOfRound();
					t++;
					buttonRoll5.setText(t + " of 3");
					
					buttonDice1.setEnabled(true);
					buttonDice2.setEnabled(true);
					buttonDice3.setEnabled(true);
					buttonDice4.setEnabled(true);
					buttonDice5.setEnabled(true);
					
					if(t==3) {
		
						buttonRoll2.setEnabled(false);
						buttonMax1.setEnabled(true);
						buttonMax2.setEnabled(true);
						buttonMax3.setEnabled(true);
						buttonMax4.setEnabled(true);
						buttonMax5.setEnabled(true);
						buttonMax6.setEnabled(true);
						buttonChance.setEnabled(true);
						buttonSame3.setEnabled(true);
						buttonSame4.setEnabled(true);
						buttonSame5.setEnabled(true);
						buttonSequence4.setEnabled(true);
						buttonSequence5.setEnabled(true);
						buttonFullen.setEnabled(true);
						
					}
					
					if(buttonDice1.isSelected()) saveDices[0]=saveDices2[0];
					if(buttonDice2.isSelected()) saveDices[1]=saveDices2[1];
					if(buttonDice3.isSelected()) saveDices[2]=saveDices2[2];
					if(buttonDice4.isSelected()) saveDices[3]=saveDices2[3];
					if(buttonDice5.isSelected()) saveDices[4]=saveDices2[4];
					
					buttonDice1.setText("" + saveDices[0]);
					buttonDice2.setText("" + saveDices[1]);
					buttonDice3.setText("" + saveDices[2]);
					buttonDice4.setText("" + saveDices[3]);
					buttonDice5.setText("" + saveDices[4]);
					
					if(t==3) {
						guardaDados[times] = "" + saveDices[0] + saveDices[1] + saveDices[2] + saveDices[3] + saveDices[4];
						
					}
				}
			}
		});
		
			
//East #######################################################################################################################
		JPanel panelEast = new JPanel(new BorderLayout());
		panel.add(panelEast, BorderLayout.EAST);
		panelEast.setBorder(new EmptyBorder(10, 10, 10, 30)); 
		
		JPanel p2 = new JPanel(new GridLayout(0, 1));
		//p2.setSize(400, 500);
		//p2.setBackground(Color.black);
		
		labelResult = new JLabel();
		labelResult.setText("TOTAL:  " + total);
		labelResult.setFont(new Font("Arial" , 1, 30));
		p2.add(labelResult);
		
		labelMyRecord = new JLabel("My Record: " + Login._score);
		System.out.println("Login._score: " + Login._score);
		p2.add(labelMyRecord);
		
		panelEast.add(p2, BorderLayout.SOUTH);
		


//South #######################################################################################################################
		JPanel panelSouth = new JPanel();
		panel.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setBorder(new EmptyBorder(10, 10, 0, 10));
		
		buttonMax1 = new JToggleButton("Max1");
		buttonMax1.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				n=0;
				if(buttonMax1.isEnabled()) {
					for (int i = 0; i < saveDices.length; i++) {
						if(saveDices[i] == 1) n++;
					}
					auxMax1 = 1*n;
					total += auxMax1;
					System.out.println("Max1 -> " + auxMax1 + " points");
					guardaInfo[times] = "Max1 -> " + auxMax1 + " points";
					stop1 = true;
					countVars();
					buttonMax1.setVisible(false);
					verifyBonus();
					buttonsDisabled();
					verifyFinished();
					ButtonsDicesDescelect();
					ButtonsDicesDisabled();
				}
			}
		});
		
		buttonMax2 = new JToggleButton("Max2");
		buttonMax2.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				n=0;
				if(buttonMax2.isEnabled()) {
					for (int i = 0; i < saveDices.length; i++) {
						if(saveDices[i] == 2) n++;
					}
					auxMax2 = 2*n;
					total += auxMax2;
					System.out.println("Max2 -> " + auxMax2 + " points");
					guardaInfo[times] = ("Max2 -> " + auxMax2 + " points");
					stop2 = true;
					countVars();
					buttonMax2.setVisible(false);
					verifyBonus();
					buttonsDisabled();
					verifyFinished();
					ButtonsDicesDescelect();
					ButtonsDicesDisabled();
				}
			}
		});
		
		buttonMax3 = new JToggleButton("Max3");
		buttonMax3.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				n=0;
				if(buttonMax3.isEnabled()) {
					for (int i = 0; i < saveDices.length; i++) {
						if(saveDices[i] == 3) n++;
					}
					auxMax3 = 3*n;
					total += auxMax3;
					System.out.println("Max3 -> " + auxMax3 + " points");
					guardaInfo[times] = ("Max3 -> " + auxMax3 + " points");
					stop3 = true;
					countVars();
					buttonMax3.setVisible(false);
					verifyBonus();
					buttonsDisabled();
					verifyFinished();
					ButtonsDicesDescelect();
					ButtonsDicesDisabled();
				}
			}
		});
		
		buttonMax4 = new JToggleButton("Max4");
		buttonMax4.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				n=0;
				if(buttonMax4.isEnabled()) {
					for (int i = 0; i < saveDices.length; i++) {
						if(saveDices[i] == 4) n++;
					}
					auxMax4 = 4*n;
					total += auxMax4;
					System.out.println("Max4 -> " + auxMax4 + " points");
					guardaInfo[times] = ("Max4 -> " + auxMax4 + " points");
					stop4 = true;
					countVars();
					buttonMax4.setVisible(false);
					verifyBonus();
					buttonsDisabled();
					verifyFinished();
					ButtonsDicesDescelect();
					ButtonsDicesDisabled();
				}
			}
		});
		
		buttonMax5 = new JToggleButton("Max5");
		buttonMax5.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				n=0;
				if(buttonMax5.isEnabled()) {
						for (int i = 0; i < saveDices.length; i++) {
							if(saveDices[i] == 5) n++;
						}
						auxMax5 = 5*n;
						total += auxMax5;
						System.out.println("Max5 -> " + auxMax5 + " points");
						guardaInfo[times] = ("Max5 -> " + auxMax5 + " points");
						stop5 = true;
						countVars();
						buttonMax5.setVisible(false);
						verifyBonus();
						buttonsDisabled();
						verifyFinished();
						ButtonsDicesDescelect();
						ButtonsDicesDisabled();
				}
			}
		});
		
		buttonMax6 = new JToggleButton("Max6");
		buttonMax6.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				n=0;
				if(buttonMax6.isEnabled()) {
					for (int i = 0; i < saveDices.length; i++) {
						if(saveDices[i] == 6) n++;
					}
					auxMax6 = 6*n;
					total += auxMax6;
					System.out.println("Max6 -> " + auxMax6 + " points");
					guardaInfo[times] = ("Max6 -> " + auxMax6 + " points");
					stop6 = true;
					countVars();
					buttonMax6.setVisible(false);
					verifyBonus();
					buttonsDisabled();
					verifyFinished();
					ButtonsDicesDescelect();
					ButtonsDicesDisabled();
				}
			}
		});
		
		buttonSame3 = new JToggleButton("3 iguais");
		buttonSame3.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				verifySames();
				if(buttonSame3.isEnabled()) {
					if (same3==true) {
						total += 15;
						System.out.println("Same3 -> 15 points");
						guardaInfo[times] = ("Same3 -> 15 points");
						saveAchiev[0]=true;
						checkAchiev();
					} else {
						System.out.println("Same3 -> 0 points");
						guardaInfo[times] = ("Same3 -> 0 points");
					}
					countVars();
					buttonSame3.setVisible(false);
					buttonsDisabled();
					verifyFinished();
					ButtonsDicesDescelect();
					ButtonsDicesDisabled();
				}
			}
		});
		
		buttonSame4 = new JToggleButton("4 iguais");
		buttonSame4.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				verifySames();
				if(buttonSame4.isEnabled()) {
					if (same4==true) {
						total += 25;
						System.out.println("Same4 -> 25 points");
						guardaInfo[times] = ("Same4 -> 25 points");
						saveAchiev[1]=true;
						checkAchiev();
					} else {
						System.out.println("Same4 -> 0 points");
						guardaInfo[times] = ("Same4 -> 0 points");
					}
					countVars();
					buttonSame4.setVisible(false);
					buttonsDisabled();
					verifyFinished();
					ButtonsDicesDescelect();
					ButtonsDicesDisabled();
				}
			}
		});
		
		buttonSame5 = new JToggleButton("5 iguais");
		buttonSame5.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				verFull();
				if(buttonSame5.isEnabled()) {
					if(full==true) {
						total += 50;
						System.out.println("Same5 -> 50 points");
						guardaInfo[times] = ("Same5 -> 50 points");
						saveAchiev[2]=true;
						checkAchiev();
					} else {
						System.out.println("Same5 -> 0 points");
						guardaInfo[times] = ("Same5 -> 0 points");
					}
					countVars();
					buttonSame5.setVisible(false);
					buttonsDisabled();
					verifyFinished();
					ButtonsDicesDescelect();
					ButtonsDicesDisabled();
				}
			}
		});

		buttonSequence4 = new JToggleButton("Sequence of 4");
		buttonSequence4.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				verSequencia();
				if(buttonSequence4.isEnabled()) {
					if (seq4==true) {
						total += 30;
						System.out.println("Seq4 -> 30 points");
						guardaInfo[times] = ("Seq4 -> 30 points");
						saveAchiev[3]=true;
						checkAchiev();
					} else {
						System.out.println("Seq4 -> 0 points");
						guardaInfo[times] = ("Seq4 -> 0 points");
					}
							countVars();
					buttonSequence4.setVisible(false);
					buttonsDisabled();
					verifyFinished();
					ButtonsDicesDescelect();
					ButtonsDicesDisabled();
				}
			}
		});
		
		buttonSequence5 = new JToggleButton("Sequence of 5");
		buttonSequence5.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				verSequencia();
				if(buttonSequence5.isEnabled()) {
					if (seq5==true) {
						total += 40;
						System.out.println("Seq5 -> 40 points");
						guardaInfo[times] = ("Seq5 -> 40 points");
						saveAchiev[4]=true;
						checkAchiev();
					} else {
						System.out.println("Seq5 -> 0 points");
						guardaInfo[times] = ("Seq5 -> 0 points");
					}
					countVars();
					buttonSequence5.setVisible(false);
					buttonsDisabled();
					verifyFinished();
					ButtonsDicesDescelect();
					ButtonsDicesDisabled();
				}
			}
		});
		
		buttonFullen = new JToggleButton("Fullen");
		buttonFullen.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				verifyFullen();
				if(buttonFullen.isEnabled()) {
					if (fullen==true) {
						total += 25;
						System.out.println("Fullen -> 25 points");
						guardaInfo[times] = ("Fullen -> 25 points");
						saveAchiev[5]=true;
						checkAchiev();
					} else {
						System.out.println("Fullen -> 0 points");
						guardaInfo[times] = ("Fullen -> 0 points");
					}
					countVars();
					buttonFullen.setVisible(false);
					buttonsDisabled();
					verifyFinished();
					ButtonsDicesDescelect();
					ButtonsDicesDisabled();
				}
			}
		});
		
		buttonChance = new JToggleButton("Chance"); //Soma todos os dados 
		buttonChance.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				if(buttonChance.isEnabled()) {
					for (int i = 0; i < saveDices.length; i++) {
						chance += saveDices[i];
					}
						total += chance;
						System.out.println("Extra -> " + chance + " points");
						guardaInfo[times] = ("Extra -> " + chance + " points");
					countVars();
					buttonChance.setVisible(false);
					buttonsDisabled();
					verifyFinished();
					ButtonsDicesDescelect();
					ButtonsDicesDisabled();
				}
			}
		});
		
		panelSouth.add(buttonMax1);
		panelSouth.add(buttonMax2);
		panelSouth.add(buttonMax3);
		panelSouth.add(buttonMax4);
		panelSouth.add(buttonMax5);
		panelSouth.add(buttonMax6);
		panelSouth.add(buttonChance);
		panelSouth.add(buttonSame3);
		panelSouth.add(buttonSame4);
		panelSouth.add(buttonSame5);
		panelSouth.add(buttonSequence4);
		panelSouth.add(buttonSequence5);
		panelSouth.add(buttonFullen);
		
		buttonsDisabled();
	}
	
	
	public void buttonsDisabled() {
		buttonMax1.setEnabled(false);
		buttonMax2.setEnabled(false);
		buttonMax3.setEnabled(false);
		buttonMax4.setEnabled(false);
		buttonMax5.setEnabled(false);
		buttonMax6.setEnabled(false);
		buttonChance.setEnabled(false);
		buttonSame3.setEnabled(false);
		buttonSame4.setEnabled(false);
		buttonSame5.setEnabled(false);
		buttonSequence4.setEnabled(false);
		buttonSequence5.setEnabled(false);
		buttonFullen.setEnabled(false);
	}
	
	public void ButtonsDicesDescelect(){
		buttonDice1.setSelected(false);
		buttonDice2.setSelected(false);
		buttonDice3.setSelected(false);
		buttonDice4.setSelected(false);
		buttonDice5.setSelected(false);
	}
	
	public void ButtonsDicesDisabled() {
		buttonDice1.setEnabled(false);
		buttonDice2.setEnabled(false);
		buttonDice3.setEnabled(false);
		buttonDice4.setEnabled(false);
		buttonDice5.setEnabled(false);
	}
	
	public void ButtonsDescelect(){
		buttonMax1.setSelected(false);
	}
	
	public void countVars() {
		t=0;
		times++;
		buttonRoll5.setText(t + " of 3");
		labelTimes.setText("Times Roled: "+ times);
		labelResult.setText("TOTAL:  " + total);
		buttonRoll2.setEnabled(true);
		labelMaxs.setText("<html><body><br>Max1 = "+auxMax1+ " &emsp;&emsp;&emsp; Max4 = "+auxMax4+
				   "<br>Max2 = "+auxMax2+" &emsp;&emsp;&emsp; Max5 = "+auxMax5+
				   "<br>Max3 = "+auxMax3+" &emsp;&emsp;&emsp; Max6 = "+auxMax6+
				   "&emsp;&emsp; Bonus not active</body></html>");
	}
	
	public void saveDicesOfRound() {
		for (int i = 0; i < saveDices.length; i++) {
			saveDices[i] = dice.array[i];
		}
	}

	public void verFull() {
		for (int i = 0; i < saveDices.length-1; i++) {
			if(saveDices[i] != saveDices[i+1]) full=false;
		}
	}
	
	public void verSequencia() {
		count = 0;
		seq4=false;
		seq5=false;
		
		int[] arrayAux = new int[saveDices.length];
		System.arraycopy(saveDices, 0, arrayAux, 0, saveDices.length);
		Arrays.sort(arrayAux);
		
		for (int i = 0; i < arrayAux.length-1; i++) {
			if(arrayAux[i] <= arrayAux[i+1]){
				if(arrayAux[i] == arrayAux[i+1]-1) {
					count +=1;
				}
			} else {
				count = 0;
			}
		}
		if(count >= 4) seq5 = true;
		if(count >= 3) seq4 = true;
	}
	
	public void verifySames() {
		int[] arrayAux = new int[saveDices.length];
		System.arraycopy(saveDices, 0, arrayAux, 0, saveDices.length);
		Arrays.sort(arrayAux);
		same=0;
		for (int i = 0; i < arrayAux.length-1; i++) {
			if(arrayAux[i]==arrayAux[i+1]) {
				same++;
				if(same>=2) same3=true;
				if(same>=3) same4=true;
			}else same = 0;
		}
	}
	
	public void verifyFullen() {
		int[] arrayAux = new int[saveDices.length];
		System.arraycopy(saveDices, 0, arrayAux, 0, saveDices.length);
		Arrays.sort(arrayAux);
		if(	(arrayAux[0]==arrayAux[1] && arrayAux[2]==arrayAux[3] && arrayAux[3]==arrayAux[4]) || 
			(arrayAux[0]==arrayAux[1] && arrayAux[1]==arrayAux[2] && arrayAux[3]==arrayAux[4])) {
				fullen=true;
		}
	}
	
	public void verifyBonus() {		
		somarMaxs = auxMax1 + auxMax2 + auxMax3 + auxMax4 +auxMax5 +auxMax6;
		if(stop1 == true && stop2 == true && stop3 == true && stop4 == true && stop5 == true && stop6 == true) {
			if(somarMaxs >= 63) {
				total += 35;
				labelResult.setText("TOTAL:  " + total);
				labelMaxs.setText("<html><body><br>Max1 = "+auxMax1+ " &emsp;&emsp;&emsp; Max4 = "+auxMax4+
						   "<br>Max2 = "+auxMax2+" &emsp;&emsp;&emsp;   Max5 = "+auxMax5+
						   "<br>Max3 = "+auxMax3+" &emsp;&emsp;&emsp;   Max6 = "+auxMax6+
						   "&emsp;&emsp; Bonus active</body></html>");
				System.out.println("Bonus -> 35 points");
			}
		}
	}
	
	public void sortArrayDices() {
		int x, y, aux;
		for(x = 0; x < saveDices.length; x++) {
			for(y = 0; y < saveDices.length; y++) {
				if(saveDices[y] > saveDices[x]) {
					aux = saveDices[y];
					saveDices[y] = saveDices[x];
					saveDices[x] = aux;
				}
			}
		}
	}
	
	public void verifyFinished() {		
		if(times >= maxTimes) {
			if(somarMaxs < 63) {
				System.out.println("Bonus -> 0 points");
			}

			Login._numPlay++;
			checkAchiev();
			updateAchievement();
			
			int _scoreBefore = Integer.parseInt(Login._score);
			if(total>_scoreBefore) {
				if(total > Login.bestRecord) {
					JOptionPane.showMessageDialog(null," Jogo terminou, fez "+ total + " pontos!!\n\n",
							 "NEW RECORD "+Login._user, JOptionPane.ERROR_MESSAGE, firstPlace);
				}else {
					System.out.println("_scoreBefore: " + _scoreBefore);
					System.out.println("recordScore(total): " + total);
					refreshRecord(total);
					JOptionPane.showMessageDialog(null," Jogo terminou, fez "+ total + " pontos!!\n\n",
							 "NEW PERSONAL RECORD "+Login._user, JOptionPane.ERROR_MESSAGE, recordPessoal);
					}
			}else {
			JOptionPane.showMessageDialog(null," Jogo terminou, fez "+ total + " pontos!!\n\n",
					 "END OF GAME "+Login._user, JOptionPane.ERROR_MESSAGE);
			}
			
			System.exit(1);
		}
	}
	
	private void refreshRecord(int recordScore) {
		 try {
      		  Class.forName("com.mysql.jdbc.Driver");
      		  Connection con = DriverManager.getConnection("jdbc:mysql://www.renatovalente5.site/u422723836_db","u422723836_user","Kebab.99"); //("jdbc:mysql:localhost:3306/test","root","");
      		  String query = "UPDATE testusers SET score='"+recordScore+"' where user='"+Login._user+"'";
      		  PreparedStatement ptmt = con.prepareStatement(query);
      		  ptmt.executeUpdate();
      		  System.out.println("Login._user: " + Login._user);
      		  System.out.println("Login._score: " + recordScore);
      		  System.out.println("****Atualizado****");
      		  con.close();
      		  
      	  }catch(Exception e){
      		  System.out.println(e);
      	  }	
	}
	
	private void updateAchievement() {
		String text = String.valueOf(Login.arrayAchiev);
		System.out.println("text: " + text);
		 try {
     		  Class.forName("com.mysql.jdbc.Driver");
     		  Connection con = DriverManager.getConnection("jdbc:mysql://www.renatovalente5.site/u422723836_db","u422723836_user","Kebab.99");
     		  String query = "UPDATE testusers SET achievements='"+text+"', numPlay='"+Login._numPlay+"' where user='"+Login._user+"'";
     		  PreparedStatement ptmt = con.prepareStatement(query);
     		  ptmt.executeUpdate();
     		  System.out.println("****Atualizado text****");
     		  con.close();
     		  
     	  }catch(Exception e){
     		  System.out.println(e);
     	  }
	}
	
	private void checkAchiev() {
		if(Login._numPlay >= 10 && Login.arrayAchiev[0]!='1') {
			Login.arrayAchiev[0]='1';
			JOptionPane.showMessageDialog(null,"Fazer 10 Jogos!!",
					 "NEW ACHIEVEMENT ", JOptionPane.INFORMATION_MESSAGE, medals0);
		}
		if(Login._numPlay >= 30 && Login.arrayAchiev[1]!='1') {
			Login.arrayAchiev[1]='1';
			JOptionPane.showMessageDialog(null,"Fazer 30 Jogos!!",
					 "NEW ACHIEVEMENT ", JOptionPane.INFORMATION_MESSAGE, medals1);
		}
		if(Login._numPlay >= 100 && Login.arrayAchiev[2]!='1') {
			Login.arrayAchiev[2]='1';
			JOptionPane.showMessageDialog(null,"Fazer 100 Jogos!!",
					 "NEW ACHIEVEMENT ", JOptionPane.INFORMATION_MESSAGE, medals2);
		}
		
		if(saveAchiev[0]==true && saveAchiev[1]==true && saveAchiev[2]==true && Login.arrayAchiev[3]!='1') {
			Login.arrayAchiev[3]='1';
			JOptionPane.showMessageDialog(null,"Fazer 3iguais, 4iguais e 5iguais no mesmo jogo!!",
					 "NEW ACHIEVEMENT ", JOptionPane.INFORMATION_MESSAGE, medals0);
		}
		if(saveAchiev[3]==true && saveAchiev[4]==true  && Login.arrayAchiev[4]!='1') {
			Login.arrayAchiev[4]='1';
			JOptionPane.showMessageDialog(null,"Fazer Seq4 e Seq5 no mesmo jogo!!",
					 "NEW ACHIEVEMENT ", JOptionPane.INFORMATION_MESSAGE, medals1);
		}
		if(saveAchiev[0]==true && saveAchiev[1]==true && saveAchiev[2]==true && saveAchiev[3]==true && saveAchiev[4]==true && Login.arrayAchiev[5]!='1') {
			Login.arrayAchiev[5]='1';
			JOptionPane.showMessageDialog(null,"Fazer 3iguais, 4iguais, 5 iguais, Seq4 e Seq5 no mesmo jogo!!",
					 "NEW ACHIEVEMENT ", JOptionPane.INFORMATION_MESSAGE, medals2);
		}
		
		if(somarMaxs>=63 && Login.arrayAchiev[6]!='1') {
			Login.arrayAchiev[6]='1';
			JOptionPane.showMessageDialog(null,"Ativar o bonus 1 vez!!",
					 "NEW ACHIEVEMENT ", JOptionPane.INFORMATION_MESSAGE, medals0);
		}
		if(somarMaxs>=80 && Login.arrayAchiev[7]!='1') {
			Login.arrayAchiev[7]='1';
			JOptionPane.showMessageDialog(null,"Ativar o bonus com mais de 80 Max's points!!",
					 "NEW ACHIEVEMENT ", JOptionPane.INFORMATION_MESSAGE, medals1);
		}
		if(somarMaxs>=85 && Login.arrayAchiev[8]!='1') {
			Login.arrayAchiev[8]='1';
			JOptionPane.showMessageDialog(null,"Ativar o bonus com mais de 85 Max's pointss!!",
					 "NEW ACHIEVEMENT ", JOptionPane.INFORMATION_MESSAGE, medals2);
		}
		
	}
}
