package project_automata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ProductDfa {
	private Dfa mResultantDfa;
	String[] productStates;
	public HashMap<String, Integer> hm_product;
	String[][] trans_product;
	int counter;
	
	ProductDfa(Dfa dfa1,Dfa dfa2){
		mResultantDfa= new Dfa();
		//mResultantDfa.setmLanguage(dfa1.getmLanguage());
		CalculateProduct(dfa1, dfa2);
	}
	
	//Getters and Setters
	public Dfa getmResultantDfa() {
		return mResultantDfa;
	}
	public void setmResultantDfa(Dfa mResultantDfa) {
		this.mResultantDfa = mResultantDfa;
	}
	
	private void CalculateProduct(Dfa dfa1,Dfa dfa2){
		mResultantDfa.setmNumOfStates(dfa1.getmNumOfStates()*dfa2.getmNumOfStates());
		productStates = new String[mResultantDfa.getmNumOfStates()];
		hm_product = new HashMap<String, Integer>();
		//Making resultant states
		counter = 0;
		for(int i=0;i<dfa1.getmNumOfStates();i++){
			for(int j=0;j<dfa2.getmNumOfStates();j++){
				productStates[counter] = dfa1.getmStates()[i]+","+dfa2.getmStates()[j];
				hm_product.put(productStates[counter],i*dfa1.getmNumOfStates()+j);
				counter++;
			}
		}
		mResultantDfa.setmStates(productStates);
		trans_product = new String[counter][counter];
		HashMap<String,String> productTransition = new HashMap<String,String>();
		for(int i=0;i < mResultantDfa.getmNumOfStates();i++){
			String[] states = mResultantDfa.getmStates()[i].split(",");
			for(int j=0;j<mResultantDfa.getmLanguage().length;j++){
				String temp1 = states[0]+"+"+mResultantDfa.getmLanguage()[j];
				String temp2 = states[1]+"+"+mResultantDfa.getmLanguage()[j];
 				String resultTransition = dfa1.getmTransitions().get(temp1)
						+","+
						dfa2.getmTransitions()
						.get(temp2);
				
 				String temp = mResultantDfa.getmStates()[i]+"+"+mResultantDfa.getmLanguage()[j];
 				trans_product[i][hm_product.get(resultTransition)]=mResultantDfa.getmLanguage()[j];
				productTransition.put(temp,resultTransition);
			}
		}
		mResultantDfa.setmTransitions(productTransition);
	}
	public void write_DFA() throws IOException{
		 File file = new File("trans_table.txt");
		 file.createNewFile();
		 FileWriter fw = new FileWriter(file);
		 fw.write('\n');
		for (int i =0;i<counter;i++){
			fw.append(productStates[i]);
			for (int j =0;j<counter;j++){
				if (trans_product[i][j]==null)fw.append("\tΦ");else fw.append("\t"+trans_product[i][j]);
		}fw.append('\n');}
		 fw.flush();fw.close();
	}
}
