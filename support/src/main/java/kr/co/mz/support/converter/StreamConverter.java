package kr.co.mz.support.converter;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StreamConverter {

  public static <T> Stream<T> fromIterable(final Iterable<T> source) {
    return Optional.ofNullable(source)
        .map(Iterable::spliterator)
        .map(spliterator -> StreamSupport.stream(spliterator, false))
        .orElse(Stream.empty());
  }
}
