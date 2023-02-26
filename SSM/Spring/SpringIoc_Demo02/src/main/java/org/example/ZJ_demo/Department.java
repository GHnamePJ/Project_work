package org.example.ZJ_demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Department {
        @Value("2022")
        private String department_num;
        @Value("前端")
        private String department_name;
    public String getDepartment_num() {
        return department_num;
    }

    public String getDepartment_name() {
        return department_name;
    }
}
