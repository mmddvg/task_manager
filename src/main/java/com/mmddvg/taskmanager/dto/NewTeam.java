package com.mmddvg.taskmanager.dto;

import jakarta.validation.constraints.NotEmpty;

public record NewTeam(
        @NotEmpty
        String name
        ) {
}
