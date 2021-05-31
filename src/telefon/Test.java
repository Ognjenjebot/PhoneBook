package telefon;

import java.awt.BorderLayout;
import java.awt.Frame;

public class Test extends Frame{

	public Test() {
		setTitle("test");
		setBounds(200, 200, 500, 500);
		add(new Tastatura(), BorderLayout.CENTER);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		 
		new Test();
	}

}
