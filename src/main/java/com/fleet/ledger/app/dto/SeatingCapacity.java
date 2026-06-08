package com.fleet.ledger.app.dto;

public enum SeatingCapacity {
    TWO(2),
    FOUR(4),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    TWELVE(12),
    FOURTEEN(14),
    SEVENTEEN(17),
    TWENTY(20),
    THIRTY(30),
    FORTY(40),
    FIFTY(50);

    private final int capacity;

    SeatingCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
