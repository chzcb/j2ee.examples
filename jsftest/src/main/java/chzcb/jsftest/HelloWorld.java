package chzcb.jsftest;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="hello")
@SessionScoped
public class HelloWorld implements Serializable {
	private static final long serialVersionUID = -5845108555234275222L;
	private String name = "World!";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
