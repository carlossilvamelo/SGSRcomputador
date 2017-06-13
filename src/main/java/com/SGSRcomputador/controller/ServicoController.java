package com.SGSRcomputador.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SGSRcomputador.enumeracoes.EnumCores;
import com.SGSRcomputador.enumeracoes.EnumStatus;
import com.SGSRcomputador.frameworkPDS.models.CheckIn;
import com.SGSRcomputador.frameworkPDS.models.Cliente;
import com.SGSRcomputador.frameworkPDS.models.Computador;
import com.SGSRcomputador.frameworkPDS.models.Loja;
import com.SGSRcomputador.frameworkPDS.models.Servico;
import com.SGSRcomputador.frameworkPDS.services.ClienteService;
import com.SGSRcomputador.frameworkPDS.services.ComputadorService;
import com.SGSRcomputador.frameworkPDS.services.LojaService;
import com.SGSRcomputador.frameworkPDS.services.MarcaModeloService;
import com.SGSRcomputador.frameworkPDS.services.ServicoService;



@Controller
@RequestMapping("/servico")
public class ServicoController {

	@Autowired
	private ClienteService clienteService;
	@Autowired
	private LojaService lojaService;
	@Autowired
	private ServicoService servicoService;
	@Autowired
	private ComputadorService computadorService;
	@Autowired
	private MarcaModeloService marcaModeloService;

	@GetMapping("/novoServico")
	public ModelAndView formServico(@RequestParam(name="id", required=true) String id, HttpSession session, String descricao){

		ModelAndView mv = new ModelAndView("/servico/formServico");
		Servico servico = new Servico();
		
		Cliente cliente = (Cliente) session.getAttribute("usuario");
		Cliente temp = (Cliente) clienteService.buscarPorId(cliente.getId());
		
		List<Computador> computadores = temp.getComputadores();
		List<Loja> lojas = lojaService.buscarTodos();
		
	
		if(descricao != null){
			servico.setDescricao(descricao);
		}
		
		mv.addObject("servico", servico);
		mv.addObject("computadores", computadores);
		mv.addObject("lojas", lojas);
		
		return mv;
	}

	@PostMapping("/novoServico")
	public ModelAndView cadastrarServico(Servico servico, HttpSession session, RedirectAttributes attributes){

		ModelAndView mv = new ModelAndView("redirect:/cliente");
		Cliente tmp = (Cliente) session.getAttribute("usuario");
		Cliente cliente = (Cliente) clienteService.buscarPorId(tmp.getId());
		
		if(cliente != null){
			Loja lojaServico = lojaService.buscarPorId(servico.getLoja().getId());
			Computador computadorServico = servico.getComputador();
			if(computadorServico != null){
				computadorServico = computadorService.buscarPorId(servico.getComputador().getId());
				if(lojaServico != null && computadorServico != null){
					servico.setStatus(EnumStatus.PRE_DIAGNOSTICO);
					servico.setLoja(lojaServico);
					servico.setComputador(computadorServico);
					servico.setCliente(cliente);
					cliente.addServico(servico);
					servicoService.inserir(servico);
				}
			}
		}
		
		attributes.addAttribute("message", "nova visita marcada!");

		return mv;
		
	}
	
	@GetMapping("/novoServicoLoja")
	public ModelAndView formServicoLoja(@RequestParam(name="id", required=true) String id, HttpSession session, String descricao){

		ModelAndView mv = new ModelAndView("/servico/formServicoLoja");
		Servico servico = new Servico();
		
		//Pegando informações para veículo
		List<String> marcas = computadorService.buscarMarcas();
		
		mv.addObject("marcas", marcas);
		mv.addObject("cores", EnumCores.values());
		mv.addObject("servico", servico);
		
		return mv;
	}
	
	@PostMapping("/novoServicoLoja")
	public ModelAndView cadastrarServicoLoja(Servico servico, HttpSession session, RedirectAttributes attributes){

		ModelAndView mv = new ModelAndView("redirect:/loja");
		Loja tmp = (Loja) session.getAttribute("usuario");
		Loja loja = (Loja) lojaService.buscarPorId(tmp.getId());

		servico.setStatus(EnumStatus.PRE_DIAGNOSTICO);
		servico.setLoja(loja);
		
		Cliente cliente = (Cliente) clienteService.buscarPorId(servico.getCliente().getId());
		
		if(cliente == null){
			attributes.addAttribute("message", "Cliente não cadastrado.");
			System.out.println("O cliente não está cadastrado");
			return mv;
		}
		else{
			Computador computadorAtual = null;
			
			List<Computador> computadores = cliente.getComputadores();
			for (int i = 0; i < computadores.size(); i++) {
				if(computadores.get(i).getMarcaModelo().getId().equals(servico.getComputador().getId())
						&&  computadores.get(i).getCor().equals(servico.getComputador().getCor())){
					computadorAtual = computadorService.buscarPorId(computadores.get(i).getId());
				}
			}
			
			if(computadorAtual == null){
				computadorAtual = new Computador();
				computadorAtual.setMarcaModelo(marcaModeloService.buscarPorMarcaModelo(servico.getComputador().getMarcaModelo().getMarca(),
						servico.getComputador().getMarcaModelo().getModelo()));
				computadorAtual.setCor(servico.getComputador().getCor());
				computadorAtual.setCliente(cliente);
				
				cliente.addComputador(computadorAtual);
				
				computadorService.inserir(computadorAtual);
				System.out.println("Cliente não tinha computador. Adicionando computador a cliente.");
			}
			
			servico.setCliente(cliente);
			servico.setComputador(computadorAtual);
			loja.addServico(servico);
			cliente.addServico(servico);
			servicoService.inserir(servico);
		}

		return mv;

	}
	
