package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.buf.B2CConverter;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Usuario;

public class Login implements Acao{

	public String executa(HttpServletRequest request, HttpServletResponse response) {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		System.out.println("Bem vindo " + login + ", login efetuado com sucesso.");
		Banco banco = new Banco();
		Usuario usuario = banco.existeUsario(login,senha);
		
		if(usuario != null) {
			System.out.println("Usuário existe");
			//Permite que o Login se mantenha durante toda a navegação
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuarioLogado", usuario);
			return "redirect:entrada?acao=ListaEmpresas";
		}else {
			return "redirect:entrada?acao=LoginForm";
		}
		
	}

}
