/**
 * MIT License
 * Copyright (c) 2022 Tristan Mahinay
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * micronaut-java
 * @author rjtmahinay
 * 2022
 */
package com.rjtmahinay.service;

import com.rjtmahinay.dto.EmployeeDto;
import com.rjtmahinay.entity.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Expose synchronous components for the Demo API.
 */
public interface SynchronousEmployeeService {
    Optional<Employee> getEmployee(Long id);

    Employee addEmployee(EmployeeDto employeeDto);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Long id, EmployeeDto employeeDto);

    void deleteEmployee(Long id);
}
