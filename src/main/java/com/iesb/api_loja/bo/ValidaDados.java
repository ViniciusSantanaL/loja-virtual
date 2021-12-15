package com.iesb.api_loja.bo;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.iesb.api_loja.model.Cartao;
import com.iesb.api_loja.model.Cliente;
import com.iesb.api_loja.model.Endereco;
import com.iesb.api_loja.model.Produto;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.safeguard.check.SafeguardCheck;
import br.com.safeguard.interfaces.Check;
import br.com.safeguard.types.ParametroTipo;

public class ValidaDados {

	public static final ValidaDados INSTANCE = new ValidaDados();

	private ValidaDados() {

	}

	public List<String> messages = new ArrayList<String>();

	private boolean validaCPF(String cpf) {
		CPFValidator cpfValidator = new CPFValidator();
		List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf);

		if (erros.size() > 0) {
			return false;
		} else {
			return true;
		}

	}

	private boolean validaEmail(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	private boolean validaNumCartao(String numCartao) {
		int sum = 0;
		boolean alternate = false;
		for (int i = numCartao.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(numCartao.substring(i, i + 1));
			if (alternate) {
				n *= 2;
				if (n > 9) {
					n = (n % 10) + 1;
				}
			}
			sum += n;
			alternate = !alternate;
		}
		return (sum % 10 == 0);
	}

	private boolean validaCep(int cep) {
		Check check = new SafeguardCheck();
		boolean hasError = check.elementOf(Integer.toString(cep), ParametroTipo.CPF).validate().hasError();
		if (hasError)
			messages.add("CEP INVÁLIDO !! - ");

		return hasError;
	}

	private boolean validaTelefone(String tel) {
		Check check = new SafeguardCheck();

		boolean hasError = check.elementOf(tel, ParametroTipo.TELEFONE).validate().hasError();

		return hasError;
	}

	public void validaDadosCadastroProduto(Produto prod) {

		if (prod.getCodigoProduto() == null || prod.getCodigoProduto().isEmpty())
			ValidaDados.INSTANCE.messages.add("PREENCHA O CODIGO DO PRODUTO - ");
		if (prod.getNome() == null || prod.getNome().isEmpty())
			ValidaDados.INSTANCE.messages.add("PREENCHA O NOME DO PRODUTO - ");
		if (prod.getPreco() == 0)
			ValidaDados.INSTANCE.messages.add("PREENCHA O VALOR DO PRODUTO - ");

	}

	public void validaDadosCadastroCliente(Cliente cliente) {

		if (!(validaCPF(cliente.getDadosPessoa().getCpf())))
			messages.add("CPF INVÁLIDO !! - ");
		if (!(validaEmail(cliente.getDadosPessoa().getEmail())))
			messages.add("EMAIL INVÁLIDO !! - ");
		if (cliente.getDadosPessoa().getTelefoneCelular() != null
				|| !(cliente.getDadosPessoa().getTelefoneCelular().isEmpty()))
			if (validaTelefone(cliente.getDadosPessoa().getTelefoneCelular())) {
				messages.add("TELEFONE CELULAR INVÁLIDO !! - ");
			}

		if (cliente.getDadosPessoa().getTelefoneComercial() != null
				|| !(cliente.getDadosPessoa().getTelefoneComercial().isEmpty()))
			if (validaTelefone(cliente.getDadosPessoa().getTelefoneComercial())) {
				messages.add("TELEFONE COMERCIAL INVÁLIDO !! - ");
			}

		if (cliente.getDadosPessoa().getTelefoneResidencial() != null
				|| !(cliente.getDadosPessoa().getTelefoneResidencial().isEmpty()))
			if (validaTelefone(cliente.getDadosPessoa().getTelefoneResidencial())) {
				messages.add("TELEFONE RESIDENCIAL INVÁLIDO !! - ");
			}

		if (cliente.getLogin() == null || cliente.getLogin().isEmpty() || cliente.getLogin().trim().length() < 4)
			messages.add("LOGIN INVALIDO !! - ");
		if (cliente.getPassword() == null || cliente.getPassword().isEmpty()
				|| cliente.getPassword().trim().length() < 4)
			messages.add("SENHA INVALIDO !! - ");

	}

	public void validaDadosEndereco(Endereco end) {
		validaCep(end.getNumCep());
	}
	
	public void validaDadosCartao(Cartao card) {
	
		if(!(validaNumCartao(Integer.toString(card.getNumCartao())))) {
			messages.add("CARTÃO INVALIDO ");
		}
			
	}
	

}
