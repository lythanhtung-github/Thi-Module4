package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.Country;
import com.codegym.service.city.ICityService;
import com.codegym.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/city", ""})
public class CityController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("city/list");
        modelAndView.addObject("cityList", cityService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("city/create");
        modelAndView.addObject("city", new City());
        modelAndView.addObject("countryList", countryService.findAll());
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showViewCity(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/city/view");
        Optional<City> city = cityService.findById(id);
        if (!city.isPresent()) {
            modelAndView.addObject("error", "Không tìm thấy city");
            return modelAndView;
        }
        City newCity = city.get();
        modelAndView.addObject("city", newCity);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<City> cityOptional = cityService.findById(id);
        if (!cityOptional.isPresent()) {
            modelAndView.setViewName("/city/list");
            modelAndView.addObject("cityList", cityService.findAll());
            modelAndView.addObject("error", "Không tìm thấy city");
            return modelAndView;
        }
        City city = cityOptional.get();
        modelAndView.addObject("city", city);
        modelAndView.setViewName("/city/delete");
        return modelAndView;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showFormEditCity(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/city/edit");

        List<Country> countryList = countryService.findAll();
        modelAndView.addObject("countryList", countryList);
        Optional<City> city = cityService.findById(id);
        if (!city.isPresent()) {
            modelAndView.setViewName("/city/list");
            modelAndView.addObject("cityList", cityService.findAll());
            modelAndView.addObject("error", "Id thành phố không tồn tại");
            return modelAndView;
        }
        City newCity = city.get();
        modelAndView.addObject("city", newCity);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView doEdit(@PathVariable Long id, @Validated @ModelAttribute City city, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/city/edit");

        Optional<City> cityOptional = cityService.findById(id);

        if (!cityOptional.isPresent()) {
            modelAndView.addObject("error", "Id thành phố không tồn tại");
            modelAndView.addObject("cityList", cityService.findAll());
            modelAndView.setViewName("/city/list");
            return modelAndView;
        }

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("error", true);
            modelAndView.addObject("city", city);
            return modelAndView;
        }

        cityService.save(city);
        modelAndView.addObject("city", city);
        List<Country> countryList = countryService.findAll();
        modelAndView.addObject("success", true);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView doDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/city/list");
        Optional<City> city = cityService.findById(id);
        if (!city.isPresent()) {
            modelAndView.addObject("error", "Id thành phố không tồn tại");
            return modelAndView;
        }
        cityService.remove(id);

        List<City> cityList = cityService.findAll();
        modelAndView.addObject("cityList", cityList);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView doCreate(@Validated @ModelAttribute City city,
                                 BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("city/create");

        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("error", true);
            return modelAndView;
        }

        try {
            city.setId(0L);
            cityService.save(city);
            modelAndView.addObject("city", new City());
            modelAndView.addObject("countryList", countryService.findAll());
            modelAndView.addObject("success", true);
        } catch (Exception e) {
            modelAndView.addObject("error", true);
        }
        return modelAndView;
    }
}
