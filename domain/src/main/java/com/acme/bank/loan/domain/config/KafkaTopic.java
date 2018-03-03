package com.acme.bank.loan.domain.config;

public enum KafkaTopic {

    REGISTERED_LOANS("registered-loans"),
    ENTITLED_LOANS("entitled-loans"),
    REJECTED_LOANS("rejected-loans");

    private String topicName;

    KafkaTopic(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
