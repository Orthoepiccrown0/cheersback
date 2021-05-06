package com.cheers.main.container;

import com.cheers.main.model.events.CommercialEvent;
import com.cheers.main.model.events.PrivateEvent;

public class SubscribedEventResponse {

    private PrivateEvent privateEvent;

    private CommercialEvent commercialEvent;

    public SubscribedEventResponse(PrivateEvent privateEvent) {
        this.privateEvent = privateEvent;
    }

    public SubscribedEventResponse(CommercialEvent commercialEvent) {
        this.commercialEvent = commercialEvent;
    }

    public PrivateEvent getPrivateEvent() {
        return privateEvent;
    }

    public CommercialEvent getCommercialEvent() {
        return commercialEvent;
    }
}
