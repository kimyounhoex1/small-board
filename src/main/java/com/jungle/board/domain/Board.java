package com.jungle.board.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private long idx;
    private String contents;
    private String title;

    private LocalDateTime createdAt;
    private long createdBy;

    @Override
    public String toString() {
        return "[Board]: idx = " + idx + ", contents = " + contents +
                ", title = " + title + ", createdAt = " + createdAt +
                ", createdBy = " + createdBy;
    }
}
