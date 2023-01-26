package kr.co.mz.jira.application.port.out;

public interface CreateEmptyDocumentPort {

  byte[] create(final String defaultMessage);
}
