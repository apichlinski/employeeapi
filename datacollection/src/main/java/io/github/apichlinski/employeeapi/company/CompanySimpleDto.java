package io.github.apichlinski.employeeapi.company;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class CompanySimpleDto {
    private int id;
    private String name;
}
