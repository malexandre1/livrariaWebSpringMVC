package br.com.livrariaWeb.loja.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.livrariaWeb.loja.dao.ProdutoDAO;
import br.com.livrariaWeb.loja.models.Produto;
import br.com.livrariaWeb.loja.models.TipoPreco;
import br.com.livrariaWeb.loja.validation.ProdutoValidation;

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
    public ModelAndView grava(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
		System.out.println(produto);
		if(result.hasErrors()) {
            return form();
        }
		
        produtoDao.gravar(produto);
        
        redirectAttributes.addFlashAttribute("Sucesso!", "Produto cadastrado com sucesso");

        return new ModelAndView("redirect:produtos");
    }
	
	@RequestMapping(method=RequestMethod.GET)
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("produtos/lista");
        List<Produto> produtos = produtoDao.listar();
        modelAndView.addObject("produto", produtos);

        return modelAndView;
    }
	
	@InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.addValidators(new ProdutoValidation());
    }

}