	@GetMapping("/listarModelos" )
	public  @ResponseBody List<String> listarModelos( String marca){
		
		List<String> modelos = computadorService.buscarModelosPorMarca(marca);
		
		return modelos;
	}

	@GetMapping("/relatorio")
	public ModelAndView relatorio(@RequestParam(name="id", required=true) Integer id){
		
		ModelAndView mv = new ModelAndView("/servico/relatorioServico");
		
		Servico servico = (Servico) servicoService.buscarPorId(id);
		mv.addObject("servico",servico);
		
		return mv;
		
	}
	
	@GetMapping("/proximoStatus")
	public ModelAndView proximoStatus(@RequestParam(name="id", required=true) Integer id){

		ModelAndView mv = new ModelAndView("redirect:/loja");		
		servicoService.proximoStatus(id);
		return mv;
	}
	
	@GetMapping("/aprovarServico")
	public ModelAndView aprovarServico(@RequestParam(name="id", required=true) Integer id){
		
		ModelAndView mv = new ModelAndView("redirect:/cliente");		
		servicoService.aprovarOrcamentoServico(id);
		return mv;
	}
	
	@GetMapping("/aprovarServicoAdmin")
	public ModelAndView aprovarServicoAdmin(@RequestParam(name="id", required=true) Integer id){
		
		ModelAndView mv = new ModelAndView("redirect:/loja");		
		servicoService.aprovarOrcamentoServico(id);
		return mv;
	}
	
	@GetMapping("/elaboracaoOrcamento")
	public ModelAndView elaboracaoOrcamento(@RequestParam(name="id", required=true) Integer id){

		ModelAndView mv = new ModelAndView("redirect:/loja");		
		servicoService.elaboracaoOrcamento(id);
		return mv;
	}
	
	@GetMapping("/cancelado")
	public ModelAndView cancelado(@RequestParam(name="id", required=true) Integer id){

		ModelAndView mv = new ModelAndView("redirect:/loja");		
		servicoService.cancelado(id);
		return mv;
	}
	
	@GetMapping("/canceladoCliente")
	public ModelAndView canceladoCliente(@RequestParam(name="id", required=true) Integer id){

		ModelAndView mv = new ModelAndView("redirect:/cliente");		
		servicoService.cancelado(id);
		return mv;
	}
	
	@GetMapping("/aguardandoPecas")
	public ModelAndView aguardandoPecas(@RequestParam(name="id", required=true) Integer id){

		ModelAndView mv = new ModelAndView("redirect:/loja");		
		servicoService.aguardandoPecas(id);
		return mv;
	}
	
	@GetMapping("/aguardandoCliente")
	public ModelAndView aguardandoCliente(@RequestParam(name="id", required=true) Integer id){

		ModelAndView mv = new ModelAndView("redirect:/loja");		
		servicoService.aguardandoCliente(id);
		return mv;
	}
	
	@GetMapping("/emAndamento")
	public ModelAndView emAndamento(@RequestParam(name="id", required=true) Integer id){

		ModelAndView mv = new ModelAndView("redirect:/loja");		
		servicoService.emAndamento(id);
		return mv;
	}
	
	@GetMapping("/finalizado")
	public ModelAndView finalizado(@RequestParam(name="id", required=true) Integer id){

		ModelAndView mv = new ModelAndView("redirect:/loja");		
		servicoService.finalizado(id);
		return mv;
	}
	
	@GetMapping("/acompanhamento")
	public ModelAndView acompanhamento(@RequestParam(name="id", required=true) Integer id){
		
		ModelAndView mv = new ModelAndView("/servico/acompanhamento");	
		
		Servico servico =  servicoService.buscarPorId(id);
		List<CheckIn> lista = null;
		
		if(servico != null){
			
			lista = servico.getAcompanhamento().getCheckIns();
			
			if(lista.isEmpty()){
				
				mv.addObject("msg", "Não há informações sobre o andamento do serviço nesse momento!");
				
			}else{
				
				mv.addObject("lista", lista);
			}
				
		}
		
		return mv;
	}
	
	@GetMapping("/finalizar")
	public ModelAndView finalizarServico(@RequestParam(name="id", required=true) Integer id, RedirectAttributes attributes){
		
		ModelAndView mv = new ModelAndView("redirect:/cliente");	
		
		//verifica se chegou no checkin de serviço finalizado para remover o servico
		servicoService.verificarServico(id);
		attributes.addAttribute("msg", "Serviço finalizado !");

		return mv;
	}

	
}
