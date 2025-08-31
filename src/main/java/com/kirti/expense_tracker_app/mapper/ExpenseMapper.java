package com.kirti.expense_tracker_app.mapper;

import com.kirti.expense_tracker_app.dto.CategoryDto;
import com.kirti.expense_tracker_app.dto.ExpenseDto;
import com.kirti.expense_tracker_app.entity.Category;
import com.kirti.expense_tracker_app.entity.Expense;

public class ExpenseMapper {

    // Map ExpenseDto to Expense entity
    public static Expense mapToExpense(ExpenseDto expenseDto) {
        Category category = new Category();
        category.setId(expenseDto.categoryDto().id());

        return new Expense(
                expenseDto.id(),
                expenseDto.amount(),
                expenseDto.expenseDate(),
                category
        );

    }


    // Map Expense entity to ExpenseDto
    public static ExpenseDto mapToExpenseDto(Expense expense) {
        return new ExpenseDto(
                expense.getId(),
                expense.getAmount(),
                expense.getExpenseDate(),
                new CategoryDto(
                        expense.getCategory().getId(),
                        expense.getCategory().getName()
                ));
    }


}
