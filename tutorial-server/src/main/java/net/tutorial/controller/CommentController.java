package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.domain.dto.CommentDTO;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.ICommentService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-06-20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/front/comment")
public class CommentController {

    private final ICommentService commentService;

    /**
     * 评论
     * @param commentDTO
     * @return
     */
    @PostMapping()
    public AjaxResult comment(@RequestBody CommentDTO commentDTO){
        return success(commentService.comment(commentDTO));
    }

    /**
     * 获取评论
     * @param commentType
     * @param articleId
     * @return
     */
    @GetMapping("/{commentType}/{articleId}")
    public AjaxResult getCommentContent(@PathVariable("commentType") String commentType,
                                        @PathVariable("articleId") Long articleId){
        return success(commentService.getCommentContent(commentType,articleId));
    }

}
