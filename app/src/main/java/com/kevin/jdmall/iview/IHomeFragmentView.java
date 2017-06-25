package com.kevin.jdmall.iview;

import com.kevin.jdmall.bean.BannerResult;
import com.kevin.jdmall.bean.RecommendResult;
import com.kevin.jdmall.bean.SecKillResult;

import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.iview.IHomeFragmentView.java
 * @author: zk
 * @date: 2017-06-14 13:14
 */

public interface IHomeFragmentView extends IView{
    void startRun(List<BannerResult.ResultBean> list);
    void showAd(String url);
    void initSecKill(List<SecKillResult.ResultBean.RowsBean> list);
    void initRecommend(List<RecommendResult.ResultBean.RowsBean> list);

}
