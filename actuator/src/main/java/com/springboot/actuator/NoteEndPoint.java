package com.springboot.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 커스텀 엔드포인트
 * 간단한 메모 기록을 남길 수 있는 기능을 엔드포인트로 생성
 *
 * @Endpoint
 * 엑추에이터에 엔드포인트로 자동 등록
 * id 속성 : 값으로 경로를 정의할 수 있다.
 * enableByDefault 속성 : 현재 생성하는 엔드포인트의 기본 활성화 여부 설정, 기본값은 true
 */
@Component
@Endpoint(id = "note")
public class NoteEndPoint {

    private Map<String, Object> noteContent = new HashMap<>();

    /**
     * @ReadOperation : HTTP GET 요청에 반응
     */
    @ReadOperation
    public Map<String, Object> getNoteContent() {
        return noteContent;
    }

    /**
     * @WriteOperation : HTTP POST 요청에 반응 
     */
    @WriteOperation
    public Map<String, Object> writeNote(String key, Object value) {
        noteContent.put(key, value);
        return noteContent;
    }

    /**
     * @DeleteOperation : HTTP DELETE 요청에 반응
     */
    @DeleteOperation
    public Map<String, Object> deleteNote(String key) {
        noteContent.remove(key);
        return noteContent;
    }
}
