package org.latinoware.geodojo.beans;

import javax.enterprise.inject.Model;
import javax.faces.component.UIOutput;

/**
 * Bean para exemplificar o modelo orientado a eventos do JSF
 * 
 * @author ranophoenix
 *
 */
@Model
public class HelloJSF {
	private String nome;
	private UIOutput output;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	public UIOutput getOutput() {
		return output;
	}

	public void setOutput(UIOutput output) {
		this.output = output;
	}

	public void sayHello() {
		output.setValue("Olá " + nome + "!!!");
	} 
	
	
}
