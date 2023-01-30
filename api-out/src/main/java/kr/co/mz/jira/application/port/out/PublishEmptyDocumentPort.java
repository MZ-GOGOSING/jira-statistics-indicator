package kr.co.mz.jira.application.port.out;

public interface PublishEmptyDocumentPort {

  byte[] publish(final String defaultMessage);
}
