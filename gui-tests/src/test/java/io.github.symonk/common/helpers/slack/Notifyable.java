package io.github.symonk.common.helpers.slack;

@FunctionalInterface
public interface Notifyable {

    void notify(final String message);

}
