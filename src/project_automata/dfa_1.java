package project_automata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class dfa_1 {
	String name_file;int counter1=0;
	HashMap<String,String> hash = new HashMap<String,String>();
	public String[][] trans;
	public String[] states;
	public dfa_1 (String name){
		name_file=name;
	}
	@SuppressWarnings("resource")
	public void read_file() throws IOException{
		File file = new File(name_file);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		int counter2;
		String buffer,token;br.readLine();
		trans = new String[20][20];
		states = new String[20];
		while((buffer=br.readLine()) != null){
			StringTokenizer st = new StringTokenizer(buffer, "\t");counter2=0;states[counter1]=st.nextToken();
			while(st.hasMoreTokens()){
				token=st.nextToken();
				if (token==">"||token=="*"){}
				trans[counter1][counter2]=token;
				counter2++;
			}
			counter1++;
		}
	}
	public int num_states(){
		return counter1;
	}
	public String[] get_states(){
		return states;
	}
	public void organize_data(){
		for (int i =0;i<num_states();i++){for (int j = 0;j<num_states();j++){if (!trans[i][j].equals("Φ")){hash.put(states[i]+"+"+trans[i][j], states[j]);}}}
	}
}
