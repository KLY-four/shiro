package com.example.demo.validtor;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
public class Dto {
//    @Length(min = 1,max = 2,message = "长度必须小于2大于1",groups = Save.class)
    @NameValid(2)
    String name;

    @Min(value = 3,message = "不能小于3")
    int age;

    interface Save{

    }

    interface Update{

    }

}
