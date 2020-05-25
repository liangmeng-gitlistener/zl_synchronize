package cn.cncommdata.test;
import	java.util.ArrayList;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Log
public class LambdaTest {
    public interface Math {
        int multiply(int a, int b);
        double divide(int a, int b);
    }

    public interface NumberProvider {
        Optional<Integer> getNumber(int index);
        void init();
    }
    public static class MyMath implements Math {
        @Override
        public int multiply(int a, int b) {
            return a*b;
        }
        @Override
        public double divide(int a, int b) {
            if (b == 0) {
                return 0;
            } else {
                return a/b;
            }
        }
    }
    public class MyNumberProvider implements NumberProvider {
        List<Optional<Integer>> munList;
        @Override
        public Optional<Integer> getNumber(int index) {
            return munList.get(index);
        }
        @Override
        public void init(){
            if(CollUtil.isEmpty(munList)){
                munList = new ArrayList<Optional<Integer> > ();
                munList.add(Optional.of(4));
                munList.add(Optional.of(2));
            }
        }
    }

//    public Optional<Double> divideFirstTwo(NumberProvider numberProvider, Math math) {
//        Optional<Integer> first = numberProvider.getNumber(0);
//        Optional<Integer> second = numberProvider.getNumber(1);
//        if(first.isPresent() && second.isPresent()) {
//            double result = math.divide(first.get(), second.get());
//            return Optional.of(result);
//        } else {
//            return Optional.empty();
//        }
//    }
//    public Optional<Double> divideFirstTwo(NumberProvider numberProvider, Math math) {
//        return numberProvider.getNumber(0)
//                .flatMap(first -> numberProvider.getNumber(1)
//                        .map(second -> math.divide(first, second)));
//    }
    public interface Optionals {
        static <R, T, Z> BiFunction<Optional<T>, Optional<R>, Optional<Z>> lift(
                BiFunction<? super T, ? super R, ? extends Z> function) {
            return (left, right) -> left.flatMap(
                    leftVal -> right.map(rightVal -> function.apply(leftVal, rightVal)));
        }
    }
    public Optional<?> divideFirstTwo(NumberProvider numberProvider, Math math) {
        return Optionals.lift(math::divide).apply(numberProvider.getNumber(0),
                numberProvider.getNumber(1));
    }

    @Test
    void myTest() {
        NumberProvider numberProvider = new MyNumberProvider();
        numberProvider.init();
        Optional<?> result = divideFirstTwo(numberProvider, new MyMath());
        log.info(result.toString());
    }
}