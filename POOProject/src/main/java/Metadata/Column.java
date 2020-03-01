package Metadata;

import java.util.ArrayList;
import java.util.List;

public class Column {
	private List<Value> values;
	private String name;
	private String type;
	private List<String> rules;
	private String ruleAnonymize;
	
	public Column(String name) {
		this.name = name;
		values = new ArrayList<>();
		rules = new ArrayList<>();
	}
	public String getRuleAnonymize() {
		return this.ruleAnonymize;
	}
	public void setRuleAnonymize(String rule) {
		this.ruleAnonymize = rule;
	}
	public List<Value> getValues(){
		return this.values;
	}
	public String getName() {
		return this.name;
	}
	public String getType() {
		return this.type;
	}
	public List<String> getRules(){
		return this.rules;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setRules(List<String> rules) {
		this.rules = rules;
	}
}
