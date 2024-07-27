package net.tutorial.service.impl;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.User;
import net.tutorial.domain.entity.Like;
import net.tutorial.mapper.LikesMapper;
import net.tutorial.service.ILikeService;
import net.tutorial.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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

    @Override
    public int cancelLikeObject(int type, Long objectId) {
        User user = SecurityUtils.getLoginUserByApp().getUser();
        Long userId = user.getId();
        return likesMapper.deleteLike(new Like(userId, type, objectId));
    }

    @Override
    public Set<Long> getSetUserLikesByType(int type) {
        User user = SecurityUtils.getLoginUserByApp().getUser();
        Long userId = user.getId();
        Set<Long> set = new HashSet<>();
        for (Like like1: likesMapper.selectLikes(new Like(userId, type))) {
            set.add(like1.getObjectId());
        }
        return set;
    }
}
