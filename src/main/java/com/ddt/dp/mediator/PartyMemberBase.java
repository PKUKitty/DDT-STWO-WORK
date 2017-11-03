/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.mediator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PartyMemberBase implements PartyMember {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartyMemberBase.class);

    protected Party party;

    @Override
    public void joinedParty(Party party) {
        LOGGER.info("{} joins the party", this);
        this.party = party;
    }

    @Override
    public void partyAction(Action action) {
        LOGGER.info("{} {}", this, action.getDescription());
    }

    @Override
    public void act(Action action) {
        if (party != null) {
            LOGGER.info("{} {}", this, action);
            party.act(this, action);
        }
    }

    @Override
    public abstract String toString();
}
