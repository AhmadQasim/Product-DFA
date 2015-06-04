package project_automata;

import javax.swing.JFrame;

public class ui_dfa extends JFrame{
	private static final long serialVersionUID = 1L;
	public static final int res_width=1000;
	public static final int res_height=800;
	public void initDraw(){
		graphics_panel gp = new graphics_panel();
		add(gp);
		setTitle("DFA");
        setSize(res_width, res_height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
