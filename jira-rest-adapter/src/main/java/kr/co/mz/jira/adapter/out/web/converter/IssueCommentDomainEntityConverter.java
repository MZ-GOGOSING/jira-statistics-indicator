package kr.co.mz.jira.adapter.out.web.converter;

import com.atlassian.jira.rest.client.api.domain.BasicUser;
import com.atlassian.jira.rest.client.api.domain.Comment;
import java.util.Optional;
import kr.co.mz.jira.domain.IssueCommentDomainEntity;
import kr.co.mz.support.converter.LocalDateTimeConverter;
import org.springframework.core.convert.converter.Converter;

public class IssueCommentDomainEntityConverter implements Converter<Comment, IssueCommentDomainEntity> {

  @Override
  public IssueCommentDomainEntity convert(final Comment comment) {
    return IssueCommentDomainEntity.fromOrigin(
        comment.getId(),
        Optional.ofNullable(comment.getAuthor())
            .map(BasicUser::getDisplayName)
            .orElse(null),
        Optional.ofNullable(comment.getAuthor())
            .map(BasicUser::getAccountId)
            .orElse(null),
        Optional.ofNullable(comment.getUpdateAuthor())
            .map(BasicUser::getDisplayName)
            .orElse(null),
        Optional.ofNullable(comment.getUpdateAuthor())
            .map(BasicUser::getAccountId)
            .orElse(null),
        LocalDateTimeConverter.fromJoda(comment.getCreationDate()),
        LocalDateTimeConverter.fromJoda(comment.getUpdateDate()),
        comment.getBody()
    );
  }
}
