package kr.hhplus.be.server.dining.restaurant.application.port.out.scheduler.fake;

import java.util.List;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.GetKeywordBackupRepository;
import kr.hhplus.be.server.dining.restaurant.application.port.out.repository.query.SearchKeywordRepository;
import kr.hhplus.be.server.dining.restaurant.model.KeywordBackup;

public class FakeSearchKeywordSyncScheduler {
    private final GetKeywordBackupRepository queryRepository;
    private final List<SearchKeywordRepository> searchKeywordRepositories;

    public FakeSearchKeywordSyncScheduler(
        GetKeywordBackupRepository queryRepository,
        List<SearchKeywordRepository> searchKeywordRepositories
    ) {
        this.queryRepository = queryRepository;
        this.searchKeywordRepositories = searchKeywordRepositories;
    }

    public void sync() {
        try {
            List<KeywordBackup> keywords = queryRepository.findAll();
            
            for (SearchKeywordRepository repository : searchKeywordRepositories) {
                try {
                    for (KeywordBackup keyword : keywords) {
                        for (int i = 0; i < keyword.count(); i++) {
                            repository.saveKeyword(keyword.keyword());
                        }
                    }
                    break; // 성공하면 중단
                } catch (Exception ignored) {
                    // 실패 시 다음 저장소로 진행
                }
            }
        } catch (Exception ignored) {
            // DB 조회 실패 시 동기화 중단
        }
    }
} 
