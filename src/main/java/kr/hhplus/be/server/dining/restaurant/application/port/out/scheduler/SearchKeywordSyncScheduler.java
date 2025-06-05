package kr.hhplus.be.server.dining.restaurant.application.port.out.scheduler;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.GetKeywordBackupRepository;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.SearchKeywordRepository;
import kr.hhplus.be.server.dining.restaurant.model.KeywordBackup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchKeywordSyncScheduler {
    private final GetKeywordBackupRepository getKeywordBackupRepository;
    private final List<SearchKeywordRepository> searchKeywordRepositories;

    @Scheduled(fixedRate = 300000) // 5분 = 300,000ms
    public void sync() {
        try {
            List<KeywordBackup> keywords = getKeywordBackupRepository.findAll();

            for (SearchKeywordRepository repository : searchKeywordRepositories) {
                try {
                    for (KeywordBackup keyword : keywords) {
                        for (int i = 0; i < keyword.count(); i++) {
                            repository.saveKeyword(keyword.keyword());
                        }
                    }
                    break; // 성공하면 중단
                } catch (Exception e) {
                    log.error("Failed to sync keyword data to repository: {}", e.getMessage());
                }
            }
        } catch (Exception e) {
            log.error("Failed to sync keyword data: {}", e.getMessage());
        }
    }
}
