import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    void horseNameVariableTest()
    {
        Throwable throwable= assertThrows(IllegalArgumentException.class, ()->
                {
                    Horse horse = new Horse(null, 1,1 );
                }
                );
        assertEquals("Name cannot be null.", throwable.getMessage());
    }
    @ParameterizedTest
    @CsvSource({
            "-1, 1",
            "1,-1",

    })
    void horseVariableSpeedAndDistance(String speed, String distance)
    {
        Throwable throwable = assertThrows(IllegalArgumentException.class, ()->
        {
            Horse horse = new Horse("1", Double.parseDouble(speed), Double.parseDouble(distance) );
        }
        );
        if(Double.parseDouble(speed)<0)
        {
            assertEquals( "Speed cannot be negative.", throwable.getMessage());
        } else if(Double.parseDouble(distance)<0)
        {
            assertEquals("Distance cannot be negative.", throwable.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t\t", "\n\n\n"})
    void horseName(String name)
    {
        Throwable throwable = assertThrows(IllegalArgumentException.class, ()->
                {
                    Horse horse = new Horse(name, 1, 1);
                }
                );
        assertEquals("Name cannot be blank.", throwable.getMessage());
    }
    @Test
    void getName() {
        String name = new String("1");
        Horse horse = new Horse(name, 1, 1);
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeed() {
        Double speed = 1.0;
        Horse horse = new Horse("name", speed, 1);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Double distance = 1.0;
        Horse horse = new Horse("name", 1, distance);
        assertEquals(distance, horse.getDistance());
    }
    @Test
    void nullDistance(){
        Horse horse = new Horse("1", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void moveUse() {
        try(MockedStatic< Horse> utilities =  Mockito.mockStatic( Horse.class))
        {
            new Horse("n",1,1).move();
            utilities.verify(()-> Horse.getRandomDouble(0.2, 0.9));
        }

    }
    @ParameterizedTest
    @ValueSource(doubles = {0.1,0.2,0.3})
    void move(double randome){
        try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class))
        {
            Horse horse = new Horse("1", 2,3);
            mockedStatic.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(randome);

            horse.move();

            assertEquals(3+2*randome, horse.getDistance());
        }
    }
}