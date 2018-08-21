package io.github.symonk.common.helpers.slack;

import io.github.symonk.configurations.properties.FrameworkProperties;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

import javax.inject.Inject;

/**
 * The documentation outlines how to enable, configure and use slack integration on the read me,
 * however here are the simple steps: Go to your_team.slack.com/services/new Search for incoming
 * webHook and click in Add Choose channel to post and press add incoming webhooks integration Set
 * the webhook url in the framework properties (@Default empty) Set the slack notifications enabled
 * to true (@Default false) Both of these properties are required, they can be set at runtime using
 * standard maven -Dslack.enabled etc
 */
public class SlackHelper implements Notifyable {

  private final SlackApi slackApi;
  private final boolean isSlackSupportEnabled;

  @Inject
  public SlackHelper(final FrameworkProperties properties) {
    this.slackApi = new SlackApi(properties.slackWebhookEndpoint());
    this.isSlackSupportEnabled = properties.isSlackEnabled();
  }

  /** The webhook configured by slack is actually tightly coupled to a channel */
  public synchronized void notify(final String message) {
    if (!isSlackSupportEnabled) return;
    slackApi.call(new SlackMessage(message));
  }
}
