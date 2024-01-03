package kr.co.sloop.post.service;

import kr.co.sloop.post.domain.PageDTO;
import org.springframework.stereotype.Service;

@Service
public interface PageService {
    PageDTO pagingInitialize(int page, int boardIdx);
}
