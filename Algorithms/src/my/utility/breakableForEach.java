package my.utility;

import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class breakableForEach {

    public static class Breaker {
        private boolean breakState = false;
 
        public void stop() {
            breakState = true;
        }
 
        boolean get() {
            return breakState;
        }
    }
    
    public static <T> void forEach(Stream<T> stream, BiConsumer<T, Breaker> consumer) {
        Spliterator<T> spliterator = stream.spliterator();
        boolean hadNext = true;
        Breaker breaker = new Breaker();
 
        while (hadNext && !breaker.get()) {
            hadNext = spliterator.tryAdvance(elem -> {
                consumer.accept(elem, breaker);
            });
        }
    }
}
