package telefon;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tastatura extends Panel implements Runnable{

	private Thread thread;
	private Label label = new Label("Pocetak -> ");
	private Button buttons[] = new Button[12];
	private Button lastPressed;
	private boolean works = false;  //mozda treba
	private Panel keyboard = new Panel();
	private int count;
	
	public Tastatura() {
		setLayout(new BorderLayout());
		keyboard.setLayout(new GridLayout(4, 3, 1, 1));
		fillButtons();
		add(label, BorderLayout.NORTH);
		add(keyboard, BorderLayout.CENTER);
	}
	
	
	private void fillButtons() {
		buttons[0] = new Button("");
		buttons[1] = new Button("ABC");
		buttons[2] = new Button("DEF");
		buttons[3] = new Button("GHI");
		buttons[4] = new Button("JKL");
		buttons[5] = new Button("MNO");
		buttons[6] = new Button("PQRS");
		buttons[7] = new Button("TUV");
		buttons[8] = new Button("WXYZ");
		buttons[9] = new Button("");
		buttons[10] = new Button("_");
		buttons[11] = new Button("");
		
		ButtonListener l = new ButtonListener();
		
		for(Button b : buttons) {
			//b.addActionListener(new ButtonListener());
			b.addActionListener(l);
			keyboard.add(b);
		}		
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(thread != null) {
				if(e.getSource() != lastPressed) {
					thread.interrupt();
					thread = null;
					count = 0;
					test = ((Button)e.getSource()).getLabel();
					label.setText(label.getText() + test.charAt(count % (test.length())));
					lastPressed = (Button)e.getSource();
					(thread = new Thread(Tastatura.this)).start();
					//ako je drugo dugme pritisnuto dodaje se char novog dugmeta na kraj
				}else {				
					thread.interrupt();
					thread = null;
					//promeni poslednji char, lastPresed(count), jer je lastPressed i trenutno dugme			
					StringBuilder labela = new StringBuilder(label.getText());
					labela.setCharAt(labela.length() - 1, test.charAt(count % (test.length())));
					label.setText(labela.toString());			
					(thread = new Thread(Tastatura.this)).start();
				}
			}else {
				//dodaj na kraj char
				//ako je null, svakako se uzima tekst !novog dugmeta!
				
				test = ((Button)e.getSource()).getLabel();				
				count = 0;
				label.setText(label.getText() + test.charAt(count % (test.length())));
				lastPressed = (Button) e.getSource();
				(thread = new Thread(Tastatura.this)).start();
			}
		}

	}
	//Uvek se zadaje novi string koji se lepi na labelu, u smislu
	private String test;

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				count++;				
				Thread.sleep(1000);
				thread.interrupt();
				thread = null; 
			}
		}catch(InterruptedException e) {}		
	}
	

}
