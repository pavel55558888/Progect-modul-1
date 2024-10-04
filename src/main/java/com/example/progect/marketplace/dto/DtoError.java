package com.example.progect.marketplace.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;
@Getter
@Setter
@Schema(name = "Dto ошибки", description = "При ошибке валидации, будет показано что, где и когда")
public class DtoError {
    private String error;
    @Schema(name = "Ошибка", description = "При валидации данных происходит какая-либо ошибка(Несоответсвие данных), данном поле она отражается")
    private List<ObjectError> listError;
}
