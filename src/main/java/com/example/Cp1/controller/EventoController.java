package com.example.Cp1.controller;

import com.example.Cp1.model.Cidade;
import com.example.Cp1.model.Evento;
import com.example.Cp1.service.CidadeService;
import com.example.Cp1.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService service;

    @Autowired
    private CidadeService cidadeService;


    @PostMapping()
    public String insert(@Valid Evento categoria,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "/evento/evento-editar";
        }
        categoria = service.insert(categoria);
        attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
        return "redirect: /evento";
    }


    @GetMapping()
    public String getAll(Model model){
        List<Evento> eventos = service.findAll();
        model.addAttribute("eventos", eventos);
        return "/evento/evento-listar";
    }


    @GetMapping("/novo")
    public String loadForm(Model model) {
        model.addAttribute("evento", new Evento());
        List<Cidade> cidades = cidadeService.findAll();
        model.addAttribute("cidades", cidades);
        return "/evento/evento-novo";
    }




    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        Evento evento = service.findById(id);
        model.addAttribute("evento", evento);
        List<Cidade> cidades = cidadeService.findAll();
        model.addAttribute("cidades", cidades);
        return "/evento/evento-editar";
    }


    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @Valid Evento evento, BindingResult result){
        if (result.hasErrors()) {
            evento.setId(id);
            return "evento/evento-novo";
        }
        service.update(id, evento);
        return "/evento/eventos-listar";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        service.delete(id);
        return "redirect:/evento";
    }

}
