/*******************************************
 * Class: Test Class für die Inputs in der KundenImport Anwendung.
 * @author: Labinot Jaha
 * @version: 1.0
 ******************************************/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KundenTest {
    public static final String[] falseValues = new String[]{"0", " ", ",.-","@","$","/","<>"};
    public static final String[] falseValuesHaus = new String[]{"a", " ", ",.-","@","$","/","<>"};
    public static final String[] falseValuesPLZ = new String[]{"a", " ", ",.-","@","$","/","<>","0","01","012","0123","012345",};
    @Test
    void testInvalidInputRegex(){
        // Arrange
        String regex = "^[a-zA-Z\\\\s]+";

        for (int i = 0; i < falseValues.length; i++) {
            Assertions.assertFalse(falseValues[i].matches(regex));
        }
    }
    @Test
    void testInvalidInputHausRegex(){
        // Arrange
        String regex = "([\\d]+)([a-zA-z]?)";

        for (int i = 0; i < falseValuesHaus.length; i++) {
            Assertions.assertFalse(falseValuesHaus[i].matches(regex));
        }
    }

    @Test
    void testInvalidInputPLZRegex(){
        // Arrange
        String regex = "^[0-9]{5}$";

        for (int i = 0; i < falseValuesPLZ.length; i++) {
            Assertions.assertFalse(falseValuesPLZ[i].matches(regex));
        }
    }
}
