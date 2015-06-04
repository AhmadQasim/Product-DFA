package project_automata;

import java.io.IOException;
import java.util.HashMap;

public class start_sim {

	public static void main(String[] args) throws IOException {
		dfa_1 a = new dfa_1("trans1.txt");a.read_file();
		dfa_1 b = new dfa_1("trans2.txt");b.read_file();
		a.organize_data();b.organize_data();
		Dfa dfa1 = new Dfa(a.num_states(), a.states, a.hash);
		Dfa dfa2 = new Dfa(b.num_states(), b.states, b.hash);
		ProductDfa productDfa = new ProductDfa(dfa1, dfa2);
		productDfa.getmResultantDfa().display_Dfa();
		productDfa.write_DFA();
		structure_file obj = new structure_file("trans_table.txt");
		obj.read_file();
		ui_dfa ui = new ui_dfa();
		ui.initDraw();
		ui.setVisible(true);
		
		Dfa dfa = productDfa.getmResultantDfa();
		
		//This is a template for finite language DFA, remove comments to test
		
		/*dfa.set_start_state("A");
		dfa.set_final_state("C");
		dfa.setmNumOfStates(3);
		String[] temp = new String[3];
		temp[0] = "A";
		temp[1] = "B";
		temp[2] = "C";
		dfa.setmStates(temp);
		HashMap<String, String> temp_map = new HashMap<String, String>();
		temp_map.put("A+0", "B");
		temp_map.put("A+1", "B");
		temp_map.put("B+0", "C");
		temp_map.put("B+1", "C");
		temp_map.put("C+0", "-1");
		temp_map.put("C+1", "-1");
		dfa.setmTransitions(temp_map);*/
		
		//bonus tasks
		Bonus bon = new Bonus(dfa);
		bon.is_infinite();
		System.out.println("Lang infinite: " + bon.lang_infinite);
		bon.lang_generator();
	}
}