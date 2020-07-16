package com.spring.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidatorControllers {

    @Autowired
    CustomValidator validator;

//    @InitBinder
//    public void initBinder(WebDataBinder dataBinder){
//        dataBinder.setValidator(validator);
//    }
    @RequestMapping(value = "/valid/method",method = RequestMethod.GET)
    public String methodDependencyValidation(@ModelAttribute ValidatorObject object, BindingResult result){
        if( validator.supports(object.getClass()) ){
            validator.validate(object,result);
        }
        if(result.hasErrors()) {
            return result.getAllErrors().get(0).getCode();
        }

        return "SUSSES";
    }

    @RequestMapping(value="/valid/annotation",method = RequestMethod.GET)
    public String annotationValidation(@ModelAttribute @Validated ValidatorObject object, BindingResult result){
        if(result.hasErrors()){
          return result.getAllErrors().get(0).getCode();
        }
        return "SUSSES";
    }

    @RequestMapping(value="/valid/JSR303", method = RequestMethod.GET)
    public String JSR303Validation(@ModelAttribute JSR303ValidatorObject object){
        return "SUSSES";
    }
}
