package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.domain.dto.CommentDTO;
import net.tutorial.domain.dto.ReplyDTO;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.IReplyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 回复表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-06-21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/front/reply")
public class ReplyController {

    private final IReplyService replyService;

    /**
     * 回复评论
     * @param replyDTO
     * @return
     */
    @PostMapping()
    public AjaxResult reply(@RequestBody ReplyDTO replyDTO){
        return success(replyService.reply(replyDTO));
    }
}
