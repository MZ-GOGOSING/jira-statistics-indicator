package kr.co.mz.jira.api.custom;

import com.atlassian.jira.rest.client.api.domain.BasicUser;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import com.atlassian.jira.rest.client.internal.json.JsonObjectParser;
import com.atlassian.jira.rest.client.internal.json.JsonParseUtil;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.joda.time.DateTime;

public class CustomWorklogJsonParser implements JsonObjectParser<Iterable<Worklog>> {

  @Override
  public Iterable<Worklog> parse(JSONObject json) throws JSONException {
    final var jsonArray = JsonParseUtil.getNestedOptionalArray(json, "worklogs");

    if (jsonArray == null) {
      return Collections.emptyList();
    }

    final Collection<Worklog> res = new ArrayList<>(jsonArray.length());
    for (int i = 0; i < jsonArray.length(); i++) {
      final var jsonObject = (JSONObject) jsonArray.get(i);
      final var parsedWorklog = this.doParse(jsonObject);
      res.add(parsedWorklog);
    }

    return res;
  }

  private Worklog doParse(final JSONObject json) throws JSONException {
    final URI self = JsonParseUtil.getSelfUri(json);
    //final URI issueUri = JsonParseUtil.parseURI(json.getString("issue"));
    final BasicUser author = JsonParseUtil.parseBasicUser(json.optJSONObject("author"));
    final BasicUser updateAuthor = JsonParseUtil.parseBasicUser(json.optJSONObject("updateAuthor"));
    // it turns out that somehow it can be sometimes omitted in the resource representation - JRJC-49
    final String comment = JsonParseUtil.getOptionalString(json, "comment");
    final DateTime creationDate = JsonParseUtil.parseDateTime(json, "created");
    final DateTime updateDate = JsonParseUtil.parseDateTime(json, "updated");
    final DateTime startDate = JsonParseUtil.parseDateTime(json, "started");
    final int timeSpentSeconds = json.getInt("timeSpentSeconds");
    final int minutesSpent = (int) timeSpentSeconds / 60;
    //final Visibility visibility = new VisibilityJsonParser().parseVisibility(json);
    return new Worklog(self, null, author, updateAuthor, comment, creationDate, updateDate, startDate, minutesSpent, null);
  }
}
