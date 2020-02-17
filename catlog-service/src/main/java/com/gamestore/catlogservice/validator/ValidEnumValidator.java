/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamestore.catlogservice.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.gamestore.catlogservice.constraint.ValidEnum;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author qbuser
 */
public class ValidEnumValidator implements ConstraintValidator<ValidEnum, CharSequence> {

    private List<String> acceptedValues;

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        return value == null ? null : acceptedValues.contains(value.toString());
    }

    @Override
    public void initialize(ValidEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
