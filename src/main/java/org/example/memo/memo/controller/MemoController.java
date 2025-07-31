package org.example.memo.memo.controller;

import org.example.memo.memo.dto.MemoRequestDto;
import org.example.memo.memo.dto.MemoResponseDto;
import org.example.memo.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/memos") // prefix
public class MemoController {

    // 자료구조가 DB 역할 수행
    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        // 식별자가 1씩 증가 하도록 만듦
        Long memoId = memoList.isEmpty() ? 1 : Collections.max(memoList.keySet()) + 1;

        // 요청받은 데이터로 Memo 객체 생성
        Memo memo = new Memo(memoId, requestDto.getTitle(), requestDto.getContents());

        // Inmemory DB에 Memo 저장
        memoList.put(memoId, memo);

        return new MemoResponseDto(memo);
    }

    @GetMapping("/{id}")
    public MemoResponseDto findMemoById(@PathVariable Long id) {
        Memo memo = memoList.get(id);
        return new MemoResponseDto(memo);

    }

    @PutMapping("/{id}")
    public MemoResponseDto updateMemoById(@PathVariable Long id, @RequestBody MemoRequestDto dto) {
        Memo memo = memoList.get(id);
        memo.updata(dto);

        return new MemoResponseDto(memo);
    }
    @DeleteMapping("/{id}")
    public void deleteMemoById(@PathVariable Long id) {
        memoList.remove(id);
    }
}