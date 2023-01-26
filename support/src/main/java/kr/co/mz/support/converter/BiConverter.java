package kr.co.mz.support.converter;

public interface BiConverter<S1, S2, R> {

  R convert(S1 s1, S2 s2);
}
