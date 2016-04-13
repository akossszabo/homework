package dtotest;

import com.mycompany.ee.dto.MobileDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;

public class MobileDTOtest {
    
    @Test
    public void testUUID() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        MobileDTO m = new MobileDTO("WrongId","Iphone 6", "Apple", 192000, 1);
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("WrongId",violations.get(0).getInvalidValue());
    }
    
    @Test
    public void nullTypeTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        MobileDTO m = new MobileDTO(UUID.randomUUID().toString(),null, "Apple", 192000, 1);
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null,violations.get(0).getInvalidValue());
        Assert.assertEquals(m.getClass(),violations.get(0).getRootBeanClass());
    }
    
    @Test
    public void sizeOfTypeTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        MobileDTO m = new MobileDTO(UUID.randomUUID().toString(),"xy", "Apple", 192000, 1);
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("xy",violations.get(0).getInvalidValue());
        Assert.assertEquals(m.getClass(),violations.get(0).getRootBeanClass());
    }
    
    @Test
    public void nullManufacturerTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        MobileDTO m = new MobileDTO(UUID.randomUUID().toString(),"Iphone 6s", null, 192000, 1);
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null,violations.get(0).getInvalidValue());
    }
    
    @Test
    public void sizeOfManufacturerTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        MobileDTO m = new MobileDTO(UUID.randomUUID().toString(),"Iphone 6s", "xy", 192000, 1);
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("xy",violations.get(0).getInvalidValue());
    }
    @Test
    public void nullPriceTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        MobileDTO m = new MobileDTO(UUID.randomUUID().toString(),"Iphone 6s", "Apple", 0, 1);
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(0,violations.get(0).getInvalidValue());
    }
    @Test
    public void pieceTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        MobileDTO m = new MobileDTO(UUID.randomUUID().toString(),"Iphone 6s", "Apple", 1, -1);
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(-1,violations.get(0).getInvalidValue());
    }
    
    @Test
    public void noViolationTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        MobileDTO m = new MobileDTO(UUID.randomUUID().toString(),"Iphone 6s", "Apple", 1, 1);
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(0, violations.size());
    }
    

}
