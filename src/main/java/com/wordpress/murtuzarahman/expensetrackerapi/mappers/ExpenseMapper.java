package com.wordpress.murtuzarahman.expensetrackerapi.mappers;

import com.wordpress.murtuzarahman.expensetrackerapi.dto.ExpenseDTO;
import com.wordpress.murtuzarahman.expensetrackerapi.entity.Expense;
import com.wordpress.murtuzarahman.expensetrackerapi.io.ExpenseRequest;
import com.wordpress.murtuzarahman.expensetrackerapi.io.ExpenseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseMapper INSTANCE = Mappers.getMapper( ExpenseMapper.class );

    @Mapping(target = "category", source = "expenseDTO.categoryDTO")
    ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO);

    ExpenseDTO mapToExpenseDTO(ExpenseRequest request);

    Expense mapToExpenseEntity(ExpenseDTO expenseDTO);

    @Mapping(target = "categoryDTO", source = "expense.category")
    ExpenseDTO mapToExpenseDTO(Expense expense);
}
