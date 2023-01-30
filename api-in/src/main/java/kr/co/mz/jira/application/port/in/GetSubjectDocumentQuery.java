package kr.co.mz.jira.application.port.in;

import javax.validation.Valid;
import kr.co.mz.jira.application.port.in.request.query.GetSubjectDocumentInQuery;

public interface GetSubjectDocumentQuery {

  byte[] publish(final @Valid GetSubjectDocumentInQuery inQuery);
}
