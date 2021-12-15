package com.iesb.api_loja.bo;

import java.util.ArrayList;
import java.util.List;

public class VerificaLogin {
	
	public static final VerificaLogin INSTANCE = new VerificaLogin();
	
	private List<String> usuarios = new ArrayList<String>();
	
	private VerificaLogin() {
		
	}
	public void registraLogin(String login) {
		usuarios.add(login);
	}
	
	public boolean verificaUsuarioLogado(String login) {
		for (String user : usuarios) {
			if(user.equals(login)) {
				return true;
			}
		}
		return false;
	}

}
