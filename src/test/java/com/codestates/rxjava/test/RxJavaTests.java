package com.codestates.rxjava.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@RunWith(SpringRunner.class)
public class RxJavaTests {

    /**
     * @descrition string1, string2를 합쳐 하나의 String(result)을 만든다.
     * result의 값이 기대 한 값(value)과 맞는지 확인 한다.
     */

    @Test
    public void 첫문제() {
        Flux<String> string1 = Flux.just("Blenders", "Old", "Johnnie").delayElements(Duration.ofSeconds(1));
        Flux<String> string2 = Flux.just("Pride", "Monk", "Walker").delayElements(Duration.ofSeconds(1));
        Flux<String> result = Flux.concat(string1, string2).log();

        StepVerifier.create(result).expectSubscription().expectNext("Blenders", "Old", "Johnnie", "Pride", "Monk", "Walker").verifyComplete();
    }

    /**
     * @description 1~100까지의 숫자 중에 짝수를 필터 한 후, 짝수만 출력한다.
     */
    @Test
    public void 짝수출력() {
        Flux.range(1, 100).filter(i -> i % 2 == 0).log().subscribe();

    }

    /**
     * @description flux를 단계별로 검증
     * 첫 번째 예상값은 hello
     * 두 번째 예상되는 값은 there
     */
    @Test
    public void hello와there() {
        Flux<String> flux = Flux.just("hello", "there").publishOn(Schedulers.immediate()).log();

        StepVerifier.create(flux).expectNext("hello").expectNext("there").verifyComplete();
    }

    @Test
    public void 다섯번째문제() {
        Flux<String> flux1 = Flux.just("Blenders", "Old", "Johnnie");
        Flux<String> flux2 = Flux.just("Pride", "Monk", "Walker");
        Flux<String> result = Flux.zip(flux1, flux2, (result1, result2) -> result1 + " " + result2).log();

        StepVerifier.create(result).expectNext("Blenders Pride").expectNext("Old Monk").expectNext("Johnnie Walker").verifyComplete();
    }

    /**
     * @description flatMap -> 비동기
     * Map -> 동기
     * <p>
     * 5글자 이상인 것들을 필터 한 후, 대문자로 치환 하고 1회 반복하고, 해당 값들이 나올 것들을 기대한다.
     * expectNext에 다른 값을 넣으면 바로 에러가 뜬다.
     * 다    *
     */
    @Test
    public void 다섯글자이상() {
        Flux<String> flux = Flux.just("google", "abc", "fb", "stackoverflow").filter(i -> i.length() > 5)
                .flatMap(j -> Flux.just(j).map(String::toUpperCase)).repeat(1).log();

        StepVerifier.create(flux).expectNext("GOOGLE").expectNext("STACKOVERFLOW").expectNext("GOOGLE").expectNext("STACKOVERFLOW")
                .verifyComplete();

    }
}
