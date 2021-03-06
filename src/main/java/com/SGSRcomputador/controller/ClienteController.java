package com.SGSRcomputador.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SGSRcomputador.enumeracoes.EnumCores;
import com.SGSRcomputador.enumeracoes.EnumMarcasModelos;
import com.SGSRcomputador.frameworkPDS.models.Computador;
import com.SGSRcomputador.frameworkPDS.models.Cliente;
import com.SGSRcomputador.frameworkPDS.models.MarcaModelo;
import com.SGSRcomputador.frameworkPDS.models.Servico;
import com.SGSRcomputador.frameworkPDS.services.ComputadorService;
import com.SGSRcomputador.frameworkPDS.services.ClienteService;
import com.SGSRcomputador.frameworkPDS.services.MarcaModeloService;
import com.SGSRcomputador.frameworkPDS.services.ServicoService;






@Controller
@RequestMapping("/cliente")
public class ClienteController {


	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ComputadorService computadorService;
	@Autowired
	private ServicoService servicoService;
	@Autowired
	private MarcaModeloService marcaModeloService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView cliente(HttpSession session){
		
		ModelAndView mv = new ModelAndView("cliente/cliente");
		//buscar cliente pelo id
		Cliente cliente = (Cliente) session.getAttribute("usuario");
		List<Servico> listaServicos = servicoService.buscarServicosPorContratante(cliente);
		mv.addObject("listaServicos",listaServicos);
		
		return mv;
		
	}
	
	@GetMapping("/novo")
	public ModelAndView formCliente(){
		ModelAndView mv = new ModelAndView("cliente/form");
		Cliente cliente = new Cliente();
		mv.addObject("cliente", cliente);
		return mv;
		
	}
	
	@PostMapping("/novo")
	public ModelAndView salvarCliente(Cliente cliente, RedirectAttributes attributes){
		
		boolean cpfValido = true;
		ModelAndView mv = new ModelAndView("cliente/form");
		
		clienteService.inserir(cliente);
		
		/*ValidacoesService validacaoCpf = ValidacoesService.getInstance();
		try {
			
			validacaoCpf.validarCpf(cliente.getId());
			clienteService.inserir(cliente);
		} catch (CamadaServicoException e) {
			cpfValido = false;
		}*/
		mv.addObject("cpfValido", cpfValido);
		attributes.addFlashAttribute("message", "O cliente foi cadastrado!");
		return mv;
		
	}
	
	@GetMapping("/novoComputador")
	public ModelAndView formCelula(@RequestParam(name="id", required=true) String id){
		
		
		ModelAndView mv = new ModelAndView("cliente/formComputador");

		Computador computador = new Computador();
		List<String> marcas = EnumMarcasModelos.listaMarcas();
		List<String> cores = EnumCores.getCores();
	
		mv.addObject("cores", cores);
		mv.addObject("marcas", marcas);
		mv.addObject("computador", computador);
	
		return mv;
	}
	
	@PostMapping("/novoComputador")
	public ModelAndView salvarCelula(Computador computador, HttpSession session){
		
		ModelAndView mv = new ModelAndView("cliente/formComputador");
		
		Cliente cliente = (Cliente) session.getAttribute("usuario");
		
		MarcaModelo m = marcaModeloService.buscarPorMarcaModelo(computador.getMarcaModelo().getMarca(), computador.getMarcaModelo().getModelo());
		
		computador.setMarcaModelo(m);
		computador.setCliente(cliente);
		cliente.addComputador(computador);
		System.out.println(computador.getId());
		clienteService.inserir(cliente);
		mv.addObject("message", "Veículo adicionado!");
		
		return mv;
	}
	
	@RequestMapping("/meusComputadores")
	public ModelAndView cadastrarComputador(HttpSession session){
		
		ModelAndView mv = new ModelAndView("cliente/meusComputadores");
		
		Cliente temp = (Cliente) session.getAttribute("usuario");
		Cliente cliente = (Cliente)clienteService.buscarPorId(temp.getId());
		
		
		/*
		for(Computador computador : cliente.getComputador()){
			
			for(Alerta alerta : computador.getAlertas()){
				
				System.out.println(alerta.getTipo());
			}
		}*/
		
		mv.addObject("cliente", cliente);
		return mv;
		
	}
	/*
	@GetMapping("/addInfoExtraComputador")
	public ModelAndView formInfoExtraComputador(@RequestParam(name="id", required=true) String id){

		ModelAndView mv = new ModelAndView("cliente/formInfoExtra");
		
	//	InfoExtraComputador infoExtraComputador = new InfoExtraComputador();
	//	Computador computador = (Computador)computadorService.buscarPorId(id);
		
	//	mv.addObject("computador", computador);
		//mv.addObject("infoExtraComputador", infoExtraComputador);
		
		return mv;
	}
	
	@PostMapping("/addInfoExtraComputador")
	public ModelAndView saveInfoExtraComputador(InfoExtraComputador infoExtraComputador){
	
		ModelAndView mv = new ModelAndView("redirect:meusComputadores");
		
		computadorService.adicionarInfoExtra(infoExtraComputador);
		
		return mv;
	}
	
	@PostMapping("/removerComputador")
	public @ResponseBody String removerComputador(String id){
	
		String result = "";
		System.out.println("Remover");
		System.out.println(id);
		
		try{
			
			servicoService.deletarTodos(computadorService.buscarPorId(id));
			//computadorService.deleteAlertasComputador(computadorService.buscarPorId(id));
			computadorService.deletePorId(id);
			result = "Success!";
			
		}catch(DataAccessException e){
			result = "Failed!";
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
	*/
	@GetMapping("/listarModelos" )
	public  @ResponseBody List<String> listarModelos( String marca){
		
		
		List<String> modelos = marcaModeloService.buscarModelosPorMarca(marca);
		
		return modelos;
		
		//return null;
		
	}
	
	
}
