import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {
  @Test
  void hippodromeNullTest()
  {
    Throwable throwable = assertThrows(IllegalArgumentException.class, ()->
            {
              Hippodrome hippodrome = new Hippodrome(null);
            }
            );
    assertEquals("Horses cannot be null.", throwable.getMessage());
  }
  @Test
  void hippodromeArrayIsEmpty()
  {
    List<Horse> horses = new ArrayList<>();
    Throwable throwable = assertThrows(IllegalArgumentException.class, ()->
            {
              Hippodrome hippodrome = new Hippodrome(horses);
            }
            );
    assertEquals("Horses cannot be empty.", throwable.getMessage());
  }
  @Test
    void getHorses() {
    List<Horse> horses = new ArrayList<>();
    for (int i = 0; i <30 ; i++) {
      horses.add(new Horse("1",1,1));
    }
    Hippodrome hippodrome = new Hippodrome(horses);
    assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
    List<Horse> horses = new ArrayList<>();
      for (int i = 0; i <50 ; i++) {
        horses.add(mock(Horse.class));
      }

      new Hippodrome(horses).move();

      for (Horse horse : horses)
      {
        verify(horse).move();
      }
  }

    @Test
    void getWinner() {
    List<Horse> horses = new ArrayList<>();
      for (int i = 0; i <4; i++) {
        horses.add(new Horse("1", 1, i));
      }
      Hippodrome hippodrome = new Hippodrome(horses);
      assertSame(horses.get(3), hippodrome.getWinner());
    }
}