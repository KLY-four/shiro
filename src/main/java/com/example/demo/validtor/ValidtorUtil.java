package com.example.demo.validtor;

import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ValidtorUtil  {

     public static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

     public static  <T>  Map<String,String>  validator(T t,Class<?> group){
      Set<ConstraintViolation<T>> validate = validator.validate(t);
      Map<String,String> map = new HashMap<>();
      for (ConstraintViolation constraintViolation : validate) {
           map.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
      }
      return map;
     }

     public static void print(Map<String,String> map){
      Set<Map.Entry<String, String>> set = map.entrySet();
      for (Map.Entry<String,String> entry: set) {
       System.out.println(entry.getKey()+ "---->" +map.get(entry.getKey()));
      }
     }

 public static void main(String[] args) {
  Map<String, String> validator = ValidtorUtil.validator(new Dto("12", 4),Dto.Save.class);
      ValidtorUtil.print(validator);
 }
}
