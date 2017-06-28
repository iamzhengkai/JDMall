package com.kevin.jdmall.iview;

import com.kevin.jdmall.bean.CommentCountResult;
import com.kevin.jdmall.bean.CommentDetailResult;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.iview.IDetailCommentView.java
 * @author: zk
 * @date: 2017-06-28 17:54
 */

public interface IDetailCommentView extends IView{
    void setCommentCount(CommentCountResult result);
    void setComment(CommentDetailResult result);
}
