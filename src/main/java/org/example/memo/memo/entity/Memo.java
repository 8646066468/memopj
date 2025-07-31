package org.example.memo.memo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.memo.memo.dto.MemoRequestDto;

@Getter
@AllArgsConstructor
public class Memo {
    private Long id;
    private String title;
    private String contents;

    public void updata(MemoRequestDto dto) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();

    }
public void updataTitle(MemoRequestDto dto) {
    this.title = dto.getTitle();
}
}