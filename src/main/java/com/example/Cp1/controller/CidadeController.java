package com.example.Cp1.controller;

import com.example.Cp1.model.Cidade;
import com.example.Cp1.service.CidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    private CidadeService service;


    @PostMapping()
    public String insert(@Valid Cidade cidade,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "/cidade/cidade-novo";
        }
        cidade = service.insert(cidade);
        attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
        return "redirect:/cidade";
    }


    @GetMapping()
    public String getAll(Model model){
        List<Cidade> cidades = service.findAll();
        model.addAttribute("cidades", cidades);
        return "/cidade/cidade-listar";
    }


    @GetMapping("/novo")
    public String loadForm(Model model) {
        model.addAttribute("cidade", new Cidade());
        return "/cidade/cidade-novo";
    }




    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        Cidade cidade = service.findById(id);
        model.addAttribute("cidade", cidade);
        return "/cidade/cidade-editar";
    }


    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @Valid Cidade cidade, BindingResult result){
        if (result.hasErrors()) {
            cidade.setId(id);
            return "cidade/cidade-listar";
        }
        service.update(id, cidade);
        return "/cidade/cidades-editar";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        service.delete(id);
        return "/home";
    }

}
