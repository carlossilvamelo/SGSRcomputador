package com.SGSRcomputador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.SGSRcomputador.frameworkPDS.models.Cliente;
import com.SGSRcomputador.frameworkPDS.models.Servico;

@Controller
@RequestMapping("/pagamento")
public class PagamentoControler {

	@GetMapping("/boleto")
	public ModelAndView gerarBoleto(Servico servico){
		ModelAndView mv = new ModelAndView("Redirect:pagamento/boleto");
		Cliente cliente = new Cliente();
		mv.addObject("cliente", cliente);
		return mv;	
	}
	
}
