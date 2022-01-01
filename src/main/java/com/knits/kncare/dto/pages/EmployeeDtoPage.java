package com.knits.kncare.dto.pages;

import com.knits.kncare.dto.EmployeeDto;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDtoPage {
    // content because I receive a json in format content[employees{}], so the name needs to be content
    List<EmployeeDto> content;
}
