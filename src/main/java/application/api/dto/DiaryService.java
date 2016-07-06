package application.api.dto;

import application.elasticsearch.ElasticsearchPersistenceService;

public interface DiaryService extends ElasticsearchPersistenceService<DiaryEntryDto>{

}
