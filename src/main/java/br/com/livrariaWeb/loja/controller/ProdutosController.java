package br.com.livrariaWeb.loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.livrariaWeb.loja.dao.ProdutoDAO;
import br.com.livrariaWeb.loja.models.Produto;
import br.com.livrariaWeb.loja.models.TipoPreco;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	@RequestMapping("/form")
    public ModelAndView form() {

        ModelAndView modelAndView = new ModelAndView("produtos/form");
        modelAndView.addObject("tipos", TipoPreco.values());

        return modelAndView;

    }
	
	@RequestMapping(method=RequestMethod.POST)
	public String grava(Produto produto) {
		System.out.println(produto);
		produtoDao.gravar(produto);
		
		return "produtos/ok";
	}
	
	@RequestMapping(method=RequestMethod.GET)
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("produtos/lista");
        List<Produto> produtos = produtoDao.listar();
        modelAndView.addObject("produto", produtos);

        return modelAndView;
    }

}
