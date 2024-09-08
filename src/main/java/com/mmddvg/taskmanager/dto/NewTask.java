package com.mmddvg.taskmanager.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record NewTask(
        @NotEmpty
        String name,
        @Positive
        int project_id,
        String description
) {

}
