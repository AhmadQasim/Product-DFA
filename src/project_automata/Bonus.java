package project_automata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * This class performs the following bonus tasks
 * 1. Identify whether the language accepted by the DFA is infinite
 * 2. In case of infinite language, write strings upto length 10 in a file
 * 3. In case of finite language, write all possible strings in a file*/

public class Bonus {

	public boolean lang_infinite;
	public Dfa dfa;
	public ArrayList<String> lang;
	
	//constructor: init with product dfa
	public Bonus(Dfa dfa){
		this.dfa = dfa;
	}
	
	//method to find if language defined by DFA is infinite
	public void is_infinite(){
		
		Map<String, String> transitions = new HashMap<String, String>();
		transitions = dfa.getmTransitions();
		
		String curr_state = dfa.get_start_state();
		String next_state;
		String[] visited = new String[dfa.getmNumOfStates()];
		for(int i=0; i<visited.length; i++)
			visited[i] = "empty";
		
		lang_infinite = false;
		int top = 0;
		
		while(true){
			next_state = transitions.get(curr_state+"+0");
			
			//check if next state is visited previously
			for(int i=0; i<visited.length; i++){
				if(next_state.equals(visited[i])){
					lang_infinite = true;
					break;
				}
			}
			if(lang_infinite)
				break;
			else {
				top++;
				visited[top] = next_state;
			}
				
			if (next_state.equals(dfa.get_final_state()))
				break;
			else
				curr_state = next_state;
		}
	}

	//method to generate language and save in file
	public void lang_generator() throws IOException{
		lang = new ArrayList<String>();
		String seq = "";
		String curr_state = dfa.get_start_state();
		String[] alphabet = dfa.getmLanguage();
		
		if(lang_infinite) {
			generate_infinite_lang(alphabet, seq, 0);
			write_lang();
		}
		else {
			generate_finite_lang(curr_state, alphabet, seq);
			write_lang();
		}
	}
	
	//finite language producer
	public void generate_finite_lang(String curr_state, String[] alphabet, String seq) {
		
		if(curr_state.equals(dfa.get_final_state())){
			System.out.println("Accepted String: " + seq);
			lang.add(seq);
		}
		else {
			for(int i=0; i<alphabet.length; i++){
				String next_state = dfa.getmTransitions().get(curr_state+"+"+alphabet[i]);
				generate_finite_lang(next_state, alphabet, seq+alphabet[i]);
			}
		}
	}
	
	//infinite language generator upto strings of length 10
	public void generate_infinite_lang(String[] alphabet, String seq, int loop_num) {
		if(loop_num > 10)
			return;
		else {
			for(int i=0; i<alphabet.length; i++) {
				seq = seq + alphabet[i];
				is_string_accepted(seq);
				loop_num++;
				generate_infinite_lang(alphabet, seq, loop_num);
			}
		}
	}
	
	//check if string is accepted by dfa
	public void is_string_accepted(String seq) {
		String curr_state = dfa.get_start_state();
		HashMap<String, String> transitions = dfa.getmTransitions();
		for(int i=0; i<seq.length(); i++) 
			curr_state = transitions.get(curr_state + "+" + seq.charAt(i));
		
		if(curr_state.equals(dfa.get_final_state())){
			System.out.println("Accepted String: " + seq);
			lang.add(seq);
		}
	}
	
	
	//method to write language to a file	
	public void write_lang() throws IOException {
		File file = new File("dfa_language.txt");
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		for(int i=0; i<lang.size(); i++)
			fw.append(lang.get(i)+"\t");
		fw.flush();
		fw.close();
	}
}
