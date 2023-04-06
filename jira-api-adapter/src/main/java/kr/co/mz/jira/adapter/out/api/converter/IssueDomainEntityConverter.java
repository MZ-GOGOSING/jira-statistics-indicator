package kr.co.mz.jira.adapter.out.api.converter;


import com.atlassian.jira.rest.client.api.domain.AddressableNamedEntity;
import com.atlassian.jira.rest.client.api.domain.BasicWatchers;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import kr.co.mz.jira.domain.IssueDomainEntity;
import kr.co.mz.jira.support.converter.BiConverter;
import kr.co.mz.jira.support.converter.LocalDateTimeConverter;
import kr.co.mz.jira.support.converter.StreamConverter;
import org.apache.commons.lang3.ObjectUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jettison.json.JSONArray;

public class IssueDomainEntityConverter
    implements BiConverter<Issue, List<Worklog>, IssueDomainEntity> {

    private static final IssueTimeTrackingDomainEntityConverter ISSUE_TIME_TRACKING_DOMAIN_ENTITY_CONVERTER =
            new IssueTimeTrackingDomainEntityConverter();

    private static final IssueWorklogDomainEntityConverter ISSUE_WORKLOG_DOMAIN_ENTITY_CONVERTER =
            new IssueWorklogDomainEntityConverter();

    private static final IssueChangelogGroupDomainEntityConverter ISSUE_CHANGELOG_GROUP_DOMAIN_ENTITY_CONVERTER =
            new IssueChangelogGroupDomainEntityConverter();

    @Override
    public IssueDomainEntity convert(
        final Issue issue,
        final List<Worklog> worklogs
    ) {
        boolean isSubTask = false;
        String parentTask = "";
        JsonFactory jsonFactory = new MappingJsonFactory();
        JsonParser parser;
        ObjectCodec obj;
        JsonNode node;

        if(issue.getIssueType().isSubtask()){
            try {
                isSubTask = true;
                //{"id":"820998","key":"ITO-199","self":"https:\/\/jira.woowa.in\/rest\/api\/2\/issue\/820998","fields":{"summary":"[어드민]인터뷰전형_심사결과 PDF 다운로드 기능 추가","status":{"self":"https:\/\/jira.woowa.in\/rest\/api\/2\/status\/10305","description":"테스트 진행 중","iconUrl":"https:\/\/jira.woowa.in\/images\/icons\/statuses\/generic.png","name":"In Test","id":"10305","statusCategory":{"self":"https:\/\/jira.woowa.in\/rest\/api\/2\/statuscategory\/4","id":4,"key":"indeterminate","colorName":"yellow","name":"In Progress"}},"priority":{"self":"https:\/\/jira.woowa.in\/rest\/api\/2\/priority\/3","iconUrl":"https:\/\/jira.woowa.in\/images\/icons\/priorities\/medium.svg","name":"Medium","id":"3"},"issuetype":{"self":"https:\/\/jira.woowa.in\/rest\/api\/2\/issuetype\/10013","id":"10013","description":"아직 개발되지 않은 새로운 기능이 요구될 때 사용한다.","iconUrl":"https:\/\/jira.woowa.in\/secure\/viewavatar?size=xsmall&avatarId=10505&avatarType=issuetype","name":"New Feature","subtask":false,"avatarId":10505}}}
                IssueField issueParentField = issue.getField("parent"); // Epic Link
                parser = jsonFactory.createJsonParser(issueParentField.getValue().toString());
                obj = parser.getCodec();
                node = obj.readTree(parser);
                parentTask = node.get("key").getTextValue();
            } catch (JsonParseException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // IssueField{id=customfield_10006, name=Epic Link, type=null, value=ITO-1}
        IssueField issueField = issue.getField("customfield_10006"); // Epic Link
        String epicKey = ObjectUtils.defaultIfNull(issueField.getValue(), "").toString();

        // Sprint
        IssueField sprintField = issue.getField("customfield_10004"); // Sprint
        String sprintName = "";
        try {
            final var test = (JSONArray) ObjectUtils.defaultIfNull(sprintField.getValue(), "[]");
            sprintName = ObjectUtils.defaultIfNull(test.toString().substring(test.toString().indexOf(",name=")+6, test.toString().indexOf(",startDate=")), "");
        } catch (Exception e) { }
        // End of Sprint

        return IssueDomainEntity.fromOrigin(
                epicKey,
                issue.getKey(),
                issue.getSelf().toString(),
                Optional.ofNullable(issue.getWatchers())
                        .map(BasicWatchers::getSelf)
                        .map(URI::toString)
                        .orElse(null),
                issue.getLabels(),
                LocalDateTimeConverter.fromJoda(issue.getDueDate()),
                LocalDateTimeConverter.fromJoda(issue.getUpdateDate()),
                LocalDateTimeConverter.fromJoda(issue.getCreationDate()),
                Optional.ofNullable(issue.getAssignee())
                        .map(AddressableNamedEntity::getName)
                        .orElse(null),
                Optional.ofNullable(issue.getReporter())
                        .map(AddressableNamedEntity::getName)
                        .orElse(null),
                issue.getSummary(),
                issue.getIssueType().getName(),
                issue.getStatus().getName(),
                sprintName,
                parentTask,
                isSubTask,
                Optional.ofNullable(issue.getTimeTracking())
                        .map(ISSUE_TIME_TRACKING_DOMAIN_ENTITY_CONVERTER::convert)
                        .orElse(null),
                StreamConverter
                        .fromIterable(worklogs)
                        .map(ISSUE_WORKLOG_DOMAIN_ENTITY_CONVERTER::convert)
                        .collect(Collectors.toList()),
                StreamConverter
                        .fromIterable(issue.getChangelog())
                        .map(ISSUE_CHANGELOG_GROUP_DOMAIN_ENTITY_CONVERTER::convert)
                        .collect(Collectors.toList())
        );
    }
}
