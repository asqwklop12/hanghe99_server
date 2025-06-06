package kr.hhplus.be.server.dining.keyword.application.port.out.listener;

import kr.hhplus.be.server.dining.keyword.application.port.out.repository.UpsertKeywordBackupRepository;
import kr.hhplus.be.server.dining.shared.SearchEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchKeywordBackupEventListener {
    private final UpsertKeywordBackupRepository upsertRepository;

    @Async
    @EventListener
    public void handleSearchEvent(SearchEvent event) {
        try {
            upsertRepository.save(event.keyword());
        } catch (Exception ignored) {
            // 백업 저장 실패는 무시
        }
    }
} 
