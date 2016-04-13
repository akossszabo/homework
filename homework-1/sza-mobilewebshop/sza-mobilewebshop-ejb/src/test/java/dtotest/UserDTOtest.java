package dtotest;

import com.mycompany.ee.dto.UserDTO;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;


public class UserDTOtest {
    
    @Test
    public void usernameSizeTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        UserDTO u = new UserDTO("xy","Asd12+","John","Doe",LocalDate.parse("1990-10-10"),LocalDate.parse("2010-10-10"),false);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("xy",violations.get(0).getInvalidValue());
    }
    
    @Test
    public void usernameNullTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        UserDTO u = new UserDTO(null,"Asd12+","John","Doe",LocalDate.parse("1990-10-10"),LocalDate.parse("2010-10-10"),false);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null,violations.get(0).getInvalidValue());
        Assert.assertEquals(u.getClass(),violations.get(0).getRootBeanClass());
    }
    
    @Test
    public void passwordNullTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        UserDTO u = new UserDTO("testUser",null,"John","Doe",LocalDate.parse("1990-10-10"),LocalDate.parse("2010-10-10"),false);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null,violations.get(0).getInvalidValue());
        Assert.assertEquals(u.getClass(),violations.get(0).getRootBeanClass());
    }
    
    @Test
    public void passwordSizeTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        UserDTO u = new UserDTO("testUser","Asd1+","John","Doe",LocalDate.parse("1990-10-10"),LocalDate.parse("2010-10-10"),false);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("Asd1+",violations.get(0).getInvalidValue());
    }
    
    @Test
    public void passwordRegexUpperCaseLetterTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        UserDTO u = new UserDTO("testUser","asd123+","John","Doe",LocalDate.parse("1990-10-10"),LocalDate.parse("2010-10-10"),false);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("asd123+",violations.get(0).getInvalidValue());
    }
    
    @Test
    public void passwordRegexLowerCaseLetterTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        UserDTO u = new UserDTO("testUser","ASD123+","John","Doe",LocalDate.parse("1990-10-10"),LocalDate.parse("2010-10-10"),false);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("ASD123+",violations.get(0).getInvalidValue());
    }
    
    @Test
    public void passwordRegexNumberTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        UserDTO u = new UserDTO("testUser","ASDasd+","John","Doe",LocalDate.parse("1990-10-10"),LocalDate.parse("2010-10-10"),false);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("ASDasd+",violations.get(0).getInvalidValue());
    }
    
    @Test
    public void passwordRegexSpecialCharacterTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        UserDTO u = new UserDTO("testUser","Asd123","John","Doe",LocalDate.parse("1990-10-10"),LocalDate.parse("2010-10-10"),false);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("Asd123",violations.get(0).getInvalidValue());
    }
    
    @Test
    public void registrationDateNotNullTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        UserDTO u = new UserDTO("user","Asd12+",null);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null,violations.get(0).getInvalidValue());
        Assert.assertEquals(u.getClass(),violations.get(0).getRootBeanClass());
    }
    
    @Test
    public void birthDayInPastTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        UserDTO u = new UserDTO("testUser","Asd123+","John","Doe",LocalDate.of(2017, Month.FEBRUARY, 10),LocalDate.MAX,false);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(LocalDate.of(2017, Month.FEBRUARY, 10),violations.get(0).getInvalidValue());
    }
    
    @Test
    public void noViolationTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        UserDTO user = new UserDTO("testUser","Asd123+","John","Doe",LocalDate.parse("2000-01-01"),LocalDate.parse("2010-10-10"),false);
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(user));
        Assert.assertEquals(0, violations.size());
    }
    
    
}
