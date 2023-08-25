package com.example.electric.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// This annotation tells Spring that this class is a Controller
@Controller
public class ChargerController {

    @Autowired
    public ChargerRepository chargerRepository;

    @GetMapping("/")
    public String showChargers(Model model) {
        List<Charger> chargers = chargerRepository.findAll();
        model.addAttribute("chargers", chargers);
        return "index";
    }
    @PostMapping("/addCharger")
    public String addCharger(@ModelAttribute Charger charger) {
        chargerRepository.save(charger);
        return "redirect:/";
    }



}