package project_automata;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;

public class structure_file {
	String name_file;
	public static String[][] transitions;
	public static HashMap<Integer, String> hm;
	public static int counter_1=0;
	public static Color[] color;
	public structure_file(String filename){
		name_file=filename;
	}
	@SuppressWarnings("resource")
	public void read_file() throws IOException{
		File file = new File(name_file);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		int counter_2;hm =new HashMap<Integer,String>();
		String buffer,token;br.readLine();
		transitions = new String[20][20];
		while((buffer=br.readLine()) != null){counter_1++;
			StringTokenizer st = new StringTokenizer(buffer, "\t");token=st.nextToken();
			if (token==">"||token=="*"){}
			hm.put(counter_1,token);counter_2=1;
			while(st.hasMoreTokens()){
				token=st.nextToken();
				if (token==">"||token=="*"){}
				transitions[counter_1][counter_2]=token;
				counter_2++;
			}
		}
		color = new Color[counter_1];
		Random r=new Random();
		for (int i =0;i<counter_1;i++){
			color[i] = new Color(r.nextInt(256));
		}
	}
}
