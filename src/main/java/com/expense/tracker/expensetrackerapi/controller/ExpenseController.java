package com.expense.tracker.expensetrackerapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

    @GetMapping(value = "/expenses")
    public String getAllExpenses() {
        return "List of Expenses";
    }
}
