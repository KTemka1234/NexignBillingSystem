package ru.learnhub.crm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.learnhub.crm.dto.AbonentDto;
import ru.learnhub.crm.dto.ChangeTariffDto;
import ru.learnhub.crm.service.ManagerService;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/manager")
@Slf4j
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

//    @PatchMapping(name = "/changeTariff")
//    public ResponseEntity<ChangeTariffDto> changeTariff(ChangeTariffDto request) {
//        return ResponseEntity.ok();
//    }

//    @RequestMapping(name = "/abonent", method = RequestMethod.POST)
//    public AbonentDto createAbonent(@RequestBody AbonentDto newAbonent) {
//        try {
//            managerService.createAbonent(newAbonent);
//        } catch (ParseException e) {
//            log.error(e.getMessage());
//            return new AbonentDto();
//        }
//        return newAbonent;
//    }

//    @RequestMapping(name = "/billing")
//    public @ResponseBody Map<String, String> makeRate() {
//
//    }
}
