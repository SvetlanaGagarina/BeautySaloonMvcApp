package org.top.beautysaloonmvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.top.beautysaloonmvcapp.service.ProcedureService;

// ProcedureController - контроллер для работы с услугами
@Controller
@RequestMapping("procedure")
public class ProcedureController {

    // сервис для работы со списком услуг
    private final ProcedureService procedureService;
    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }


}
