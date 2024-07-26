package net.tutorial.service.impl;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Like;
import net.tutorial.mapper.LikesMapper;
import net.tutorial.service.ILikeService;
import org.springframework.stereotype.Service;

/**
 * @author guansiyu
 * @date 2024/7/26
 */
@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements ILikeService {
    private final LikesMapper likesMapper;

    @Override
    public int likeObject(Long userId, int type, Long objectId) {
        return likesMapper.insertLike(new Like(userId, type, objectId));
    }

}
