package kr.co.mz.support.converter;

public interface TriConverter<S1, S2, S3, R> {

  R convert(S1 s1, S2 s2, S3 s3);
}
