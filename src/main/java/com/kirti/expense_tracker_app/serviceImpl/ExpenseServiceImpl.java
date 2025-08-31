package com.kirti.expense_tracker_app.serviceImpl;

import com.kirti.expense_tracker_app.dto.ExpenseDto;
import com.kirti.expense_tracker_app.entity.Category;
import com.kirti.expense_tracker_app.entity.Expense;
import com.kirti.expense_tracker_app.exceptions.ResourceNotFoundException;
import com.kirti.expense_tracker_app.mapper.ExpenseMapper;
import com.kirti.expense_tracker_app.repository.CategoryRepository;
import com.kirti.expense_tracker_app.repository.ExpenseRepository;
import com.kirti.expense_tracker_app.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;

    private CategoryRepository categoryRepository;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {

        // convert ExpenseDto to Expense entity
        Expense expense = ExpenseMapper.mapToExpense(expenseDto);

        // save expense object into database table
        Expense savedExpense = expenseRepository.save(expense);

        // convert saved expense entity into ExpenseDto
        return ExpenseMapper.mapToExpenseDto(savedExpense);

    }

    @Override
    public ExpenseDto getExpenseById(Long expenseId) {

        // get expense entity from the database using expense id
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(()-> new ResourceNotFoundException("Expense not fund with id : " + expenseId));

        // convert expense entity to ExpenseDto
        return ExpenseMapper.mapToExpenseDto(expense);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {

        List<Expense> expenses = expenseRepository.findAll();

        return expenses.stream()
                .map((expense)-> ExpenseMapper.mapToExpenseDto(expense))
                .collect(Collectors.toList());


    }

    @Override
    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));

        // update expense amount
        expense.setAmount(expenseDto.amount());

        // update expense date
        expense.setExpenseDate(expenseDto.expenseDate());

        // update the category
        if(expenseDto.categoryDto() != null) {

            //get the category entity by id

           Category category =  categoryRepository.findById(expenseDto.categoryDto().id())
                    .orElseThrow(()-> new ResourceNotFoundException("Category not found with id: " + expenseDto.categoryDto().id()));

           expense.setCategory(category);
        }

        // update expense entity into database
        Expense updatedExpense = expenseRepository.save(expense);

        // convert expense entity to expenseDto
        return ExpenseMapper.mapToExpenseDto(updatedExpense);
    }

    @Override
    public void deleteExpense(long expenseId) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));

        expenseRepository.delete(expense);
    }
}
