package kr.co.mz.jira.api.service;

import static com.atlassian.jira.rest.client.api.IssueRestClient.Expandos.CHANGELOG;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.IssueRestClient.Expandos;
import com.atlassian.jira.rest.client.api.domain.BasicWatchers;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.AttachmentInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import io.atlassian.util.concurrent.Promise;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import kr.co.mz.jira.api.request.command.CreateIssueBodyCommand;
import kr.co.mz.jira.api.request.command.CreateIssueCommand;
import kr.co.mz.jira.api.request.command.CreateIssueHeaderCommand;
import kr.co.mz.jira.api.request.command.IssueAttachmentInputCommand;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class IssueRestClientService {

  /**
   * @see IssueRestClient#getIssue(String, Iterable)
   */
  private static final Set<Expandos> ISSUE_ADDITIONAL_EXPANDS_SET = Set.of(CHANGELOG);

  private final IssueRestClient issueRestClient;

  public List<Issue> loadAllByIssueKeyList(final List<String> issueKeyList) {
    return CollectionUtils.emptyIfNull(issueKeyList)
        .stream()
        .filter(StringUtils::isNotBlank)
        .map(issueKey -> issueRestClient.getIssue(issueKey, ISSUE_ADDITIONAL_EXPANDS_SET))
        .map(Promise::claim)
        .collect(Collectors.toList());
  }

  public String createIssue(final CreateIssueCommand createIssueCommand) {
    final var headerCommand = createIssueCommand.getHeader();
    final var bodyCommand = createIssueCommand.getBody();

    final var issueInput = this.proceedIssueInput(headerCommand, bodyCommand);

    final var createdBasicIssue = issueRestClient.createIssue(issueInput).claim();
    final var createdBasicIssueKey = createdBasicIssue.getKey();

    final var storedIssue = issueRestClient.getIssue(createdBasicIssueKey).claim();

    this.addIssueWatchers(storedIssue, bodyCommand.getWatcherUsernames());
    this.addIssueAttachments(storedIssue, bodyCommand.getAttachments());

    return storedIssue.getKey();
  }

  private IssueInput proceedIssueInput(
      final CreateIssueHeaderCommand headerCommand,
      final CreateIssueBodyCommand bodyCommand
  ) {
    return new IssueInputBuilder(headerCommand.getProjectKey(), headerCommand.getIssueTypeId())
        .setSummary(bodyCommand.getTitle())
        .setDescription(bodyCommand.getContents())
        .setReporterName(bodyCommand.getReporterUsername())
        .setAssigneeName(bodyCommand.getAssigneeUsername())
        .build();
  }

  private void addIssueWatchers(
      final Issue issue,
      final Set<String> usernames
  ) {
    final var issueWatchersURI = Optional.ofNullable(issue.getWatchers())
        .map(BasicWatchers::getSelf)
        .orElse(null);

    if (Objects.isNull(issueWatchersURI)) {
      return;
    }

    CollectionUtils.emptyIfNull(usernames)
        .forEach(username -> issueRestClient.addWatcher(issueWatchersURI, username));
  }

  @SuppressWarnings("FuseStreamOperations")
  private void addIssueAttachments(
      final Issue issue,
      final Set<IssueAttachmentInputCommand> attachments
  ) {
    final var issueAttachmentsURI = issue.getAttachmentsUri();

    if (Objects.isNull(issueAttachmentsURI) || CollectionUtils.isEmpty(attachments)) {
      return;
    }

    final var attachmentInputs = attachments
        .stream()
        .map(attachment -> new AttachmentInput(attachment.getFilename(), attachment.getInputStream()))
        .collect(Collectors.toSet());

    issueRestClient.addAttachments(issueAttachmentsURI, attachmentInputs.toArray(new AttachmentInput[0]));
  }
}
