package kr.co.mz.jira.application.port.out;

import javax.validation.Valid;
import kr.co.mz.jira.application.port.out.request.command.PublishSubjectDocumentOutCommand;

public interface PublishSubjectDocumentPort {

  byte[] publish(final @Valid PublishSubjectDocumentOutCommand outCommand);
}
