package project_automata;

import java.util.HashMap;

public class Dfa {
	private int mNumOfStates;
	private String[] mStates;
	private String[] mLanguage;
	private HashMap<String,String> mTransitions;
	private String final_state;
	private String start_state;
	
	Dfa(){
		this.mLanguage=new String[2];
		this.mLanguage[0]="0";
		this.mLanguage[1]="1";
		this.start_state = "1,1";
		this.final_state = "2,2";
	}
	
	public String[] getmLanguage() {
		return mLanguage;
	}

	public void setmLanguage(String[] mLanguage) {
		this.mLanguage = mLanguage;
	}

	Dfa(int numOfStates,String[] states,HashMap<String,String> transitions){
		this.mNumOfStates=numOfStates;
		this.mStates = states;
		this.mTransitions = transitions;
		this.mLanguage=new String[2];
		this.mLanguage[0]="0";
		this.mLanguage[1]="1";
	}
	
	public int getmNumOfStates() {
		return mNumOfStates;
	}
	public void setmNumOfStates(int mNumOfStates) {
		this.mNumOfStates = mNumOfStates;
	}
	public String[] getmStates() {
		return mStates;
	}
	public void setmStates(String[] mStates) {
		this.mStates = mStates;
	}
	public HashMap<String,String> getmTransitions() {
		return mTransitions;
	}
	public void setmTransitions(
			HashMap<String,String> mTransitions) {
		this.mTransitions = mTransitions;
	}
	
	
	
	public void display_Dfa(){
		for(int j=0;j<this.mNumOfStates;j++){
			for(int i=0;i < this.mLanguage.length;i++){
				String temp = mStates[j]+ "+" + mLanguage[i];
				System.out.println(mStates[j]+
						"  -"+this.mLanguage[i]+"->  "+ this.getmTransitions().get(temp));
			}
		}
	}

	public String get_final_state() {
		return final_state;
	}

	public void set_final_state(String final_state) {
		this.final_state = final_state;
	}

	public String get_start_state() {
		return start_state;
	}

	public void set_start_state(String start_state) {
		this.start_state = start_state;
	}
}
