package yinyueba.meetstudio.com.library;

import yinyueba.meetstudio.com.library.model.ErrorBean;

/**
 * 作者：chaoyongzhang on 15/11/10 16:38
 * 邮箱：zhangcy@meet-future.com
 */
public interface YCHRequestHandler {
    void onYCHRequestSuccess(String msg);
    void onYCHRequestFailed(ErrorBean error);
}
