package com.mmddvg.taskmanager.dto;

import jakarta.validation.constraints.NotEmpty;

public record NewProject(
        @NotEmpty
        String name,
        @NotEmpty
        String description,
        Integer teamId
) {
}
