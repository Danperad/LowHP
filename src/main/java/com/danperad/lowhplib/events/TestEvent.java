package com.danperad.lowhplib.events;

import com.danperad.lowhplib.PlayerLow;
import lombok.Getter;

import java.util.EventObject;

public class TestEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    @Getter
    private final PlayerLow _player;

    public TestEvent(Object source, PlayerLow playerLow) {
        super(source);
        _player = playerLow;
    }

}
